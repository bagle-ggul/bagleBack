package com.suhsaechan.dongbanza.member.repository;

import com.suhsaechan.dongbanza.member.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
  Optional<Member> findByEmail(String email);
  Optional<Member> findByCharacterName(String characterName);
}
