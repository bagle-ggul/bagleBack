package com.suhsaechan.dongbanza.member.dto.request;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class MemberSignUpForm {
  private String email;

  private String password;

  private String nickname;

  private String mbti;

  private LocalDate birthDate;

  private String gender;
}
