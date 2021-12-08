package com.twodigits.debuggable.service;

import com.twodigits.debuggable.model.CommunityMember;
import com.twodigits.debuggable.repo.CommunityMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommunityMemberService {

    private final CommunityMemberRepository repository;

    public CommunityMemberService(CommunityMemberRepository repository) {
        this.repository = repository;
    }

    public List<CommunityMember> getAllMembers() {
        log.info("Getting all members");
        List<CommunityMember> members = repository.findAll();

        return members;
    }
}
