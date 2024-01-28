package com.franc.account.controller.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;


public class AccountGetDto {

    @Schema(name = "AccountGetRequest", description = "회원조회 Request")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Response {

        @Schema(description = "이름", example = "Kelly")
        private Long accountId;

        @Schema(description = "이름", example = "Kelly")
        private String accountName;

        @Schema(description = "생년월일(yyyyMMdd)", example = "20000101")
        private String birth;

        @Schema(description = "핸드폰번호(숫자만)", example = "01012341234")
        private String hphone;

        @Schema(description = "이메일주소", example = "kelly@naver.com")
        private String email;

        @Schema(description = "VIP여부", allowableValues = {"true", "false"})
        private boolean vipYn;

        @Schema(description = "가입일시", example = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private Date insertDate;

    }
}
