package co.com.robin.feedback.infrastructure.drivenadapters.dto;

import co.com.robin.feedback.domain.model.transaction.request.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RequestDTO {
    private List<Request> data;
}
