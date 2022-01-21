package co.com.robin.feedback.domain.model.datatest;

import co.com.robin.feedback.domain.model.error.Error;
import co.com.robin.feedback.domain.model.transaction.Transaction;
import co.com.robin.feedback.domain.model.transaction.request.Request;
import co.com.robin.feedback.domain.model.transaction.request.type.RequestAnswer;
import co.com.robin.feedback.domain.model.transaction.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTest {

    /*
    public static Transaction buildTransaction() {
        return Transaction.builder()
                .request(Request.builder()
                        .req(RequestAnswer.builder()
                                .customer(RequestAnswer.Customer.builder()
                                        .identification(RequestAnswer.Customer.Identification.builder()
                                                .type("")
                                                .number("123456789")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .response(Response.builder().build())
                .route("/robin/api/v1/feedback/poll")
                .pathParams(Map.of("id", "1"))
                .headers(new HashMap<>())
                .statusCode(200)
                .build();
    }
     */

    public static Error buildError() {
        return Error.builder()
                .errors(List.of(Error.Data.builder()
                        .message("any-message")
                        .code("any-code")
                        .build()))
                .build();
    }

}
