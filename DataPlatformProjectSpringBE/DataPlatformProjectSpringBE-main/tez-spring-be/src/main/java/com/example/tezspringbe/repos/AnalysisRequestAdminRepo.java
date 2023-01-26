package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.DatasetAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @Author meteh
 * @create 28.05.2022 22:45
 */
public interface AnalysisRequestAdminRepo extends MongoRepository<DatasetAnalysis,String> {

    @Query("{'related_data_set_id': ?0}")
    List<DatasetAnalysis> findByRelatedId(String related_data_set_id);

}
