package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Dataset;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author meteh
 * @create 9.06.2022 14:24
 */
@DataMongoTest
@RunWith(SpringRunner.class)
class DatasetRepoTest {

    @Autowired
    private DatasetRepo datasetRepo;

    @Test
    void getAllOnaylanmadi() {
        List<Dataset> result = datasetRepo.getAllOnaylanmadi("onaylanmadi");
        assertNotNull(result);
    }

    @Test
    void getAllOnaylandi() {
        List<Dataset> result = datasetRepo.getAllOnaylandi("onaylandi");
        assertNotNull(result);
    }

    @Test
    void findByTitlesContainNotEmpty() {
        List<Dataset> result = datasetRepo.findByTitlesContain("spotify");
        assertNotNull(result);
    }

    @Test
    void findByDescriptionsContainNotEmpty() {
        List<Dataset> result = datasetRepo.findByDescriptionsContain("music");
        assertNotNull(result);
    }

    @Test
    void findByTitlesContainEmpty() {
        List<Dataset> result = datasetRepo.findByTitlesContain("qzxcccc");
        assertTrue(result.isEmpty());
    }

    @Test
    void findByDescriptionsContainEmpty() {
        List<Dataset> result = datasetRepo.findByDescriptionsContain("qzxcccc");
        assertTrue(result.isEmpty());
    }
}