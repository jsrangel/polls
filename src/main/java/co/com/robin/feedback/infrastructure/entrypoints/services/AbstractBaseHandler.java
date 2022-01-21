package co.com.robin.feedback.infrastructure.entrypoints.services;

import co.com.robin.feedback.domain.model.commons.exceptions.TechnicalException;
import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.request.Request;
import co.com.robin.feedback.infrastructure.entrypoints.commons.util.ParamsUtil;
import co.com.robin.feedback.infrastructure.entrypoints.commons.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

import static co.com.robin.feedback.domain.model.commons.enums.TechnicalExceptionEnum.TECHNICAL_REQUEST_ERROR;

@RequiredArgsConstructor
public class AbstractBaseHandler {

    protected Mono<ServerResponse> getResponse(
            ServerRequest serverRequest, Function<Transaction, Mono<Transaction>> useCase) {
        return getRequestData(serverRequest)
                .flatMap(transaction -> useCaseApply(useCase, transaction))
                .switchIfEmpty(Mono.error(new TechnicalException(TECHNICAL_REQUEST_ERROR)));
    }

    private Mono<Transaction> getRequestData(ServerRequest serverRequest) {
        return Mono.zip(getHeaders(serverRequest), serverRequest.bodyToMono(Request.class))
                .map(tuples -> Transaction.builder()
                        .headers(tuples.getT1())
                        .pathParams(serverRequest.pathVariables())
                        .queryParams(serverRequest.queryParams())
                        .request(tuples.getT2())
                        .route(serverRequest.uri().toString())
                        .build());
    }

    private Mono<Map<String, String>> getHeaders(ServerRequest request) {
        return ParamsUtil.entrySetToMap(request.headers().asHttpHeaders().entrySet());
    }

    private Mono<ServerResponse> useCaseApply
            (Function<Transaction, Mono<Transaction>> useCase, Transaction transaction) {
        return useCase.apply(transaction)
                .flatMap(ResponseUtil::response);
    }

}
