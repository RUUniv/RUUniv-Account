package com.ruunivaccountserver.common.error.user;

import com.ruunivaccountserver.common.error.BusinessException;
import com.ruunivaccountserver.common.error.ErrorCode;

public class MaxApiKeyException extends BusinessException {
    public MaxApiKeyException() {
        super(ErrorCode.MAX_API_KEY);
    }
}
