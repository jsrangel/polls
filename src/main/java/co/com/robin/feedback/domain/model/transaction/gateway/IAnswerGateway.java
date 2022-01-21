package co.com.robin.feedback.domain.model.transaction.gateway;

import co.com.robin.feedback.domain.model.transaction.Transaction;
import reactor.core.publisher.Mono;

public interface IAnswerGateway {
    Mono<Transaction> saveAnswer(Transaction transaction);
}
