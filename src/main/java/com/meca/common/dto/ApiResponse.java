package com.meca.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;
    private T data;
    private Integer code;
    private String message;

    /**
     * 성공 응답
     *
     * @param status 응답 상태
     * @param data   응답 데이터
     */
    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    /**
     * 오류 응답
     *
     * @param status  응답 상태
     * @param code    오류 코드
     * @param message 오류 메시지
     */
    public ApiResponse(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
