package com.suhsaechan.dongbanza.member.dto.request;

import lombok.Getter;

@Getter
public class MemberSignUpForm {
  private String email;

  private String password;

  private String nickname;

  private String phone;

  private String address;

  private Double latitude;

  private Double longitude;
}
