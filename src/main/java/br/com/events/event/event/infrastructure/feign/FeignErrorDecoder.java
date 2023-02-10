package br.com.events.event.event.infrastructure.feign;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.events.event.event.infrastructure.exception.BusinessException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * This class decodes every error that came from feign clients
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            log.error("Error at {} {}", response.request().httpMethod(), response.request().url());

            var objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(bodyIs, BusinessException.class);
        } catch (IOException e) {
            log.error(
                "Error mapping feign error at {} {}. {}",
                response.request().httpMethod(),
                response.request().url(),
                e.getMessage()
            );

            return new BusinessException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro ao tentar ler mensagemd e falha dos servidores.",
                "A chamada a um dos nossos servidores falhou e o motivo ainda não foi mapeado."
            );
        }
    }
}
