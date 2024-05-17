package com.suhsaechan.dongbanza.member.domain.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

  USER("ROLE_USER"), // Member
  ADMIN("ROLE_ADMIN");

  private final String key;
}


