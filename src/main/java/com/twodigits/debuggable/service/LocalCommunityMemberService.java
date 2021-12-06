package com.twodigits.debuggable.service;

import com.twodigits.debuggable.model.CommunityMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Profile("local")
public class LocalCommunityMemberService implements ICommunityMemberService {

    @Override
    public List<CommunityMember> getAllMembers() {
        log.info("Local: Getting all members");
        return null;
    }
}
