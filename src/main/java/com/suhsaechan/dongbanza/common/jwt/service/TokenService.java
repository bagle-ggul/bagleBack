package com.suhsaechan.dongbanza.common.jwt.service;

import com.suhsaechan.dongbanza.common.exception.ErrorCode;
import com.suhsaechan.dongbanza.common.exception.api.TokenException;
import com.suhsaechan.dongbanza.common.jwt.domain.repository.RefreshTokenRepository;
import com.suhsaechan.dongbanza.common.jwt.dto.CustomUserDetails;
import com.suhsaechan.dongbanza.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final JwtUtil jwtUtil;
  private final RefreshTokenService refreshTokenService;
  private final CustomUserDetailsService customUserDetailsService;
  private final RefreshTokenRepository refreshTokenRepository;

  public String createNewAccessToken(String refreshToken) {
    if (!jwtUtil.validateToken(refreshToken)) {
      throw new TokenException(ErrorCode.INVALID_REFRESH_TOKEN);
    }
    Long memberId = refreshTokenService.findByRefreshToken(refreshToken)
        .getMemberId();
    CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(
        memberId.toString());
    return jwtUtil.createAccessToken(userDetails);
  }

  public String createRefreshToken(Member member) {
    // 새로운 Refresh Token String 생성
    String refreshToken = jwtUtil.createRefreshToken(
        CustomUserDetails.builder()
            .member(member)
            .build()
    );

    refreshTokenService.createOrUpdateRefreshToken(member.getId(), refreshToken);
    return refreshToken;
  }


}
