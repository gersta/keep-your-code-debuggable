package com.twodigits.debuggable.service;

import com.twodigits.debuggable.model.CommunityMember;
import com.twodigits.debuggable.repo.CommunityMemberRepository;
import com.twodigits.debuggable.util.FileReaderWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CommunityMemberService {

    private static final String DATABASE_PREFIX = "H2";

    private final CommunityMemberRepository repository;
    private final FileReaderWriter fileWriter;

    public CommunityMemberService(CommunityMemberRepository repository, FileReaderWriter fileWriter) {
        this.repository = repository;
        this.fileWriter = fileWriter;
    }

    public List<CommunityMember> getAllMembers() {
        log.info("Getting all members");
        List<CommunityMember> members = repository.findAll();
        fileWriter.writeFile(DATABASE_PREFIX, Collections.emptyList(), members);

        return members;
    }
}