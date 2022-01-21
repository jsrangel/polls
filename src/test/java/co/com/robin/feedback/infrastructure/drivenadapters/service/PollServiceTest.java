package co.com.robin.feedback.infrastructure.drivenadapters.service;

import co.com.robin.feedback.domain.model.datatest.DataTest;
import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.infrastructure.drivenadapters.RestClient;
import co.com.robin.feedback.infrastructure.drivenadapters.config.ClientProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PollServiceTest {

    @InjectMocks
    private PollService pollService;

    @Mock
    private RestClient restClient;

    @Mock
    private ClientProperties clientProperties;

    // Transaction requestPoll = DataTest.buildTransaction();

    /*
    @BeforeEach
    public void setUp() {
        when(clientProperties.getResources()).thenReturn(buildProperties());
        when(restClient.post(any(String.class), any(Transaction.class))).thenReturn(Mono.just(requestPoll));
    }
    */


    private ClientProperties.Resources buildProperties(){
        return new ClientProperties.Resources("");
    }

    /*
    @Test
    public void retrieveList() {
        StepVerifier.create(pollService.retrievePoll(requestPoll))
                .expectNextMatches(res -> res.getResponse() != null)
                .verifyComplete();
    }
     */

}
