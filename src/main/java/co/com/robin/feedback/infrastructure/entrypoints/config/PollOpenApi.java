package co.com.robin.feedback.infrastructure.entrypoints.config;

import co.com.robin.feedback.domain.model.error.Error;
import co.com.robin.feedback.domain.model.transaction.request.type.RequestAnswer;
import co.com.robin.feedback.domain.model.transaction.response.Response;
import org.springdoc.core.fn.builders.operation.Builder;

import java.util.function.Consumer;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class PollOpenApi {

    private static final String OPERATION_ID = "Retrieves poll";
    private static final String DESCRIPTION = "Retrieve poll from restaurants";
    private static final String DESCRIPTION_OK = "When the response has status 200";
    private static final String DESCRIPTION_CONFLICT = "When the request fails";
    private static final String DESCRIPTION_ERROR = "Internal server error";
    private static final String DESCRIPTION_REQUEST = "Object with data required to make the request";
    private static final String TAG = "Polls";

    public static Consumer<Builder> pollRoute() {
        return ops -> ops
                .operationId(OPERATION_ID)
                .description(DESCRIPTION)
                .tag(TAG)
                .summary(OPERATION_ID)
                .requestBody(request())
                .response(responseOk())
                .response(responseBusiness())
                .response(responseError())
                .response(responseNotFound());
    }

    private static org.springdoc.core.fn.builders.requestbody.Builder request() {
        return requestBodyBuilder()
                .description(DESCRIPTION_REQUEST)
                .required(true)
                .content(contentBuilder()
                        .mediaType(APPLICATION_JSON.toString())
                        .schema(schemaBuilder()
                                .implementation(RequestAnswer.class)));
    }

    private static org.springdoc.core.fn.builders.apiresponse.Builder responseOk(){
        return responseBuilder().
                responseCode(String.valueOf(OK.value()))
                .description(DESCRIPTION_OK)
                .implementation(Response.class);
    }

    private static org.springdoc.core.fn.builders.apiresponse.Builder responseBusiness(){
        return responseBuilder()
                .responseCode(String.valueOf(CONFLICT.value()))
                .description(DESCRIPTION_CONFLICT)
                .implementation(Error.class);
    }

    private static org.springdoc.core.fn.builders.apiresponse.Builder responseError(){
        return responseBuilder()
                .responseCode(String.valueOf(INTERNAL_SERVER_ERROR.value()))
                .description(DESCRIPTION_ERROR)
                .implementation(Error.class);
    }

    private static org.springdoc.core.fn.builders.apiresponse.Builder responseNotFound(){
        return responseBuilder()
                .responseCode(String.valueOf(NOT_FOUND.value()))
                .description(NOT_FOUND.getReasonPhrase())
                .implementation(Error.class);
    }

}
