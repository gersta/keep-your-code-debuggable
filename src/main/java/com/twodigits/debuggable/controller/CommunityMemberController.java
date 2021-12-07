package com.twodigits.debuggable.controller;

import com.twodigits.debuggable.model.CommunityMember;
import com.twodigits.debuggable.service.ICommunityMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
@Slf4j
public class CommunityMemberController {

    private final ICommunityMemberService service;

    public CommunityMemberController(ICommunityMemberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CommunityMember>> getAllMembers() {
        log.info("Getting all members");
        return ResponseEntity.ok(service.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityMember> getMember(@PathVariable("id") String id) {
        log.info("Getting member {}", id);
        return ResponseEntity.ok(service.getMemberById(id));
    }
}
