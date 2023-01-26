package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.AnalysisRequest;
import com.example.tezspringbe.models.DatasetAnalysis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author meteh
 * @create 9.06.2022 14:22
 */
@DataMongoTest
@RunWith(SpringRunner.class)
class AnalysisRequestAdminRepoTest {
    @Autowired
    private AnalysisRequestAdminRepo analysisRequestAdminRepo;

    @Test
    public void testFindByRelatedIdNull() {
        List<DatasetAnalysis> result =  analysisRequestAdminRepo.findByRelatedId("abc");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByRelatedIdNotNull() {
        List<DatasetAnalysis> result = analysisRequestAdminRepo.findByRelatedId("629925b38eccae5ecc3dc987");
        assertFalse(result.isEmpty());
        assertEquals(result.size(),1);
    }

}