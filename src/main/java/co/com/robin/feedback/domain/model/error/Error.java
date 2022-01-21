package co.com.robin.feedback.domain.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Error {

    private List<Data> errors;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class Data {
        private String reason;
        private String domain;
        private String code;
        private String message;
    }

}
