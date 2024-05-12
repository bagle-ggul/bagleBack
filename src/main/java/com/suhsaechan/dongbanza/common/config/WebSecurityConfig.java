package com.suhsaechan.dongbanza.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suhsaechan.dongbanza.common.jwt.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final UserDetailsService userDetailsService;
  private final ObjectMapper objectMapper;

  private final static String MEMBER = "USER"; // Spring 기본 유저
  private final static String ADMIN = "ADMIN"; // Spring 기본 관리자
  @Bean
  public WebSecurityCustomizer configure() {
    return (web) -> web.ignoring()
        .requestMatchers(new AntPathRequestMatcher("/static/**"));
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers(
                "/", // 기본화면
                "/api/signup", // 회원가입
                "/api/login", // 로그인
                "/docs/**", // Swagger
                "/v3/api-docs/**" // Swagger
            ).permitAll()
            .requestMatchers(HttpMethod.POST,"/api/token").hasRole(MEMBER)
            .anyRequest().authenticated()
        )

        // 폼 로그인 (현재사용안함)
//        .formLogin(formLogin -> formLogin
//            .loginPage("/login")
//            .defaultSuccessUrl("/home")
//        )

        .logout(logout -> logout
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService customUserDetailsService) throws Exception {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(customUserDetailsService);
    authProvider.setPasswordEncoder(bCryptPasswordEncoder);
    return new ProviderManager(authProvider);
  }
}