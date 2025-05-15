package com.meca.auth.exception;

import com.meca.common.exception.CommonException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends CommonException {

    public UserException(UserErrorType errorType) {
        super(errorType.getCode(), errorType.getMessage(), errorType.getStatus());
    }

    @Getter
    public static class ErrorInfo {
        private final String code;
        private final String message;
        private final int status;

        public ErrorInfo(UserException ex) {
            this.code = ex.getCode();
            this.message = ex.getMessage();
            this.status = ex.getStatus().value();
        }
    }

    public enum UserErrorType {
        USER_NOT_FOUND("USER_001", HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
        PERMISSION_DENIED("USER_002", HttpStatus.FORBIDDEN, "사용자명은 중복될 수 없습니다."),
        DUPLICATED_USER("USER_003", HttpStatus.FORBIDDEN, "권한이 없습니다."),
        INTERNAL_SERVER_ERROR("USER_004", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
        DATABASE_ERROR("USER_005", HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 처리 중 오류가 발생했습니다."),
        AUTHENTICATION_FAILED("USER_006", HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),
        TOKEN_EXPIRED("USER_007", HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");

        private final String code;
        private final HttpStatus status;
        private final String message;

        UserErrorType(String code, HttpStatus status, String message) {
            this.code = code;
            this.status = status;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
