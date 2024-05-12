package com.suhsaechan.dongbanza.member.domain.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

  MEMBER("ROLE_USER"),
  ADMIN("ROLE_ADMIN");

  private final String key;
  private static final Map<String, MemberRole> roleMap = new HashMap<>();

  static {
    for (MemberRole role : MemberRole.values()) {
      roleMap.put(role.getKey(), role);
    }
  }

  public static Optional<MemberRole> get(String role) {
    return Optional.of(roleMap.get(role));
  }
}


