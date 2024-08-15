package com.ruunivaccountserver.common.error.user;

import com.ruunivaccountserver.common.error.BusinessException;
import com.ruunivaccountserver.common.error.ErrorCode;

public class UserAccessDeniedException extends BusinessException {
    public UserAccessDeniedException() {
        super(ErrorCode.USER_ACCESS_DENIED);
    }
}
