package br.com.events.event.event.application.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import br.com.events.event.event.infrastructure.feign.CountryStateCityFeignClient;
import feign.RequestInterceptor;

/**
 * This class makes all needed configuration for {@link CountryStateCityFeignClient} feign client
 *
 * @author Gabriel Guimarães de Almeida
 */
public class CountryStateCityFeignClientConfiguration {

    @Value("${feign.client.country.state.city.header.name}")
    private String headerName;

    @Value("${feign.client.country.state.city.header.value}")
    private String headerValue;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(headerName, headerValue);
    }
}
