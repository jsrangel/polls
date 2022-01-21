package co.com.robin.feedback.infrastructure.entrypoints.services.answer;

import co.com.robin.feedback.domain.usecase.AnswerUseCase;
import co.com.robin.feedback.infrastructure.entrypoints.services.AbstractBaseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AnswerHandler extends AbstractBaseHandler  {

    private final AnswerUseCase answerPollUseCase;

    public Mono<ServerResponse> saveAnswer(ServerRequest serverRequest) {
        return getResponse(serverRequest, answerPollUseCase::saveAnswer);
    }
}
