package com.suhsaechan.dongbanza.common.jwt.controller;

import com.suhsaechan.dongbanza.common.api.docs.TokenControllerDocs;
import com.suhsaechan.dongbanza.common.jwt.dto.request.CreateAccessTokenRequest;
import com.suhsaechan.dongbanza.common.jwt.dto.response.CreateAccessTokenResponse;
import com.suhsaechan.dongbanza.common.jwt.service.TokenService;
import com.suhsaechan.dongbanza.common.log.LogMonitoringInvocation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TokenController implements TokenControllerDocs {
  private final TokenService tokenService;

  @PostMapping("/token")
  @LogMonitoringInvocation
  public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
      @RequestBody CreateAccessTokenRequest request
  ) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CreateAccessTokenResponse.builder()
            .accessToken(tokenService.createNewAccessToken(request.getRefreshToken()))
            .build());
  }
}
