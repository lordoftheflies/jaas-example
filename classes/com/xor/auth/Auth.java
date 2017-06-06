package com.xor.auth;


import javax.security.auth.login.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;

/** 

*/

public interface Auth {
    public boolean authenticate();
    public Subject getSubject();

    public final static String SUBJECT_SESSION_KEY = "subject_key";
}
