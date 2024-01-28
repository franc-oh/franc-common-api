package com.franc.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 공통 Response - [디폴트]
 */

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DefaultResponse {
    @Schema(description = "응답코드", example = "0")
    @Builder.Default
    private String code = CommonVar.API_SUCCESS_CODE;

    @Schema(description = "응답메시지", example = "정상")
    @Builder.Default
    private String message = CommonVar.API_SUCCESS_MSG;
}
