package co.com.robin.feedback.infrastructure.drivenadapters.repository;

import co.com.robin.feedback.domain.model.transaction.request.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPollRepository extends MongoRepository<Request.RequestData.Poll, Integer> {

    @Query("{pollName:'?0'}")
    List<Request.RequestData.Poll> findPollByName(String pollName);

}
