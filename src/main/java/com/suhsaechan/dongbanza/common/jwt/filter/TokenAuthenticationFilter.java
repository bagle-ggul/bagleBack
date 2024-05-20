package com.suhsaechan.dongbanza.common.jwt.filter;

import com.suhsaechan.dongbanza.common.jwt.service.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.util.AntPathMatcher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  // Security와 JWT 인증 생략하는 URI
  private static final String[] WHITELIST = {
      "/", // 기본화면
      "/api/signup", // 회원가입
      "/api/login", // 로그인
      "/docs/**", // Swagger
      "/v3/api-docs/**", // Swagger
      "/api/token", // Access Token 재발급
      "/actuator/**", // Prometheus 엔드포인트 허용
      "/favicon.ico" // Prometheus
  };

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String URI = request.getRequestURI();
    String authorizationHeader = request.getHeader("Authorization");

    // WHITELIST URL 인 경우 -> JWT Token Validation 하지않는다.
    if (Arrays.stream(WHITELIST)
        .anyMatch(whiteListUri -> antPathMatcher.match(whiteListUri, URI))) {
      logger.info("Whitelisted URI: " + URI);
      // Token 검사 생략
      filterChain.doFilter(request, response);
      return;
    }

    // 이외 주소 Token 검사
    String token = getAccessToken(authorizationHeader);

    // 토큰 검사 통과 로직
    if (jwtUtil.validateToken(token)) {
      log.info("Token 검사로직 통과: " + URI );
      Authentication authentication = jwtUtil.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String getAccessToken(String authorizationHeader) {
    if (authorizationHeader != null && authorizationHeader.startsWith(
        "Bearer ")) {
      return authorizationHeader.substring("Bearer ".length());
    }
    return null;
  }
}