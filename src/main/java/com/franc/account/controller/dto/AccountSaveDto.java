package com.franc.account.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


public class AccountSaveDto {

    @Schema(name = "AccountSaveRequest", description = "회원가입 Request")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request {

        @Schema(description = "이름", example = "Kelly")
        @NotBlank
        private String accountName;

        @Schema(description = "생년월일(yyyyMMdd)", example = "20000101")
        @NotBlank
        @Pattern(regexp = "^[\\d]*$")
        @Size(min = 8, max = 8)
        private String birth;

        @Schema(description = "핸드폰번호(숫자만)", example = "01012341234")
        @NotBlank
        @Pattern(regexp = "^[\\d]*$")
        @Size(min = 10, max = 11)
        private String hphone;

        @Schema(description = "이메일주소", example = "kelly@naver.com")
        @NotBlank
        @Email
        private String email;

    }
}
