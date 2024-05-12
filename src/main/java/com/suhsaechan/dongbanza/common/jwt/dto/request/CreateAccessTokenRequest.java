package com.suhsaechan.dongbanza.common.jwt.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAccessTokenRequest {
  private String refreshToken;
}
