package com.meca.folder.exception;

import com.meca.common.exception.CommonException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FolderException extends CommonException {
    public FolderException(FolderErrorType errorType) {
        super(errorType.getCode(), errorType.getMessage(), errorType.getStatus());
    }

    public enum FolderErrorType {
        FOLDER_NOT_FOUND("FOLDER_001", HttpStatus.NOT_FOUND, "폴더를 찾을 수 없습니다.");

        private final String code;
        private final HttpStatus status;
        private final String message;

        FolderErrorType(String code, HttpStatus status, String message) {
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
