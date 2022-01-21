package co.com.robin.feedback.infrastructure.drivenadapters.config;

import co.com.robin.feedback.infrastructure.drivenadapters.dto.DataTest;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ClientPropertiesTest {

    @Test
    public void filterHeadersTest(){
        StepVerifier.create(DataTest.clientProperties().filterHeaders(DataTest.map()))
                .expectNextMatches(map -> map.size() == 2)
                .verifyComplete();
    }
}
