package com.suhsaechan.dongbanza.common.jwt.service;

import com.suhsaechan.dongbanza.common.exception.ErrorCode;
import com.suhsaechan.dongbanza.common.exception.api.TokenException;
import com.suhsaechan.dongbanza.common.jwt.domain.entity.RefreshToken;
import com.suhsaechan.dongbanza.common.jwt.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken findByRefreshToken(String refreshToken){
    return refreshTokenRepository.findByRefreshToken(refreshToken)
        .orElseThrow(() -> new TokenException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
  }

  // RefreshToken 존재시 토큰 업데이트, 존재하지않을시 객체 생성
  public void createOrUpdateRefreshToken(Long memberId, String refreshToken){
    // 객체가 있는지 확인
    RefreshToken token = refreshTokenRepository.findByMemberId(memberId)
        .orElse(
            // 객체가 존재하지 않을시 객체 생성 후 토큰저장
            RefreshToken.builder()
                .memberId(memberId)
                .refreshToken(refreshToken)
            .build());

    // 객체안에 토큰 String 저장
    token.update(refreshToken);
    refreshTokenRepository.save(token);
  }
}
