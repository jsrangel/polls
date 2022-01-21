package co.com.robin.feedback.infrastructure.drivenadapters.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "adapters.rest-client")
public class ClientProperties {

    private String baseUrl;
    private Resources resources;
    private List<String> allowedHeaders;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Resources {
        private String retrievePoll;
    }

    public Mono<Map<String, String>> filterHeaders(Map<String, String> headersToSent){
        return filterMap(headersToSent.entrySet(), allowedHeaders);
    }

    private static Mono<Map<String, String>> filterMap(
            Set<Map.Entry<String, String>> entrySet, List<String> allowedHeaders) {
        return Mono.just(entrySet.stream()
                .filter(stringListEntry -> allowedHeaders.contains(stringListEntry.getKey().toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, v -> String.join(",", v.getValue()))));
    }

}
