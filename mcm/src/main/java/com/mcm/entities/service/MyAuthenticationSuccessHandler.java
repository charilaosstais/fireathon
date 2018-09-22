package com.mcm.entities.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;  

@Service("authenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  
		  
	    @Override  
	    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,  
	                                        HttpServletResponse httpServletResponse,  
	                                        Authentication authentication)  
	            throws IOException, ServletException {  
	        //do some logic here if you want something to be done whenever  
	        //the user successfully logs in.  
	  
	        HttpSession session = httpServletRequest.getSession();  
	        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
	        session.setAttribute("username", authUser.getUsername());  
	        session.setAttribute("authorities", authentication.getAuthorities());  
	  

	        
	        //set our response to OK status  
	        httpServletResponse.setStatus(HttpServletResponse.SC_OK);  
	  
	        System.out.println("AuthenticationSuccessHandler !!!");
	        
	        String accheader = httpServletRequest.getHeader("Accept");
	        System.out.println(accheader);
	        
	        if (accheader.equals("application/json")){
	        	//since we have created our custom success handler, its up to us to where  
		        //we will redirect the user after successfully login  
		        
		        if(hasRole("ROLE_CUSTOMER", authentication)){
		        	httpServletResponse.sendRedirect("customer.json"); 
		        }else if(hasRole("ROLE_ADMIN", authentication)){
		        	httpServletResponse.sendRedirect("admin.json"); 
		        }else if(hasRole("ROLE_DEVELOPER", authentication)){
		        	httpServletResponse.sendRedirect("developer.json"); 
		        }else if(hasRole("ROLE_MANUFACTURER", authentication)){
		        	httpServletResponse.sendRedirect("manufacturer.json"); 
		        }
	        }else{
	        	//since we have created our custom success handler, its up to us to where  
		        //we will redirect the user after successfully login  
		        
		        if(hasRole("ROLE_CUSTOMER", authentication)){
		        	httpServletResponse.sendRedirect("customer"); 
		        }else if(hasRole("ROLE_ADMIN", authentication)){
		        	httpServletResponse.sendRedirect("admin"); 
		        }else if(hasRole("ROLE_DEVELOPER", authentication)){
		        	httpServletResponse.sendRedirect("developer"); 
		        }else if(hasRole("ROLE_MANUFACTURER", authentication)){
		        	httpServletResponse.sendRedirect("manufacturer"); 
		        }
	        }
	        
	        
	        
	    }  

	    private boolean hasRole(String role, Authentication authentication) {
	    	  boolean hasRole = false;
	    	  for (GrantedAuthority authority : authentication.getAuthorities()) {
	    	     hasRole = authority.getAuthority().equals(role);
	    	     if (hasRole) {
	    		  break;
	    	     }
	    	  }
	    	  return hasRole;
	    	}
	}  