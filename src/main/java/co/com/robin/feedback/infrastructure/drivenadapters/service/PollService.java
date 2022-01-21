package co.com.robin.feedback.infrastructure.drivenadapters.service;

import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.gateway.IPollGateway;
import co.com.robin.feedback.domain.model.transaction.request.Request;
import co.com.robin.feedback.infrastructure.drivenadapters.repository.IPollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollService implements IPollGateway {

    private final IPollRepository pollRepository;

    @Override
    public Mono<Transaction> savePoll(Transaction transaction) {
        pollRepository.save(Request.RequestData.Poll.builder()
                .pollId(transaction.getRequest().getData().getPoll().getPollId())
                .pollName(transaction.getRequest().getData().getPoll().getPollName())
                .pollDescription(transaction.getRequest().getData().getPoll().getPollDescription())
                .questionPolls(transaction.getRequest().getData().getPoll().getQuestionPolls())
                .build());

        return Mono.just(Transaction.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.is2xxSuccessful())
                .request(transaction.getRequest())
                .response("Poll save success")
                .build());
    }

    @Override
    public Mono<Transaction> retrievePoll(Transaction transaction) {
        return Mono.just(pollRepository.findPollByName(transaction.getRequest().getData().getPoll().getPollName()))
                .doOnNext(System.out::println)
                .map(obj -> Transaction.builder()
                        .statusCode(200)
                        .response(obj)
                        .build());
    }

    /*
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

    public Mono<Transaction> post(String route, Transaction transaction) {
        return clientProperties.filterHeaders(transaction.getHeaders())
                .flatMap(headers -> webClient.post()
                        .uri(uriBuilder(route, transaction))
                        .headers(head -> head.setAll(headers))
                        .bodyValue(assignRequest(transaction.getRequest()))
                        .exchangeToMono(clientResponse -> assignResponse(clientResponse, transaction))
                        .onErrorMap(ex -> new TechnicalException(ex, TechnicalExceptionEnum.TECHNICAL_REST_CLIENT_ERROR)));
    }

    private Mono<Transaction> assignResponse(ClientResponse clientResponse, Transaction transaction) {
        return Mono.just(clientResponse.statusCode())
                .filter(HttpStatus::is2xxSuccessful)
                .flatMap(s -> clientResponse.bodyToMono(Response.class))
                .flatMap(responseSuccess -> toComplete(clientResponse, transaction, responseSuccess))
                .switchIfEmpty(assignError(clientResponse, transaction));
    }
    */

}
