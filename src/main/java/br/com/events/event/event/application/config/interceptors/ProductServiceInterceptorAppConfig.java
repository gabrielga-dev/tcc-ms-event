package br.com.events.event.event.application.config.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.RequiredArgsConstructor;

/**
 * This class set the interceptors for all requests
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ProductServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {

    private final ClearSecurityInterceptor clearSecurityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clearSecurityInterceptor);
    }
}
