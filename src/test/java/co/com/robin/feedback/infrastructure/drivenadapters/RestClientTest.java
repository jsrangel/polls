package co.com.robin.feedback.infrastructure.drivenadapters;

import co.com.robin.feedback.domain.model.error.Error;
import co.com.robin.feedback.domain.model.datatest.DataTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Objects;

import static co.com.robin.feedback.domain.model.commons.enums.TechnicalExceptionEnum.TECHNICAL_REST_CLIENT_ERROR;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.CODE_ERROR;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.PATH_NOT_FOUND;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.MESSAGE_ERROR;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.PATH;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.RESPONSE;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.apicResponseError;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.apicResponseUnauthorized;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.buildRestClient;
import static co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest.getBaseUrl;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class RestClientTest {

    private MockWebServer mockServer;
    private RestClient restClient;

    @BeforeEach
    public void setUp() {
        mockServer = new MockWebServer();
        restClient = buildRestClient(mockServer.getPort());
        mockServer.url(getBaseUrl(mockServer.getPort()));
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockServer.shutdown();
    }

    /*
    @Test
    public void givenPostWhenInvalidCredentialsThenUnauthorizedTest() {
        mockServer.enqueue(apicResponseUnauthorized());
        StepVerifier.create(restClient.post(PATH, DataTest.buildTransaction()))
                .expectNextMatches(transaction -> {
                    Error.Data error = ((Error) transaction.getResponse()).getErrors().get(0);
                    return error.getCode().equals(TECHNICAL_REST_CLIENT_ERROR.getCode());
                })
                .verifyComplete();
    }

    @Test
    public void givenPostWhenValidRequestThenApiConnectResponseError(){
        mockServer.enqueue(apicResponseError());
        StepVerifier.create(restClient.post(PATH, DataTest.buildTransaction()))
                .expectNextMatches(transaction -> {
                    Error.Data errorData = ((Error)transaction.getResponse()).getErrors().get(0);
                    return errorData.getMessage().equals(MESSAGE_ERROR) && errorData.getCode().equals(CODE_ERROR);
                })
                .verifyComplete();
    }

    /*
    @Test
    public void givenPostWhenValidParamsThenSuccess() {
        mockServer.enqueue(new MockResponse()
                .setResponseCode(OK.value())
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .setBody(RESPONSE));
        StepVerifier.create(restClient.post(PATH, DataTest.buildTransaction()))
                .expectNextMatches(Objects::nonNull)
                .verifyComplete();
    }


    @Test
    public void givenPostWhenInvalidPathThenNotFound() {
        mockServer.enqueue(new MockResponse().setResponseCode(NOT_FOUND.value()));
        StepVerifier.create(restClient.post(PATH_NOT_FOUND, DataTest.buildTransaction()))
                .expectError();
    }

     */

}