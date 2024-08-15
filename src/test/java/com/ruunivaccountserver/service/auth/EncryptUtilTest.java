package com.ruunivaccountserver.service.auth;

import com.ruunivaccountserver.app.auth.service.EncryptUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EncryptUtilTest {

    EncryptUtil encryptUtil = new EncryptUtil();

    @Test
    @DisplayName("Encrypt Password Test")
    void encryptPasswordTest(){
        String password = "test";

        String encryptPassword = encryptUtil.encryptPassword(password);

        Assertions.assertEquals(encryptPassword,encryptUtil.encryptPassword(password));
    }
}
