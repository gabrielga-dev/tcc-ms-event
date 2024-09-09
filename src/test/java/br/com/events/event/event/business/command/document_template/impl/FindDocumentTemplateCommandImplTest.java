package br.com.events.event.event.business.command.document_template.impl;


import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.repository.DocumentTemplateRepository;
import br.com.events.event.event.core.exception.document_template.DocumentTemplateNotFoundException;
import br.com.events.event.event.data.model.DocumentTemplateMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindDocumentTemplateCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
public class FindDocumentTemplateCommandImplTest {

    @InjectMocks
    private FindDocumentTemplateCommandImpl command;

    @Mock
    private DocumentTemplateRepository documentTemplateRepository;

    @Test
    @DisplayName("execute - when template is not found, then throws DocumentTemplateNotFoundException")
    void executeWhenTemplateIsNotFoundThenThrowsDocumentTemplateNotFoundException() {
        when(documentTemplateRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                DocumentTemplateNotFoundException.class,
                () -> command.findByIdOrThrow(MockConstants.LONG)
        );

        verify(documentTemplateRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("execute - when template is found, then returns template")
    void executeWhenTemplateIsFoundThenReturnTemplate() {
        var template = DocumentTemplateMock.build();
        when(documentTemplateRepository.findById(anyLong())).thenReturn(Optional.of(template));

        var returned = command.findByIdOrThrow(MockConstants.LONG);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(template, returned);

        verify(documentTemplateRepository, times(1)).findById(anyLong());
    }
}
