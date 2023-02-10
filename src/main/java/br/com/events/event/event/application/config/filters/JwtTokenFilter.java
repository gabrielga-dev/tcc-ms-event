package br.com.events.event.event.application.config.filters;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;

import br.com.events.event.event.application.config.filters.exception.NoTokenReceivedException;
import br.com.events.event.event.infrastructure.feign.msAuth.PersonMsAuthFeignClient;
import br.com.events.event.event.util.helpers.MySecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class intercept every request and check if there is a Authorization header containing a JWT and validate it
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Order(2)
@Configuration
@RequiredArgsConstructor
public class JwtTokenFilter implements Filter {

    private final PersonMsAuthFeignClient personMsAuthFeignClient;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
        throws IOException, ServletException {

        log.info("Filtering by jwt token");

        var httpRequest = (HttpServletRequest) request;

        var token = extractToken(httpRequest);

        var person = Objects.requireNonNull(
            personMsAuthFeignClient.getAuthenticatedPersonInformation("Bearer " + token)
                .getBody()
        );

        log.info("Setting up security context: {}", person);
        MySecurityContextHolder.setContext(token, person);

        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            throw new NoTokenReceivedException();
        }
        return token.substring(7);
    }
}
