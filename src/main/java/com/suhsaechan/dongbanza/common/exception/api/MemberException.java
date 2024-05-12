package com.suhsaechan.dongbanza.common.exception.api;

import com.suhsaechan.dongbanza.common.exception.CustomException;
import com.suhsaechan.dongbanza.common.exception.ErrorCode;

public class MemberException extends CustomException {
  public MemberException(
      ErrorCode errorCode) {
    super(errorCode);
  }
}