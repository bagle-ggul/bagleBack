package com.suhsaechan.dongbanza.common.jwt.service;

import com.suhsaechan.dongbanza.common.exception.ErrorCode;
import com.suhsaechan.dongbanza.common.exception.api.MemberException;
import com.suhsaechan.dongbanza.common.jwt.dto.CustomUserDetails;
import com.suhsaechan.dongbanza.member.domain.entity.Member;
import com.suhsaechan.dongbanza.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final MemberRepository memberRepository;

  // memberId 사용
  @Override
  public CustomUserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
    Member member = memberRepository.findById(Long.parseLong(memberId))
        .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));
    return new CustomUserDetails(member);
  }
}