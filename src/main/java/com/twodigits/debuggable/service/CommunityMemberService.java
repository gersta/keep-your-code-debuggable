package com.twodigits.debuggable.service;

import com.twodigits.debuggable.model.CommunityMember;
import com.twodigits.debuggable.repo.CommunityMemberRepository;
import com.twodigits.debuggable.util.FileReaderWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Profile("server")
public class CommunityMemberService implements ICommunityMemberService {

    private static final String DATABASE_PREFIX = "H2";

    private final CommunityMemberRepository repository;
    private final FileReaderWriter fileWriter;

    public CommunityMemberService(CommunityMemberRepository repository, FileReaderWriter fileWriter) {
        this.repository = repository;
        this.fileWriter = fileWriter;
    }

    public List<CommunityMember> getAllMembers() {
        log.info("Server: Getting all members");
        List<CommunityMember> members = repository.findAll();
        fileWriter.writeFile(DATABASE_PREFIX, Collections.emptyList(), members);

        return members;
    }

    @Override
    public CommunityMember getMemberById(String id) {
        log.info("Server: Getting member {}", id);

        Optional<CommunityMember> member = repository.findById(id);
        if ( member.isPresent() ) {
            fileWriter.writeFile(DATABASE_PREFIX, Collections.singletonList(id), member.get());

            return member.get();
        }

        return new CommunityMember();
    }
}
