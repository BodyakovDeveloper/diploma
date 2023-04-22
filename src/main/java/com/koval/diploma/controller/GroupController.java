package com.koval.diploma.controller;

import com.koval.diploma.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroup(@PathVariable("id") Long id) {
        return ResponseEntity.ok(groupService.getStudentsByGroup(id));
    }
}