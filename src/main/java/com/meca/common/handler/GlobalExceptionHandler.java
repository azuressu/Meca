package com.meca.common.handler;

import com.meca.common.dto.ApiResponse;
import com.meca.common.exception.CommonException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String TRACE_ID = "traceId";

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ApiResponse<CommonException>> handleUserException(
            CommonException exception, HttpServletRequest request) {
        String traceId = MDC.get(TRACE_ID);

        log.warn("[{}] CustomException 발생 : URI={}, Method={}, ErrorCode={}, ErrorMessage={}",
                traceId, request.getRequestURI(), request.getMethod(),
                exception.getErrorCode(), exception.getMessage());

        return ResponseEntity.status(exception.getStatus()).body(new ApiResponse(
                exception.getErrorCode(), exception.getStatus().value(), exception.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(
            Exception exception, HttpServletRequest request) {
        String traceId = MDC.get(TRACE_ID);

        log.error("[{}] 예상하지 못한 Exception 발생 : URI={}, Method={}, ErrorMessage={}",
                traceId, request.getRequestURI(), request.getMethod(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
                "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()
        ));
    }
}