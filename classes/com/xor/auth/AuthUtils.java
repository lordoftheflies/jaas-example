package com.xor.auth;


import java.security.*;
import javax.security.auth.*;
import java.util.*;

/** 

*/

public class AuthUtils {
    // static class
    private AuthUtils() {}

    static public boolean permitted(Subject subj, final Permission p) {
        if ((p == null)) {
            System.err.println("subj or perm is null");
            return false;
        }
        if (subj == null) {
            subj = new Subject();
        }
        final SecurityManager sm;
	if (System.getSecurityManager() == null) {
	   sm  = new SecurityManager();
  	} else {
	   sm = System.getSecurityManager();
	}
        System.err.println("trying to auth "+subj+ " with permission "+p);
        try {
            Subject.doAsPrivileged(subj, new PrivilegedExceptionAction() {
                public Object run() { 
                    System.err.println("sm: "+sm);
                    sm.checkPermission(p);
                    return null;
                }
                },null);
            return true;
        } catch (AccessControlException ace) {
            System.err.println("exception caught: "+ace);
            return false;
        } catch (PrivilegedActionException pae) {
            if (pae.getException() instanceof SecurityException) {
                System.err.println("exception caught: "+pae);
            } else {
                System.err.println("what the hell is this: "+pae);
            }
            return false;
        }

    }
}

