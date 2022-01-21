package co.com.robin.feedback.infrastructure.drivenadapters.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private ClientHttpConnector connector() {
        return new ReactorClientHttpConnector(HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .compress(true)
                .keepAlive(true));
    }

    @Bean
    public WebClient webClientConfig(final ClientProperties clientProperties) {
        return WebClient.builder()
                .clientConnector(connector())
                .baseUrl(clientProperties.getBaseUrl())
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
                .build();
    }

}
