package com.suhsaechan.dongbanza.member.domain.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {

  DELETED("탈퇴회원"),
  INACTIVE("휴면회원"),
  ACTIVE("활성회원");

  private final String description;
}
