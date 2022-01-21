package co.com.robin.feedback.infrastructure.drivenadapters.service;

import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.gateway.IAnswerGateway;
import co.com.robin.feedback.domain.model.transaction.gateway.IPollGateway;
import co.com.robin.feedback.infrastructure.drivenadapters.repository.IAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerGateway {

    private final IAnswerRepository answerRepository;

    @Override
    public Mono<Transaction> saveAnswer(Transaction transaction) {
        return null;
    }
}
