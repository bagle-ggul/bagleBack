package com.suhsaechan.dongbanza.member.dto.response;

import com.suhsaechan.dongbanza.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
  private Long id;

  private String email;

  private String nickname;

  private String phone;

  private String address;

  private String role;

  private String status;

  private String profileImageUrl;

  private Double latitude;

  private Double longitude;

  private String refreshToken;

  public static MemberDto from(Member member){
    return MemberDto.builder()
        .id(member.getId())
        .address(member.getAddress())
        .email(member.getEmail())
        .nickname(member.getNickname())
        .phone(member.getPhone())
        .role(String.valueOf(member.getRole()))
        .status(String.valueOf(member.getStatus()))
        .profileImageUrl(member.getProfileImageUrl())
        .latitude(member.getLatitude())
        .longitude(member.getLongitude())
        .refreshToken(member.getRefreshToken())
        .build();
  }
}
