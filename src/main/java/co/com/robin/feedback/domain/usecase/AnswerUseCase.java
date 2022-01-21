package co.com.robin.feedback.domain.usecase;

import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.gateway.IAnswerGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AnswerUseCase {

    private final IAnswerGateway answerGateway;

    public Mono<Transaction> saveAnswer(Transaction transaction) {
        return answerGateway.saveAnswer(transaction);
    }
}
