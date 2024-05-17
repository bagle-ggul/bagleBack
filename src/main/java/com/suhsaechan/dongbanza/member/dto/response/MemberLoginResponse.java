package com.suhsaechan.dongbanza.member.dto.response;

import com.suhsaechan.dongbanza.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponse {
  private Long id;
  private String email;
  private String characterName;
  private String accessToken; // 헤더에 포함된다
  private String refreshToken; // body에 포함시킨다

  public static MemberLoginResponse from(Member member, String accessToken, String refreshToken) {
    return MemberLoginResponse.builder()
        .id(member.getId())
        .email(member.getEmail())
        .characterName(member.getCharacterName())
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  public static MemberLoginResponse exceptAccessToken(MemberLoginResponse response){
    return MemberLoginResponse.builder()
        .id(response.getId())
        .email(response.getEmail())
        .characterName(response.getCharacterName())
        .refreshToken(response.getRefreshToken())
        .build();
  }
}
