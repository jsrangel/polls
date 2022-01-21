package co.com.robin.feedback.infrastructure.drivenadapters.repository;

import co.com.robin.feedback.domain.model.transaction.response.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends MongoRepository<Response.ResponseData, Integer> {
}
