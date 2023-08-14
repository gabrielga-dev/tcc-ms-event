package br.com.events.event.event.process;

/**
 * This interface will be implemented by classes to execute a series of micro process that will be part of a
 * bigger chain
 *
 * @param <T> the class that will be passed as parameter
 * @param <R> the class that will be returned
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseProcessCaller<T, R> {

    R callProcesses(T param);
}
