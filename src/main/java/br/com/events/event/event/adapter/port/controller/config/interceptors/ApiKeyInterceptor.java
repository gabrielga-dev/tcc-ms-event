package br.com.events.event.event.adapter.port.controller.config.interceptors;

import br.com.events.event.event.core.exception.interceptors.InvalidApiKeyException;
import br.com.events.event.event.core.exception.interceptors.NoApiKeyReceivedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * This class makes the request filtering so only consumers with allowed api-key can access this microservice's
 * features
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${valid.api.keys}")
    private List<String> validApiKeys;

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Override
    public boolean preHandle(
            final HttpServletRequest request, final HttpServletResponse response, final Object handler
    ) throws Exception {
        var apiKey = Optional.ofNullable(request.getHeader(apiKeyHeader))
                .orElseThrow(NoApiKeyReceivedException::new);
        if (validApiKeys.contains(apiKey)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        throw new InvalidApiKeyException();
    }
}
