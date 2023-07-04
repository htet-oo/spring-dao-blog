package springblog.bl.services.auth;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	String account = authentication.getName();
    	request.getSession().setAttribute("account", account);
    	System.out.println("this is account " + account);
    	
        if (authorities.contains(new SimpleGrantedAuthority("Admin"))) {
            response.sendRedirect("users/list");
        } else if (authorities.contains(new SimpleGrantedAuthority("User"))) {
        	response.sendRedirect("posts/list");
        }
    }
}
