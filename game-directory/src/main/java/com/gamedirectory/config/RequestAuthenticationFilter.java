package com.gamedirectory.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAuthenticationFilter extends OncePerRequestFilter {

    private static final String Auth_Header = "Authorization";

    public RequestAuthenticationFilter(){

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if (this.CheckIfHeaderExists(request)) {
                String authValue = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getEnvironment().getProperty("client_auth");
                if (ObjectUtils.isEmpty(authValue)) {
                    response.sendError(500, "Internal Server Error");
                    return;
                }

                boolean authVerify = authValue.equals(request.getHeader("Authorization"));
                if (authVerify && authValue.trim().isEmpty()) {
                    response.sendError(500, "Internal Server Error");
                    return;
                }

                if (!authVerify) {
                    response.sendError(500, "UnAuthorized");
                    return;
                }
                SecurityContextHolder.getContext().setAuthentication(new CustomTokenAuthentication(true));
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (Exception var6) {
            response.sendError(500, "Internal server error");
        }

    }


    private boolean CheckIfHeaderExists(HttpServletRequest request){
        String authenticationHeader = request.getHeader("Authorization");
        return authenticationHeader != null;
    }
}
