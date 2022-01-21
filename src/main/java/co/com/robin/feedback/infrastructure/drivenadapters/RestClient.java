package co.com.robin.feedback.infrastructure.drivenadapters;

import co.com.robin.feedback.domain.model.commons.enums.TechnicalExceptionEnum;
import co.com.robin.feedback.domain.model.commons.exceptions.TechnicalException;
import co.com.robin.feedback.domain.model.error.Error;
import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.request.Request;
import co.com.robin.feedback.domain.model.transaction.response.Response;
import co.com.robin.feedback.infrastructure.drivenadapters.dto.ErrorDTO;
import co.com.robin.feedback.infrastructure.drivenadapters.dto.RequestDTO;
import co.com.robin.feedback.infrastructure.drivenadapters.config.ClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

import static reactor.core.publisher.Mono.just;

@Component
@RequiredArgsConstructor
public class RestClient {

    private final WebClient webClient;
    private final ClientProperties clientProperties;

    public Mono<Transaction> post(String route, Transaction transaction) {
        return clientProperties.filterHeaders(transaction.getHeaders())
                .flatMap(headers -> webClient.post()
                        .uri(uriBuilder(route, transaction))
                        .headers(head -> head.setAll(headers))
                        .bodyValue(assignRequest(transaction.getRequest()))
                        .exchangeToMono(clientResponse -> assignResponse(clientResponse, transaction))
                        .onErrorMap(ex -> new TechnicalException(ex, TechnicalExceptionEnum.TECHNICAL_REST_CLIENT_ERROR)));
    }

    private Function<UriBuilder, URI> uriBuilder(String route, Transaction transaction) {
        return uriBuilder -> uriBuilder.path(route)
                .queryParams((MultiValueMap<String, String>) transaction.getQueryParams())
                .build(transaction.getPathParams());
    }

    private RequestDTO assignRequest(Request request) {
        return RequestDTO.builder().data(List.of(request)).build();
    }

    private Mono<Transaction> assignResponse(ClientResponse clientResponse, Transaction transaction) {
        return Mono.just(clientResponse.statusCode())
                .filter(HttpStatus::is2xxSuccessful)
                .flatMap(s -> clientResponse.bodyToMono(Response.class))
                .flatMap(responseSuccess -> toComplete(clientResponse, transaction, responseSuccess))
                .switchIfEmpty(assignError(clientResponse, transaction));
    }

    private Mono<Transaction> toComplete(ClientResponse clientResponse, Transaction transaction, Object response) {
        return just(clientResponse.statusCode())
                .map(status -> {
                    transaction.setStatus(status.is2xxSuccessful());
                    transaction.setStatusCode(status.value());
                    transaction.setResponse(response);
                    return transaction;
                });
    }

    private Mono<Transaction> assignError(ClientResponse cr, Transaction transaction) {
        return cr.bodyToMono(ErrorDTO.class)
                .flatMap(responseErrorDTO -> toComplete(cr, transaction, responseErrorDTO))
                .flatMap(this::errorDtoToModel);
    }

    private Mono<Transaction> errorDtoToModel(Transaction transaction) {
        return just(transaction.getResponse())
                .cast(ErrorDTO.class)
                .map(errorDTO -> errorDTO.evaluateErrors(transaction.getRoute()))
                .flatMap(ErrorDTO::toModel)
                .flatMap(error -> assignErrorResponse(error, transaction));
    }

    public static Mono<Transaction> assignErrorResponse(Error error, Transaction transaction){
        return Mono.just(error)
                .map(responseError -> {
                    transaction.setResponse(responseError);
                    return transaction;
                });
    }

}
