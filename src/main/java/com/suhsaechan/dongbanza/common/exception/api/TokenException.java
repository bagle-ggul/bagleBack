package com.suhsaechan.dongbanza.common.exception.api;

import com.suhsaechan.dongbanza.common.exception.CustomException;
import com.suhsaechan.dongbanza.common.exception.ErrorCode;

public class TokenException extends CustomException {

  public TokenException(
      ErrorCode errorCode) {
    super(errorCode);
  }
}
