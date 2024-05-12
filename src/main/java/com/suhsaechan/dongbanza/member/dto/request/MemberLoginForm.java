package com.suhsaechan.dongbanza.member.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginForm {
  private String email;
  private String password;
}