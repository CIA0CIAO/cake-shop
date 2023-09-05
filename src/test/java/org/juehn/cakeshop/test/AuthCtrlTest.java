package org.juehn.cakeshop.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.juehn.cakeshop.controller.AuthCtrl;
import org.junit.Test;

import java.util.Map;

public class AuthCtrlTest {

    AuthCtrl authCtrl = new AuthCtrl();

    @Test
    public void login() throws JsonProcessingException {
        Map data = authCtrl.login("13487526134", "123456");
        System.out.println(data);
    }
}
