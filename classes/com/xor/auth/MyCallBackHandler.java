package com.xor.auth;

import javax.security.auth.callback.*;
import javax.servlet.http.*;

/** 
callback handler
*/

public class MyCallBackHandler implements CallbackHandler {
    public void handle(Callback[] callbacks) {
        for (int i = 0; i< callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback)callbacks[i];
                nc.setName(username);
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback)callbacks[i];
                pc.setPassword(password.toCharArray());
            }
        }
    }
    public MyCallBackHandler(HttpSession sess) {
        // yet to be implemented
    }

    private String username;
    private String password;

    public MyCallBackHandler(String username, String password) {
        this.username=username;
        this.password=password;
    }

}
