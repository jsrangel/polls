package co.com.robin.feedback.domain.model.commons.exceptions;

import co.com.robin.feedback.domain.model.commons.enums.TechnicalExceptionEnum;
import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {

    private final TechnicalExceptionEnum typeTechnicalException;

    public TechnicalException(TechnicalExceptionEnum technicalExceptionEnum) {
        super(technicalExceptionEnum.getMessage());
        this.typeTechnicalException = technicalExceptionEnum;
    }

    public TechnicalException(Throwable error, TechnicalExceptionEnum technicalExceptionEnum) {
        super(error);
        this.typeTechnicalException = technicalExceptionEnum;
    }

}
