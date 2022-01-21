package co.com.robin.feedback.infrastructure.entrypoints.services.poll;

import co.com.robin.feedback.domain.usecase.PollUseCase;
import co.com.robin.feedback.infrastructure.entrypoints.services.AbstractBaseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PollHandler extends AbstractBaseHandler {

    private final PollUseCase pollUseCase;

    public Mono<ServerResponse> savePoll(ServerRequest serverRequest) {
        return getResponse(serverRequest, pollUseCase::savePoll);
    }

    public Mono<ServerResponse> retrievePoll(ServerRequest serverRequest) {
        return getResponse(serverRequest, pollUseCase::retrievePoll);
    }

}
