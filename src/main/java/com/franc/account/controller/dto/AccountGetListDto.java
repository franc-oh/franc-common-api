package com.franc.account.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;
import java.util.List;


public class AccountGetListDto {

    @Schema(name = "AccountGetListResponse", description = "회원목록조회 Response")
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Response {

        @Schema(description = "데이터 건 수", example = "1")
        private Integer count;

        @Schema(description = "회원목록", type = "array")
        private List<AccountData> list;

    }


    @Schema(name = "AccountGetListResponseListData")
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class AccountData {

        @Schema(description = "회원번호", example = "1")
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

        @Schema(description = "가입일시", example = "2024-01-28 10:05:40", type = "string")
        private Date insertDate;

    }
}
