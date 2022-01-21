package co.com.robin.feedback.infrastructure.entrypoints.services.answer;

import co.com.robin.feedback.infrastructure.entrypoints.services.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static co.com.robin.feedback.infrastructure.entrypoints.config.PollOpenApi.pollRoute;
import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
@RequiredArgsConstructor
public class AnswerRoute {

    private final ApiProperties properties;

    @Bean
    public RouterFunction<ServerResponse> answerFunctionalRoute(AnswerHandler handler) {
        return nest(path(properties.getPathBase()).and(accept(APPLICATION_JSON)),
                route().GET(properties.getAnswerRoutes().getSaveAnswer(), handler::saveAnswer, pollRoute()).build());
    }
}
