package com.suhsaechan.dongbanza.member.dto.response;

import com.suhsaechan.dongbanza.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponse {
  private Long id;

  private String email;

  private String nickname;

  private String token;

  public static MemberLoginResponse from(Member member, String token){
    return MemberLoginResponse.builder()
        .id(member.getId())
        .email(member.getEmail())
        .nickname(member.getNickname())
        .token(token)
        .build();
  }
}
