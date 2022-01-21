package co.com.robin.feedback.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "polls")
public class PollEntity {

    @Id
    private String pollId;
    private String pollName;
    private String pollDescription;

    private List<QuestionPoll> questionPolls;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class QuestionPoll {

        @Id
        private String questionId;
        private String questionTypeId;
        private String questionDescription;

        private List<OptionQuestion> optionQuestions;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder(toBuilder = true)
        public static class OptionQuestion {
            @Id
            private String optionId;
            private String questionId;
            private String optionDescription;
        }
    }

}
