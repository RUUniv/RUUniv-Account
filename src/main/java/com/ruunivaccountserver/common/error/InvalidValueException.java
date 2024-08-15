package com.ruunivaccountserver.common.error;

public class InvalidValueException extends BusinessException {
    
    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
