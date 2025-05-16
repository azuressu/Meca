package com.meca.card.exception;

import com.meca.common.exception.CommonException;
import com.meca.folder.exception.FolderException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CardException extends CommonException {

    public CardException(CardException.CardErrorType errorType) {
        super(errorType.getCode(), errorType.getMessage(), errorType.getStatus());
    }

    public enum CardErrorType {
        CARD_NOT_FOUND("CARD_001", HttpStatus.NOT_FOUND, "카드를 찾을 수 없습니다."),
        PERMISSION_DENIED("CARD_002", HttpStatus.FORBIDDEN, "카드 수정 권한이 없습니다.");

        private final String code;
        private final HttpStatus status;
        private final String message;

        CardErrorType(String code, HttpStatus status, String message) {
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
