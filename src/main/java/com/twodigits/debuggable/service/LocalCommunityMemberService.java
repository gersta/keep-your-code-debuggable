package com.twodigits.debuggable.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.twodigits.debuggable.exceptions.LocalDataReadException;
import com.twodigits.debuggable.model.CommunityMember;
import com.twodigits.debuggable.util.FileReaderWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Profile("local")
public class LocalCommunityMemberService implements ICommunityMemberService {

    private static final String DATABASE_PREFIX = "H2";

    private final FileReaderWriter fileReader;

    public LocalCommunityMemberService(FileReaderWriter fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public List<CommunityMember> getAllMembers() {
        log.info("Local: Getting all members");

        return fileReader.readFile(DATABASE_PREFIX, Collections.emptyList(), new TypeReference<List<CommunityMember>>() {});
    }

    @Override
    public CommunityMember getMemberById(String id) {
        log.info("Local: Getting member {}", id);

        return fileReader.readFile(DATABASE_PREFIX, Collections.singletonList(id), new TypeReference<CommunityMember>() {});
    }
}
