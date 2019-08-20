package com.aquinoa.ziptechtask.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class InvalidFieldException extends Exception {

  final String field;
  final String message;
}
