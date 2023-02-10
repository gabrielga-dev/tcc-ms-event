package br.com.events.event.event.application.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;


public class MyEventFeignClientConfiguration {

    @Value("${api.key.header}")
    private String apiKeyHeaderName;

    @Value("${api.key.header.value}")
    private String apiKeyHeaderValue;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(apiKeyHeaderName, apiKeyHeaderValue);
    }
}
