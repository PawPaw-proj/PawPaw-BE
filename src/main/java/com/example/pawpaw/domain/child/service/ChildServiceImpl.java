package com.example.pawpaw.domain.child.service;

import com.example.pawpaw.domain.auth.entity.User;
import com.example.pawpaw.domain.auth.service.AuthService;
import com.example.pawpaw.domain.child.dto.*;
import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.entity.ParentChild;
import com.example.pawpaw.domain.child.repository.ChildRepository;
import com.example.pawpaw.domain.child.repository.ParentChildRepository;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final AuthService authService;
    private final ChildRepository childRepository;
    private final ParentChildRepository parentChildRepository;

    @Transactional
    @Override
    public ChildDTO addChild(CreateChildDTO createChildDTO) {
        User parent = authService.getUser();

        Child child = childRepository.save(Child.builder()
                .address(createChildDTO.address())
                .name(createChildDTO.name())
                .birthDate(createChildDTO.birthDate())
                .height(createChildDTO.height())
                .weight(createChildDTO.weight())
                .build());

        parentChildRepository.save(ParentChild.of(parent, child));
        authService.refreshSecurityContext();
        return ChildDTO.of(child);
    }

    @Override
    public List<ChildSummaryDTO> getChildren() {
        User parent = authService.getUser();
        List<Child> children = parentChildRepository.findChildrenByParentId(parent.getId());
        return children.stream().map(ChildSummaryDTO::of).toList();
    }

    @Override
    public ChildDTO getChild(Integer childId) {
        if (!authService.isParentOfChild(childId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }
        Child child = childRepository.findById(childId);
        return ChildDTO.of(child);
    }

    @Transactional
    @Override
    public void deleteChild(Integer childId) {
        if (!authService.isParentOfChild(childId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }
        childRepository.deleteById(childId);
        authService.refreshSecurityContext();
    }

    @Transactional
    @Override
    public ChildDTO updateChild(Integer childId, UpdateChildDTO updateChildDTO) {
        if (!authService.isParentOfChild(childId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CHILD);
        }
        Child child = childRepository.findById(childId);
        Child updatedChild = child.update(updateChildDTO.name(), updateChildDTO.birthDate(),
                updateChildDTO.height(), updateChildDTO.weight(), updateChildDTO.profile());
        return ChildDTO.of(updatedChild);
    }

    @Transactional
    @Override
    public ChildDTO syncChildren(ChildSyncDTO childSyncDTO) {
        User parent = authService.getUser();
        Child child = childRepository.findByAddress(childSyncDTO.address());
        parentChildRepository.save(ParentChild.of(parent, child));
        authService.refreshSecurityContext();
        return ChildDTO.of(child);
    }
}
