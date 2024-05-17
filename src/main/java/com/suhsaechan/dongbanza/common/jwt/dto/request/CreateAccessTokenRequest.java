package com.suhsaechan.dongbanza.common.jwt.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateAccessTokenRequest {
  private String refreshToken;
}
