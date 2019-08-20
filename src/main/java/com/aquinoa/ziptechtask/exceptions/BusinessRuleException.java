package com.aquinoa.ziptechtask.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class BusinessRuleException extends Exception {

  final String message;
}
