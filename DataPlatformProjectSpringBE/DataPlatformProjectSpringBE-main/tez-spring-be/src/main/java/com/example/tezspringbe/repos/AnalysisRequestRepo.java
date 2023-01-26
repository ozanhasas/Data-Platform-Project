package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.AnalysisRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface AnalysisRequestRepo extends MongoRepository<AnalysisRequest,String> {
    @Query("{showInFe:true}")
    List<AnalysisRequest> findByShowInFe(boolean showInFE);

    @Query("{'dataset_id': ?0}")
    List<AnalysisRequest> findByRelatedId(String related_data_set_id);

}
