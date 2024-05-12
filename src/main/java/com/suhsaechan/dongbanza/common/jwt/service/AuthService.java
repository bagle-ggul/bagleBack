package com.suhsaechan.dongbanza.common.jwt.service;

import com.suhsaechan.dongbanza.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
  private final JwtUtil jwtUtil;
  private final MemberRepository memberRepository;
  private final PasswordEncoder encoder;


}
