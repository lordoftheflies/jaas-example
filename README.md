    ASSUMPTIONS
<javahome> is assumed to be something like
/usr/local/java/jre
and 
<tomcathome> is assumed to be something like
/usr/local/tomcat4/ 

this doc assumes tomcat 4.  tomcat3.3.x is not that different.
modify these as you like.

Also, we output to stderr (it's only an example) which you can find in
<tomcathome>/logs/catalina.out

    STEPS

0. get the jaas-example.tar file, which you have otherwise you wouldn't
have these instructions, untar it.

1. install tomcat 

2. download struts, unzip somewhere

3. copy <src>/webapps/struts-example.war to tomcat/webapps

4. start tomcat

5. view localhost:8080/struts-example

6. login with user/pass

7. stop tomcat.

8. edit java.security, located at <javahome>/lib/security/java.security
and add these lines:

#################################################################
#
# Java Authentication and Authorization Service (JAAS) properties
#
login.configuration.provider=com.sun.security.auth.login.ConfigFile
login.config.url.1=file:${java.home}/lib/security/tagish.login

auth.policy.provider=com.sun.security.auth.PolicyFile
auth.policy.url.1=file:${java.home}/lib/security/struts.policy

9. copy the contents of the config subdirectory of the jaas-example
directory to <javahome>/lib/security/

10. edit <tomcathome>/webapps/struts-example/WEB-INF/web.xml

and change these lines:
  <!-- Action Servlet Configuration -->
  <servlet>
      <servlet-name>action</servlet-name>
      <servlet-class>org.apache.struts.action.ActionServlet</servlet-class>

to these:
  <!-- Action Servlet Configuration -->
  <servlet>
      <servlet-name>action</servlet-name>
      <servlet-class>com.xor.auth.ActionServlet</servlet-class>

11. copy the contents of the jars subdirectory of the jaas-example
directory to <javahome>/lib/ext/

12. copy the contents of the classes sub directory of the jaas-examples
directory to <tomcathome>/webapps/struts-example/WEB-INF/:
cp -pr classes/ <tomcathome>/webapps/struts-example/WEB-INF/

13. restart tomcat

14. view localhost:8080/struts-example

try to login with user/pass--you can't

we are now authing against the passwd file you installed in step 9.

try to login with user/test2--you can

15. click on "Edit your user registration profile"

you should be able to go there

Note: if you cannot, check the case of the url--case matters.

16. edit <javahome>/lib/security/struts.policy, comment out this line:

    permission com.xor.auth.perm.URLPermission "/struts-example/editRegistration.do";

by placing // in front of it

17. restart tomcat

repeat steps 14 and 15, you shouldn't be able to view editRegistration
(blank screen and message in catalina.out).

Thanks,
Dan Moore <dan.moore@seurat.com>
