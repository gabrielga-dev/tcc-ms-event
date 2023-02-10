package br.com.events.event.event.infrastructure.validation;

/**
 * This interface dictates which methods are needed to implement when a validator is needed
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseValidator<T> {

    void validate(T toValidate);
}
