package com.example.pawpaw.domain.child.controller;

import com.example.pawpaw.domain.child.dto.*;
import com.example.pawpaw.domain.child.service.ChildService;
import com.example.pawpaw.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/children")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping
    public Response<ChildDTO> addChild(@RequestBody CreateChildDTO createChildDTO) {
        ChildDTO childDTO = childService.addChild(createChildDTO);
        return Response.success(childDTO);
    }

    @GetMapping
    public Response<List<ChildSummaryDTO>> getChildren() {
        List<ChildSummaryDTO> children = childService.getChildren();
        return Response.success(children);
    }

    @GetMapping("/{childId}")
    public Response<ChildDTO> getChild(@PathVariable Integer childId) {
        ChildDTO childDTO = childService.getChild(childId);
        return Response.success(childDTO);
    }

    @DeleteMapping("/{childId}")
    public Response<Void> deleteChild(@PathVariable Integer childId) {
        childService.deleteChild(childId);
        return Response.success();
    }

    @PutMapping("/{childId}")
    public Response<ChildDTO> updateChild(@PathVariable Integer childId, @RequestBody UpdateChildDTO updateChildDTO) {
        ChildDTO childDTO = childService.updateChild(childId, updateChildDTO);
        return Response.success(childDTO);
    }

    @PostMapping("/sync")
    public Response<ChildDTO> syncChildren(@RequestBody ChildSyncDTO childSyncDTO) {
        ChildDTO childDTO = childService.syncChildren(childSyncDTO);
        return Response.success(childDTO);
    }
}
