package co.com.robin.feedback.domain.model.transaction;

import co.com.robin.feedback.domain.model.transaction.request.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction implements Serializable {

    private String route;

    private boolean status;
    private Integer statusCode;

    private Request request;
    private Object response;
    private Object queryParams;

    private Map<String, String> headers;
    private Map<String, String> pathParams;

    /*
    public Transaction setIdentificationNumber(String number) {
        this.getRequest().getRequestAnswer().getCustomer().getIdentification().setNumber(number);
        return this;
    }
     */


}
