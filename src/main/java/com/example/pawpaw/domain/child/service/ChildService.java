package com.example.pawpaw.domain.child.service;

import com.example.pawpaw.domain.child.dto.*;

import java.util.List;

public interface ChildService {

    ChildDTO addChild(CreateChildDTO createChildDTO);

    List<ChildSummaryDTO> getChildren();

    ChildDTO getChild(Integer childId);

    void deleteChild(Integer childId);

    ChildDTO updateChild(Integer childId, UpdateChildDTO updateChildDTO);

    ChildDTO syncChildren(ChildSyncDTO childSyncDTO);
}
