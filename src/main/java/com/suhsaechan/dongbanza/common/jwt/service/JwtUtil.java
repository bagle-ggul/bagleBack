package com.suhsaechan.dongbanza.common.jwt.service;

import com.suhsaechan.dongbanza.common.jwt.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtil {

  private final long accessTokenExpTime;
  private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
  private final String ROLE ="role";

  // JWT 비밀키, 만료시간 정의 생성자
  public JwtUtil(
//      @Value("${jwt.secret}") String secretKey
      @Value("${spring.jwt.expiration_time}")
      long accessTokenExpTime
  ) {
//    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.accessTokenExpTime = accessTokenExpTime;
  }

  // Access Token 생성
  public String createAccessToken(CustomUserDetails customUserDetails,
      long accessTokenExpTime) {
    return createAccessToken(customUserDetails, this.accessTokenExpTime);
  }

  private String createToken(CustomUserDetails customUserDetails,
      long expiredAt) {
    Date now = new Date();

    // Header 설정
    Map<String, Object> headers = new HashMap<>();
    headers.put("typ", "JWT");

    return Jwts.builder()
        .header()
        .add(headers)
        .and()
        .issuer("Saechan")
        .issuedAt(now)
        .expiration(new Date(now.getTime() + expiredAt))
        .subject(customUserDetails.getUsername())
        .claim(ROLE, customUserDetails.getAuthorities())
        .signWith(secretKey)
        .compact();
  }

  // JWT 검증
  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(secretKey)
          .build()
          .parseSignedClaims(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException |
             MalformedJwtException e) {
      log.info("Invalid JWT Token", e);
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT Token", e);
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT Token", e);
    } catch (IllegalArgumentException e) {
      log.info("JWT claims string is empty.", e);
    }
    return false;
  }

  // 토큰 기반 -> 인증 정보 가져옴 // 누구의 토큰인가
  public Authentication getAuthentication(String token) {
    Claims claims = getClaims(token);

    Set<SimpleGrantedAuthority> authorities
        = Collections.singleton(new SimpleGrantedAuthority(claims.get(ROLE, String.class)));

    return new UsernamePasswordAuthenticationToken(
        new User(claims.getSubject(), "", authorities), token, authorities);
  }

  public Claims getClaims(String token) {
    return Jwts.parser()
        .decryptWith(secretKey)
        .build()
        .parseEncryptedClaims(token).getPayload();
  }

}