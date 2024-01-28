package com.franc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionContent {
    /* BizException */
    BIZ_ALREADY_EMAIL(HttpStatus.BAD_REQUEST, "이미 해당 이메일로 가입한 이력이 있습니다."),
    BIZ_ALREADY_HPHONE(HttpStatus.BAD_REQUEST, "이미 해당 휴대폰번호로 가입한 이력이 있습니다."),
    BIZ_ACCOUNT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 회원은 없는 회원입니다."),

    /* 4xx Exception */
    HTTP_MESSAGE_NOT_READ_ABLE_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청 양식입니다."),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청 양식입니다."),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청 양식입니다."),

    /* 5xx Exception */
    SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "오류가 발생했습니다. <br/>고객센터(1588-9999)로 문의주세요.");



    private final HttpStatus code;
    private final String message;
}
