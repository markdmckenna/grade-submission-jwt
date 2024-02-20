package com.ltp.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    //         throws IOException, ServletException {
    //     chain.doFilter(request, response);
    // }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            LOGGER.info(user.getUsername());
            LOGGER.info(user.getPassword());
        } catch (IOException e) {
            LOGGER.info("IOException: {}", e.getStackTrace());
            throw new RuntimeException();
        }
        return super.attemptAuthentication(request, response);
    }

}
