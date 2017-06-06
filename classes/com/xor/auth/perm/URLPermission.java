package com.xor.auth.perm;

import java.security.*;

public final class URLPermission extends BasicPermission {

    public URLPermission(String name)
    {
	super(name);
    }

    // note that actions is ignored and not used,
    // but this constructor is still needed
    public URLPermission(String name, String actions) 
    {
	super(name, actions);
    }
}

