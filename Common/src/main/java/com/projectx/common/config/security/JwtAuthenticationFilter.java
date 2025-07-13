package com.projectx.common.config.security;

import com.projectx.common.utils.AuthUtil;
import com.projectx.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
// OncePerRequestFilter is used to filter requests once and only once
public class JwtAuthenticationFilter extends OncePerRequestFilter { 

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserService userService;

    @Override
    // doFilterInternal is used to filter requests and execute for every request
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // Bearer token is 7 characters long
            username = authUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Find user by username
            var userOpt = userService.getUserRepository().findByUsername(username);
            // Check if user is present (control is optional has user object) and token is not expired 
            if (userOpt.isPresent() && !authUtil.isTokenExpired(jwt)) {
                // Create authentication token with username and null password and null authorities 
                // We don't need password and authorities because we are using JWT 
                // we can add roles to there but should we ? 
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, null);
                // Set details to authentication token to get more information about the request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Set authentication token to security context holder to use it in other parts of the application
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); // Continue filter chain
    }
} 