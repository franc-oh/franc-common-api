package com.franc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BizException extends RuntimeException {
    private final ExceptionContent result;
}
