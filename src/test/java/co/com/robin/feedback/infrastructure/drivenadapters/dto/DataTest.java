package co.com.robin.feedback.infrastructure.drivenadapters.dto;

import co.com.robin.feedback.infrastructure.drivenadapters.RestClient;
import co.com.robin.feedback.infrastructure.drivenadapters.config.ClientProperties;
import okhttp3.mockwebserver.MockResponse;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class DataTest {

    public static final String HOST = "http://localhost:%s/";
    public static final String PATH = "v1/operations/service";
    public static final String PATH_NOT_FOUND = "v1/not-found";
    public static final String RESPONSE = buildResponse();
    public static final String APP_NAME = "app";
    public static final String MESSAGE_ERROR = "Gateway Timeout";
    public static final String CODE_ERROR = "BP504";

    public static String getBaseUrl(int port){
        return String.format(HOST, port);
    }

    private static String buildResponse(){
        return "{\n" +
                "  \"meta\": {\n" +
                "    \"_messageId\": \"c4e6bd04-5149-11e7-b114-b2f933d5fe66\",\n" +
                "    \"_requestTimeStamp\": \"2017-01-24T05:00:00.000Z\",\n" +
                "    \"_applicationId\": \"AW1920001\",\n" +
                "    \"_responseSize\": 1,\n" +
                "    \"_version\": \"3.0\"\n" +
                "  },\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"header\": {\n" +
                "        \"type\": \"Recharge\",\n" +
                "        \"id\": \"string\"\n" +
                "      },\n" +
                "      \"customerInformation\": {\n" +
                "        \"documentType\": \"CC\",\n" +
                "        \"documentNumber\": 12345678\n" +
                "      },\n" +
                "      \"rechargeInformation\": {\n" +
                "        \"transactionCode\": \"09123456789\",\n" +
                "        \"stateRecharge\": \"Exitoso\",\n" +
                "        \"rejectCode\": \"0000\",\n" +
                "        \"rejectDescription\": \"Exitoso\",\n" +
                "        \"dateHourRequestRecharge\": \"2020-08-11T17:33:05.731Z\",\n" +
                "        \"dateHourAnswerRecharge\": \"2020-08-11T17:33:05.731Z\",\n" +
                "        \"referenceRecharge\": \"string\",\n" +
                "        \"rechargeAmount\": \"############.##\",\n" +
                "        \"rechargeCost\": \"############.##\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"links\": {\n" +
                "    \"self\": \"string\",\n" +
                "    \"related\": \"string\"\n" +
                "  }\n" +
                "}";
    }

    public static MockResponse apicResponseOk(){
        return new MockResponse()
                .setResponseCode(OK.value())
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .setBody(RESPONSE);
    }

    public static MockResponse apicResponseUnauthorized(){
        return new MockResponse()
                .setResponseCode(UNAUTHORIZED.value())
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .setBody("{\n" +
                        "    \"httpCode\": \"401\",\n" +
                        "    \"httpMessage\": \"Unauthorized\",\n" +
                        "    \"moreInformation\": \"Invalid client id or secret.\"\n" +
                        "}");
    }

    public static MockResponse apicResponseError(){
        return new MockResponse()
                .setResponseCode(INTERNAL_SERVER_ERROR.value())
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .setBody("{\n" +
                        "\t\"meta\": {\n" +
                        "\t\t\"_messageId\": \"c4e6bd04-5149-11e7-b114-b2f933d5fe66\",\n" +
                        "\t\t\"_requestTimeStamp\": \"2017-01-24T05:00:00.000Z\",\n" +
                        "\t\t\"_applicationId\": \"acxff62e-6f12-42de-9012-3e7304418abd\",\n" +
                        "\t\t\"_responseSize\": 1,\n" +
                        "\t\t\"_version\": \"3.0\"\n" +
                        "\t},\n" +
                        "\t\"errors\": [{\n" +
                        "\t\t\"href\": \"https://tools.ietf.org/html/rfc7231#section-6.5.4\",\n" +
                        "\t\t\"status\": \"504\",\n" +
                        "\t\t\"code\": \""+CODE_ERROR+"\",\n" +
                        "\t\t\"title\": \""+MESSAGE_ERROR+"\",\n" +
                        "\t\t\"detail\": \"Tiempo de respuesta del proveedor del servicio excedido\"\n" +
                        "\t}]\n" +
                        "}");
    }

    public static RestClient buildRestClient(int port){
        MockEnvironment env = new MockEnvironment();
        env.setProperty("messages.general-error","error");
        env.setProperty("messages.document-error", "documentError");
        return new RestClient(WebClient.builder()
                .baseUrl(getBaseUrl(port))
                .build(), clientProperties());
    }

    public static ClientProperties clientProperties(){
        return ClientProperties.builder()
                .allowedHeaders(List.of("x-ibm-client-id", "x-ibm-client-secret"))
                .build();
    }

    public static Map<String, String> map() {
        return Map.of("x-ibm-client-id", "value",
                "x-ibm-client-secret", "value",
                "message-id", "123");
    }

}
