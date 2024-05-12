package com.suhsaechan.dongbanza.common.jwt.service;

import com.suhsaechan.dongbanza.common.exception.ErrorCode;
import com.suhsaechan.dongbanza.common.exception.api.TokenException;
import com.suhsaechan.dongbanza.common.jwt.domain.entity.RefreshToken;
import com.suhsaechan.dongbanza.common.jwt.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken findByRefreshToken(String refreshToken){
    return refreshTokenRepository.findByRefreshToken(refreshToken)
        .orElseThrow(() -> new TokenException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
  }
}
