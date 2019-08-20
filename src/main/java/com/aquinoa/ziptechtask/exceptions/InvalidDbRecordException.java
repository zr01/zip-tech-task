package com.aquinoa.ziptechtask.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class InvalidDbRecordException extends Exception {

  final String message;
}
