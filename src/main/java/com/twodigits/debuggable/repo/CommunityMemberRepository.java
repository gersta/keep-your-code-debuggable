package com.twodigits.debuggable.repo;

import com.twodigits.debuggable.model.CommunityMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityMemberRepository extends JpaRepository<CommunityMember, String> {
}
