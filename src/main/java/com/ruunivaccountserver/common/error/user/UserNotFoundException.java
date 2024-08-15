package com.ruunivaccountserver.common.error.user;

import com.ruunivaccountserver.common.error.EntityNotFoundException;
import com.ruunivaccountserver.common.error.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
