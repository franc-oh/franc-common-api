package com.franc.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExceptionResponse {

    @Schema(description = "에러 코드", allowableValues = {"4xx", "500"})
    private String code;

    @Schema(description = "에러 메시지", example = "잘못된 요청 양식입니다.")
    private String message;

    @Schema(description = "에러 상세메시지")
    private String detail;
}
