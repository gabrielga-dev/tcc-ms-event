package br.com.events.event.event.application.config.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


public class MyEventFeignClientConfiguration extends BaseFeignClientConfig{

    @Value("${api.key.header}")
    private String apiKeyHeaderName;

    @Value("${api.key.header.value}")
    private String apiKeyHeaderValue;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(apiKeyHeaderName, apiKeyHeaderValue);
    }
}
