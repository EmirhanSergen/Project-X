package com.projectx.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFound extends RuntimeException {
  public MemberNotFound(String message) {
    super(message);
  }
}
