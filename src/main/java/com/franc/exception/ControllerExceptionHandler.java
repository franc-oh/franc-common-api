package com.franc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * '비즈니스 예외' 처리
     * @param e
     * @return
     */
    @ExceptionHandler({BizException.class})
    public ResponseEntity<ExceptionResponse> bizExceptionHandler(BizException e) {
        e.printStackTrace();

        String errorCode = String.valueOf(e.getResult().getCode().value());
        String errorMessage = e.getResult().getMessage();

        return ResponseEntity.status(e.getResult().getCode())
                .body(buildExceptionResponse(errorCode, errorMessage));
    }

    /**
     * '파라미터 유효성 검증 실패' 처리
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponse> methodArgmentNotValidExceptionHandler(
            MethodArgumentNotValidException e, BindingResult valid) {
        e.printStackTrace();

        ExceptionContent exceptionContent = ExceptionContent.METHOD_ARGUMENT_NOT_VALID_EXCEPTION;

        String errorCode = String.valueOf(exceptionContent.getCode().value());
        String errorMessage = exceptionContent.getMessage();
        String errorDetail = valid.getFieldError().getField() + " - " + valid.getFieldError().getDefaultMessage();

        return ResponseEntity.status(exceptionContent.getCode())
                .body(buildExceptionResponse(errorCode, errorMessage, errorDetail));
    }

    /**
     * '요청 HTTP METHOD 예외' 처리
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ExceptionResponse> httpRequestMethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();

        ExceptionContent exceptionContent = ExceptionContent.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION;

        String errorCode = String.valueOf(exceptionContent.getCode().value());
        String errorMessage = exceptionContent.getMessage();
        String errorDetail = e.getMessage();

        return ResponseEntity.status(exceptionContent.getCode())
                .body(buildExceptionResponse(errorCode, errorMessage, errorDetail));
    }

    /**
     * '요청 HTTP 양식 예외' 처리
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionResponse> httpMessageNotReadableExceptionHandler(
            HttpMessageNotReadableException e) {
        e.printStackTrace();

        ExceptionContent exceptionContent = ExceptionContent.HTTP_MESSAGE_NOT_READ_ABLE_EXCEPTION;

        String errorCode = String.valueOf(exceptionContent.getCode().value());
        String errorMessage = exceptionContent.getMessage();
        String errorDetail = e.getMessage();

        return ResponseEntity.status(exceptionContent.getCode())
                .body(buildExceptionResponse(errorCode, errorMessage, errorDetail));
    }


    /**
     * '기타오류' 처리
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e) {
        e.printStackTrace();

        ExceptionContent exceptionContent = ExceptionContent.SERVER_EXCEPTION;

        String errorCode = String.valueOf(exceptionContent.getCode().value());
        String errorMessage = exceptionContent.getMessage();
        String errorDetail = e.getMessage();

        return ResponseEntity.status(exceptionContent.getCode())
                .body(buildExceptionResponse(errorCode, errorMessage, errorDetail));
    }



    // 예외핸들러의 body를 빌드
    public ExceptionResponse buildExceptionResponse(String errorCode, String errorMessage) {
        return new ExceptionResponse().builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
    }

    // 예외핸들러의 body를 빌드
    public ExceptionResponse buildExceptionResponse(String errorCode, String errorMessage, String errorDetail) {
        return new ExceptionResponse().builder()
                .code(errorCode)
                .message(errorMessage)
                .detail(errorDetail)
                .build();
    }
}
