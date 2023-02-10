package br.com.events.event.event.application.config.filters;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.events.event.event.application.config.filters.exception.InvalidApiKeyException;
import br.com.events.event.event.application.config.filters.exception.NoApiKeyReceivedException;
import lombok.extern.slf4j.Slf4j;

/**
 * This class makes the request filtering so only consumers with allowed api-key can access this microservice's
 * features
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Order(1)
@Component
public class ApiKeyFilter implements Filter {

    @Value("${valid.api.keys}")
    private List<String> validApiKeys;

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
        throws IOException, ServletException {

        log.info("Filtering by api-key");

        var httpRequest = (HttpServletRequest) request;

        var apiKey = Optional.ofNullable(httpRequest.getHeader(apiKeyHeader))
            .orElseThrow(NoApiKeyReceivedException::new);

        if (!validApiKeys.contains(apiKey)) {
            throw new InvalidApiKeyException();
        }

        chain.doFilter(request, response);
    }
}
