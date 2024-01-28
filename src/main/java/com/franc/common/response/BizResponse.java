package com.franc.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 공통 Resonse - [실제 Response]
 */
@Getter
@ToString(callSuper = true)
public class BizResponse<T> extends DefaultResponse {

    @JsonIgnore
    private ObjectMapper objectMapper;

    @Schema(description = "결과데이터")
    private Map<String, Object> data;



    public BizResponse() {
        this.data = new HashMap<>();
    }

    public BizResponse(T data) {
        this();
        this.data = objectMapper.convertValue(data, new TypeReference<>(){});
    }


    /**
     * Map 요소 추가 - Map.put(Key, Object)
     * @param key
     * @param data
     */
    public void addObject(String key, T data) {
        this.data.put(key, data);
    }

    /**
     * Map 요소 추가 - Map.put(Key, List)
     * @param key
     * @param data
     */
    public void addList(String key, List<T> data) {
        this.data.put(key, data);
    }

    /**
     * Map에 객체 밀어넣기
     * @param data
     */
    public void merge(T data) {
        this.data.putAll(objectMapper.convertValue(data, new TypeReference<Map<String, Object>>(){}));
    }

    /**
     * 현 데이터로 Response 객체 반환
     * @param httpStatus
     * @return
     */
    public ResponseEntity<BizResponse<?>> getResponse(HttpStatus httpStatus) {
        if (this.data.isEmpty())
            this.data = null;

        return new ResponseEntity<>(this, httpStatus);
    }

}
