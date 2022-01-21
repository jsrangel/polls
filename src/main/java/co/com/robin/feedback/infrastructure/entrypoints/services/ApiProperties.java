package co.com.robin.feedback.infrastructure.entrypoints.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "entry-points")
public class ApiProperties {

    private String pathBase;

    private PollRoutes pollRoutes;
    private AnswerRoutes answerRoutes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PollRoutes {
        private String savePoll;
        private String retrievePoll;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswerRoutes {
        private String saveAnswer;
    }

}
