package co.com.robin.feedback.infrastructure.drivenadapters.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RestClientConfigTest {

    @InjectMocks
    private RestClientConfig restClientConfig;

    @Test
    public void webClientConfig() {
        assertThat(restClientConfig.webClientConfig( buildClientProperties() ))
                .isNotNull();
    }

    private ClientProperties buildClientProperties(){
        ClientProperties properties = new ClientProperties();
        properties.setBaseUrl("");
        return properties;
    }

}
