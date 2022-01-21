package co.com.robin.feedback.domain.model.commons.exceptions;

import co.com.robin.feedback.domain.model.commons.enums.TechnicalExceptionEnum;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TechnicalExceptionTest {

    @Test
    public void exceptionStructureTest() {
        TechnicalExceptionEnum exception = TechnicalExceptionEnum.TECHNICAL_REQUEST_ERROR;
        TechnicalException appExc = new TechnicalException(exception);
        assertThat(appExc).isNotNull();
        assertThat(appExc.getTypeTechnicalException().getMessage()).isEqualTo(exception.getMessage());
        assertThat(appExc.getTypeTechnicalException().getCode()).isEqualTo(exception.getCode());
    }

    @Test
    public void exceptionStructureTest2() {
        TechnicalExceptionEnum exception = TechnicalExceptionEnum.TECHNICAL_SERVER_ERROR;
        TechnicalException appExc = new TechnicalException(new Throwable(), exception);
        assertThat(appExc).isNotNull();
        assertThat(appExc.getTypeTechnicalException().getMessage()).isEqualTo(exception.getMessage());
        assertThat(appExc.getTypeTechnicalException().getCode()).isEqualTo(exception.getCode());
    }
}