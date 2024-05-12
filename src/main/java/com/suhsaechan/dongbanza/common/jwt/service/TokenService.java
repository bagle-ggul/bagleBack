package com.suhsaechan.dongbanza.common.jwt.service;

import com.suhsaechan.dongbanza.common.exception.ErrorCode;
import com.suhsaechan.dongbanza.common.exception.api.TokenException;
import com.suhsaechan.dongbanza.common.jwt.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
  private final JwtUtil jwtUtil;
  private final RefreshTokenService refreshTokenService;
  private final CustomUserDetailsService customUserDetailsService;


  @Value("${spring.jwt.expiration_time}")
  private long accessTokenExpTime;

  public String createNewAccessToken(String refreshToken){
    if(!jwtUtil.validateToken(refreshToken)){
      throw new TokenException(ErrorCode.INVALID_REFRESH_TOKEN);
    }
    Long memberId = refreshTokenService.findByRefreshToken(refreshToken).getMemberId();
    CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(memberId.toString());
    return jwtUtil.createAccessToken(userDetails, accessTokenExpTime);
  }
}
