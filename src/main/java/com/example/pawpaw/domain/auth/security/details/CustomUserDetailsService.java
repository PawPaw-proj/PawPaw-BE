package com.example.pawpaw.domain.auth.security.details;

import com.example.pawpaw.domain.auth.entity.User;
import com.example.pawpaw.domain.auth.repository.UserRepository;
import com.example.pawpaw.domain.child.entity.Child;
import com.example.pawpaw.domain.child.repository.ParentChildRepository;
import com.example.pawpaw.global.response.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.pawpaw.global.response.ErrorCode.BAD_REQUEST_RESOURCE;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ParentChildRepository parentChildRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userId) throws CustomException {

        Integer id;
        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            throw new CustomException(BAD_REQUEST_RESOURCE, "userId 형식이 맞지 않습니다.");
        }

        User user = userRepository.findById(id);
        List<Child> children = parentChildRepository.findChildrenByParentId(id);

        return new CustomUserDetails(user.getId(), children.stream().map(Child::getId).toList());
    }
}
