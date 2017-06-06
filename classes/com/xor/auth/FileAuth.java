package com.xor.auth;


import javax.security.auth.login.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import java.io.*;

/** 
adapter class for tagish auth module
*/

public class FileAuth implements Auth{
    private String username;
    private String password;
    private LoginContext lc = null;
    public FileAuth(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public boolean authenticate() {
        try {
            lc = new LoginContext("FileLogin", new MyCallBackHandler(username,password));
            lc.login();
        } catch (LoginException le) {
            return false;
        }
        return true;
    }
    public Subject getSubject() {
        if (lc == null) {
            // either login failed or the authenticate method hasn't been
            // called.
            throw new IllegalStateException("either login failed or the authenticate method hasn't been called.");
        } else {
            try {
            ObjectOutputStream oos = new ObjectOutputStream(new
            FileOutputStream("/home/moore/subject.ser"));           
            oos.writeObject(lc.getSubject());
            } catch (IOException ioe) { 
                System.err.println("err on ser "+ioe);
            }

            return lc.getSubject();
        }

    }

}
