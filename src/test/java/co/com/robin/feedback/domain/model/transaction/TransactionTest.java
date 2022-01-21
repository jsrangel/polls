package co.com.robin.feedback.domain.model.transaction;

import co.com.robin.feedback.domain.model.datatest.DataTest;
import co.com.robin.feedback.domain.model.error.Error;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    private static final String ID_NUMBER = "123";


    /*
    @Test
    public void setIdNumberTest() {
        Transaction transaction = DataTest.buildTransaction();
        assertThat(transaction.setIdentificationNumber(ID_NUMBER))
                .isNotNull();
    }

    @Test
    public void assignErrorResponseTest() {
        StepVerifier.create(assignErrorResponse(DataTest.buildError(), DataTest.buildTransaction()))
                .expectNextMatches(trx -> trx.getResponse() != null)
                .verifyComplete();
    }
    */

    public static Mono<Transaction> assignErrorResponse(Error error, Transaction transaction){
        return Mono.just(error)
                .map(responseError -> {
                    transaction.setResponse(responseError);
                    return transaction;
                });
    }

}
