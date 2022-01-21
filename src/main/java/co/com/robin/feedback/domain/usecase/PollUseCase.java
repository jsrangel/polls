package co.com.robin.feedback.domain.usecase;

import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.gateway.IPollGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PollUseCase {

    private final IPollGateway pollGateway;

    public Mono<Transaction> savePoll(Transaction transaction) {
        return pollGateway.savePoll(transaction);
    }

    public Mono<Transaction> retrievePoll(Transaction transaction) {
        return pollGateway.retrievePoll(transaction);
    }

}
