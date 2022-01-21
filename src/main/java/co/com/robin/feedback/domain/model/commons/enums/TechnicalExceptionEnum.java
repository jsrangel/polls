package co.com.robin.feedback.domain.model.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalExceptionEnum {

    TECHNICAL_SERVER_ERROR("FP0001", "Internal server error"),
    TECHNICAL_REST_CLIENT_ERROR("FP0002", "An error has occurred in the Rest Client"),
    TECHNICAL_HEADER_MISSING("FP0003", "Missing parameters per header"),
    TECHNICAL_REQUEST_ERROR("FP0004", "There is an error in the request body"),
    TECHNICAL_INVALID_VALUE_PARAMS("FP0005", "Invalid value parameter");

    private final String code;
    private final String message;

}
