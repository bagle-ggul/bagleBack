package com.suhsaechan.dongbanza.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  // Global

  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다."),

  INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

  ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),

  // Token

  REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "리프레시 토큰을 찾을 수 없습니다."),

  INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 리프레시 토큰입니다."),

  // Member

  INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 회원를 찾을 수 없습니다."),

  EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "이메일을 찾을 수 없습니다."),

  CHARACTER_NAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "중복된 캐릭터 이름입니다."),

  EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "중복된 이메일 주소입니다.");

  private final HttpStatus status;
  private final String message;
}