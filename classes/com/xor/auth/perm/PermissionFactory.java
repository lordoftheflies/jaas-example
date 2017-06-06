package com.xor.auth.perm;

import java.security.*;

public final class PermissionFactory {
    
    private static PermissionFactory factory = null;
    private PermissionFactory() {}

    public static PermissionFactory getInstance() {
        if (factory == null) {
            factory = new PermissionFactory(); 
        } 
        return factory;
    }

    public Permission getPermission(String url) {
        return new URLPermission(url);
    }
}

