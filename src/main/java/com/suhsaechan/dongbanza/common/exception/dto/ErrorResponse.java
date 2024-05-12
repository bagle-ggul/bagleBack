package com.suhsaechan.dongbanza.common.exception.dto;

import com.suhsaechan.dongbanza.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  private ErrorCode errorCode;
  private String errorMessage;
}

