package br.com.events.event.event.application.config.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.events.event.event.util.helpers.MySecurityContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * This class clears all security context after every request
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
public class ClearSecurityInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Cleaning security context...");
        MySecurityContextHolder.clear();
    }
}