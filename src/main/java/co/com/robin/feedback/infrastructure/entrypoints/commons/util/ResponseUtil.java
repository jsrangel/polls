package co.com.robin.feedback.infrastructure.entrypoints.commons.util;

import co.com.robin.feedback.domain.model.error.Error;
import co.com.robin.feedback.domain.model.transaction.Transaction;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@UtilityClass
public class ResponseUtil {

    public static Mono<ServerResponse> response(Transaction transaction) {
        return Mono.just(transaction.getStatusCode())
                .map(HttpStatus::valueOf)
                .flatMap(httpStatus -> buildResponse(httpStatus, transaction));
    }

    public static <T> Mono<ServerResponse> responseFail(T body){
        return buildResponse(INTERNAL_SERVER_ERROR, body);
    }

    public static <T> Mono<ServerResponse> buildResponse(HttpStatus status, T body) {
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(body);
    }

}
