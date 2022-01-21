package co.com.robin.feedback.application.config;

import co.com.robin.feedback.domain.model.transaction.gateway.IAnswerGateway;
import co.com.robin.feedback.domain.model.transaction.gateway.IPollGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UseCaseBeanConfigTest {

    @InjectMocks
    private UseCaseBeanConfig config;

    @MockBean
    private IPollGateway pollGateway;

    @MockBean
    private IAnswerGateway answerGateway;

    @Test
    public void pollUseCaseTest() {
        assertNotNull(config.pollUseCase(pollGateway));
    }

    @Test
    public void answerUseCaseTest() {
        assertNotNull(config.answerUseCase(answerGateway));
    }

}
