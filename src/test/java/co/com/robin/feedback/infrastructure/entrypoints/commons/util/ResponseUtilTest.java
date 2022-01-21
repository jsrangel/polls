package co.com.robin.feedback.infrastructure.entrypoints.commons.util;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ResponseUtilTest {

    private TestClass testClass;

    @Data
    @Builder
    public static class TestClass {
        public String name;
    }

    @BeforeEach
    public void init() {
        testClass = TestClass.builder().name("test").build();
    }

    @Test
    public void buildResponseOK() {
        StepVerifier.create(ResponseUtil.buildResponse(HttpStatus.OK, testClass))
                .expectNextMatches(res -> res.statusCode().is2xxSuccessful())
                .verifyComplete();
    }

    @Test
    public void buildResponseFail() {
        StepVerifier.create(ResponseUtil.responseFail(testClass))
                .expectNextMatches(res -> res.statusCode().is5xxServerError())
                .verifyComplete();
    }

    @Test
    public void buildResponse() {
        StepVerifier.create(ResponseUtil.buildResponse(HttpStatus.CONFLICT, testClass))
                .expectNextMatches(res -> res.statusCode().is4xxClientError())
                .verifyComplete();
    }

}
