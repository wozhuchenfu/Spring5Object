package com.qi.exceptionUtils;
import lombok.Data;

@Data
public class ObjectException extends RuntimeException {
    private BizError errorCode;
    private String errorMessage;

    public ObjectException(BizError errorCode) {
        super(errorCode.getErrorDesc());
        this.errorCode = errorCode;
    }

    public BizError getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(BizError errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
