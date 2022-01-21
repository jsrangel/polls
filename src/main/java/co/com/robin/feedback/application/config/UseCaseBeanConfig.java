package co.com.robin.feedback.application.config;

import co.com.robin.feedback.domain.model.transaction.gateway.IAnswerGateway;
import co.com.robin.feedback.domain.model.transaction.gateway.IPollGateway;
import co.com.robin.feedback.domain.usecase.AnswerUseCase;
import co.com.robin.feedback.domain.usecase.PollUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean
    public PollUseCase pollUseCase(IPollGateway pollGateway) {
        return new PollUseCase(pollGateway);
    }

    @Bean
    public AnswerUseCase answerUseCase(IAnswerGateway answerGateway) {
        return new AnswerUseCase(answerGateway);
    }

}
