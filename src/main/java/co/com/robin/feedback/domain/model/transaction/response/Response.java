package co.com.robin.feedback.domain.model.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Response {

    private ResponseData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class ResponseData {
        private Poll poll;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder(toBuilder = true)
        public static class Poll {

            private String pollId;
            private String pollName;
            private String pollDescription;

            private List<QuestionPoll> questionPolls;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            @Builder(toBuilder = true)
            public static class QuestionPoll {

                private String questionId;
                private String questionTypeId;
                private String questionDescription;

                private List<OptionQuestion> optionQuestions;

                @Data
                @NoArgsConstructor
                @AllArgsConstructor
                @Builder(toBuilder = true)
                public static class OptionQuestion {
                    private String optionId;
                    private String questionId;
                    private String optionDescription;
                }
            }
        }
    }

}
