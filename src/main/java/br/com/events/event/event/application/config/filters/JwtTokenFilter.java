package br.com.events.event.event.application.config.filters;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.events.event.event.application.config.filters.exception.NoTokenReceivedException;
import br.com.events.event.event.infrastructure.feign.msAuth.PersonMsAuthFeignClient;
import br.com.events.event.event.util.FilteredRoutesUtil;
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
public class JwtTokenFilter extends OncePerRequestFilter {

    private final PersonMsAuthFeignClient personMsAuthFeignClient;

    private final FilteredRoutesUtil filteredRoutesUtil;

    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) {
        var path = request.getRequestURI();
        return filteredRoutesUtil.isRouteNotProtected(path);
    }

    @Override
    public void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        log.info("Filtering by jwt token");

        var token = extractToken(httpRequest);

        var person = Objects.requireNonNull(
            personMsAuthFeignClient.getAuthenticatedPersonInformation("Bearer " + token)
                .getBody()
        );

        log.info("Setting up security context: {}", person);
        MySecurityContextHolder.setContext(token, person);

        filterChain.doFilter(httpRequest, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            throw new NoTokenReceivedException();
        }
        return token.substring(7);
    }
}
