package com.franc.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 공통 Resonse - [실제 Response]
 */
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class BizResponse<T> extends DefaultResponse {

    @Schema(description = "결과데이터")
    private T data;


    public BizResponse(T data) {
        this.data = data;
    }


    public void put(T data) {
        this.data = data;
    }

    /**
     * 현 데이터로 Response 객체 반환
     * @param httpStatus
     * @return
     */
    public ResponseEntity<BizResponse<?>> getResponse(HttpStatus httpStatus) {
        return new ResponseEntity<>(this, httpStatus);
    }

}
