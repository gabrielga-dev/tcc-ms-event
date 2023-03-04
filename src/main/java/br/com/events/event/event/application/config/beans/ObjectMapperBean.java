package br.com.events.event.event.application.config.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

/**
 * This class creates a bean object of {@link ObjectMapper}
 *
 * @author Gabriel Guimarães de Almeida
 */
@Configuration
public class ObjectMapperBean {

    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
