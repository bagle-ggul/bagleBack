package com.suhsaechan.dongbanza.common.jwt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateAccessTokenResponse {
  private String accessToken;
}
