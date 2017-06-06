package com.xor.auth;


//import org.apache.struts.action.*;
import java.security.*;
import javax.security.auth.Subject;
import javax.servlet.http.*;
import com.xor.auth.Auth;
import com.xor.auth.perm.*;
import javax.servlet.ServletException;

/** 
overrides process method to implement acl
*/

public class ActionServlet extends org.apache.struts.action.ActionServlet {

  protected void process(HttpServletRequest request, 
    HttpServletResponse response)
  throws ServletException, java.io.IOException
  {
      String loginPage = request.getContextPath()+"/logon.do";
      System.err.println("loginPage: "+loginPage);
      String pageReq = request.getRequestURI();
      System.err.println("requestedPage: "+pageReq);
      Permission perm = PermissionFactory.getInstance().getPermission(pageReq);
      Subject subject = ((Subject)(request.getSession().getAttribute(  Auth.SUBJECT_SESSION_KEY)));
      if (subject == null && (! request.getRequestURI().equals(loginPage))) {
          // redirect to login page
      } else if (subject == null &&  request.getRequestURI().equals(loginPage)) {
          // login page is always permitted
           super.process(request,response);

      } else {
            if ( ! AuthUtils.permitted(subject, perm) ) {
	           // subject is not permitted; redirect to error page
	    } else {
               super.process(request,response);
           }
       }
  }

    protected void loginRedirect(HttpServletResponse res, String url) {
        try {
            res.sendRedirect(url);
        } catch (java.io.IOException ioe) {
            //ignore
        }
    }
}
