package co.com.robin.feedback.infrastructure.entrypoints.commons.util;

import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ParamsUtil {

    public static Mono<Map<String, String>> entrySetToMap(Set<Map.Entry<String, List<String>>> entrySet) {
        return Mono.just(entrySet.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> String.join(",", v.getValue()))));
    }

}
