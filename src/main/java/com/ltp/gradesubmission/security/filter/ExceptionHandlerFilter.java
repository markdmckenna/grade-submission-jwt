package com.ltp.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ltp.gradesubmission.exception.EntityNotFoundException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            LOGGER.info("ExceptionHandlerFilter - filterChain.doFilter");
            filterChain.doFilter(request, response);           
        } catch (EntityNotFoundException e) {
            LOGGER.info("ExceptionHandlerFilter - EntityNotFoundException: {}", e.getStackTrace());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Username doesn't exist");
            response.getWriter().flush();
        } catch (RuntimeException e) {
            LOGGER.info("ExceptionHandlerFilter - RuntimeException: {}", e.getStackTrace());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } 

    }

}
