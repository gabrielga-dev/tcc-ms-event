package br.com.events.event.event.business.command.document_template.impl;


import br.com.events.event.event.core.config.Base64ImageProvider;
import br.com.events.event.event.core.exception.document_template.CouldNotGenerateDocumentException;
import br.com.events.event.event.data.io.inbound.document_template.PdfConfigurationDTO;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

/**
 * Tests for {@link GeneratePdfDocumentCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
public class GeneratePdfDocumentCommandImplTest {

    private final GeneratePdfDocumentCommandImpl command = new GeneratePdfDocumentCommandImpl(
            new Base64ImageProvider(),
            XMLWorkerHelper.getInstance().getDefaultCssResolver(true)
    );

    @Test
    @DisplayName("execute - when no exception is thrown, then return byteArray")
    void executeWhenNoExceptionIsThrownThenReturnByteArray() {
        var configuration = new PdfConfigurationDTO(
                "<html><head></head><body>Test</body></html>"
        );
        var returned = command.execute(configuration, new HashMap<>());

        Assertions.assertNotNull(returned);
        Assertions.assertTrue(returned.length > 0);
    }

    @Test
    @DisplayName("execute - when any exception is thrown, then throws CouldNotGenerateDocumentException")
    void executeWhenAnyExceptionIsThrownThenThrowsCouldNotGenerateDocumentException() {
        var configuration = new PdfConfigurationDTO(
                "<html><head></head><body>Test</body></html>"
        );

        Assertions.assertThrows(
                CouldNotGenerateDocumentException.class,
                () -> command.execute(configuration, null)
        );
    }
}
