package com.timespace.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class LogonSuccessHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request,   HttpServletResponse response, Authentication authentication) throws IOException  {
       Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
       if (roles.contains("ROLE_HR")) {
           response.sendRedirect("humanresource/listemployee");
       }
	 if (roles.contains("ROLE_EMPLOYEE")) {
	    	   
	           response.sendRedirect("/");
	       }
   }
}