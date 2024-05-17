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
  private String accessToken; // 헤더에 포함된다
  private String refreshToken; // body에 포함시킨다

  public static MemberLoginResponse from(Member member, String accessToken, String refreshToken) {
    return MemberLoginResponse.builder()
        .id(member.getId())
        .email(member.getEmail())
        .nickname(member.getNickname())
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  public static MemberLoginResponse exceptAccessToken(MemberLoginResponse response){
    return MemberLoginResponse.builder()
        .id(response.getId())
        .email(response.getEmail())
        .nickname(response.getNickname())
        .refreshToken(response.getRefreshToken())
        .build();
  }
}

