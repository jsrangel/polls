package co.com.robin.feedback.infrastructure.drivenadapters.dto;

import co.com.robin.feedback.domain.model.commons.enums.TechnicalExceptionEnum;
import co.com.robin.feedback.domain.model.error.Error;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private List<DataDTO> errors;

    public Mono<Error> toModel() {
        return Mono.just(Error.builder()
                .errors(List.of(Error.Data.builder()
                        .code(this.getErrors().get(0).getCode())
                        .domain(this.getErrors().get(0).getHref())
                        .message(this.getErrors().get(0).getTitle())
                        .reason(this.getErrors().get(0).getDetail())
                        .build()))
                .build());
    }

    public ErrorDTO evaluateErrors(String serviceRoute) {
        if (errors == null) {
            this.errors = List.of(DataDTO.builder()
                    .href(serviceRoute)
                    .build());
        }
        return this;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDTO {

        @Builder.Default
        private String title = TechnicalExceptionEnum.TECHNICAL_REST_CLIENT_ERROR.getMessage();

        private String href;

        @Builder.Default
        private String code = TechnicalExceptionEnum.TECHNICAL_REST_CLIENT_ERROR.getCode();

        @Builder.Default
        private String detail = TechnicalExceptionEnum.TECHNICAL_REST_CLIENT_ERROR.getMessage();

    }

}