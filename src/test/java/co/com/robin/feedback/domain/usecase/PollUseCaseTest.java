package co.com.robin.feedback.domain.usecase;

import co.com.robin.feedback.domain.model.datatest.DataTest;
import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.gateway.IPollGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PollUseCaseTest {

    @InjectMocks
    private PollUseCase pollUseCase;

    @Mock
    private IPollGateway pollGateway;

    private Transaction transaction;

    /*
    @BeforeEach
    public void setUp() {
        this.transaction = DataTest.buildTransaction();
    }
    */

    @Test
    public void retrievePoll() {
        when(pollGateway.retrievePoll(transaction)).thenReturn(Mono.just(transaction));
        validateUseCase(pollUseCase::retrievePoll);
        verify(pollGateway).retrievePoll(transaction);

    }

    private void validateUseCase(Function<Transaction, Mono<Transaction>> applyUseCase) {
        StepVerifier.create(applyUseCase.apply(transaction))
                .expectNextMatches(res -> res.getResponse() != null)
                .verifyComplete();
    }

}

