package com.ruunivaccountserver.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //Common
    INVALID_INPUT_VALUE("C01", "Invalid Input Value.", HttpStatus.BAD_REQUEST.value()),
    METHOD_NOT_ALLOWED("C02", "Invalid Method Type.", HttpStatus.METHOD_NOT_ALLOWED.value()),
    ENTITY_NOT_FOUND("C03", "Entity Not Found.", HttpStatus.NOT_FOUND.value()),
    INTERNAL_SERVER_ERROR("C04", "Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    FILE_NOT_UPLOAD("C05", "Internal Server Error.", HttpStatus.BAD_REQUEST.value()),

    //User
    USER_ACCESS_DENIED("U01", "User Access is Denied.", HttpStatus.UNAUTHORIZED.value()),
    USER_NOT_FOUND("U02", "User is not Found.", HttpStatus.BAD_REQUEST.value()),
    MAX_API_KEY("U03", "Max Api Key.", HttpStatus.BAD_REQUEST.value()),

    //Auth
    NOT_VERIFIED_EMAIL("A01", "Mail is not Verified", HttpStatus.BAD_REQUEST.value()),
    AUTH_CODE_NOT_FOUND("A02", "AuthCode is not Found", HttpStatus.BAD_REQUEST.value()),
    INVALID_AUTH_CODE("A03", "AuthCode is Invalid", HttpStatus.BAD_REQUEST.value()),
    INVALID_TOKEN("A04", "Token is Invalid", HttpStatus.BAD_REQUEST.value());

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
