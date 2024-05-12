package com.suhsaechan.dongbanza.member.service;

import com.suhsaechan.dongbanza.common.config.PasswordEncoderConfig;
import com.suhsaechan.dongbanza.common.exception.ErrorCode;
import com.suhsaechan.dongbanza.common.exception.api.MemberException;
import com.suhsaechan.dongbanza.member.domain.constants.MemberRole;
import com.suhsaechan.dongbanza.member.domain.constants.MemberStatus;
import com.suhsaechan.dongbanza.member.domain.entity.Member;
import com.suhsaechan.dongbanza.member.dto.request.MemberLoginForm;
import com.suhsaechan.dongbanza.member.dto.response.MemberDto;
import com.suhsaechan.dongbanza.member.dto.request.MemberSignUpForm;
import com.suhsaechan.dongbanza.member.dto.response.MemberLoginResponse;
import com.suhsaechan.dongbanza.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public MemberDto save(MemberSignUpForm form){
    //TODO: 이메일 중복 검증, 닉네임 중복 검증

    // 이메일 중복 확인
    if(memberRepository.findByEmail(form.getEmail()).isPresent()){
      throw new MemberException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }

    // 닉네임 중복 확인
    if(memberRepository.findByNickname(form.getNickname()).isPresent()){
      throw new MemberException(ErrorCode.NICKNAME_ALREADY_EXISTS);
    }

    return MemberDto.from(
        memberRepository.save(Member.builder()
            .email(form.getEmail())
            .password(passwordEncoder.encode(form.getPassword()))
            .nickname(form.getNickname())
            .phone(form.getPhone())
            .role(MemberRole.MEMBER)
            .status(MemberStatus.ACTIVE)
            .profileImageUrl(null)
            .address(form.getAddress())
            .latitude(form.getLatitude())
            .longitude(form.getLongitude())
            .build()
        )
    );
  }

  public MemberLoginResponse login(MemberLoginForm form){
    String token = ""; //TODO: JWT Token 받는 동작
    memberRepository.findByEmail(form.getEmail())
        .orElseThrow(() -> new MemberException(ErrorCode.EMAIL_NOT_FOUND));
    //TODO: 비밀번호 검증
    form.getPassword();
    return MemberLoginResponse.from(Member.builder().build(), token);
  }
}
