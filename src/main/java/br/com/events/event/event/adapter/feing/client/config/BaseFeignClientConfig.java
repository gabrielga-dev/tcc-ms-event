package br.com.events.event.event.adapter.feing.client.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * This class sets the logging level of all feign clients
 *
 * @author Gabriel Guimarães de Almeida
 */
public class BaseFeignClientConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
