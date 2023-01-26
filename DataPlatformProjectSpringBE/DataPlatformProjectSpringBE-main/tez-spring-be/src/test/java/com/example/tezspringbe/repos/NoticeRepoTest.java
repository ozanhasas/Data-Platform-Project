package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Notice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author meteh
 * @create 9.06.2022 14:24
 */
@DataMongoTest
@RunWith(SpringRunner.class)
class NoticeRepoTest {

    @Autowired
    private NoticeRepo noticeRepo;

    @Test
    public void testGetAll() {
        List<Notice> noticeList = noticeRepo.findAll();
        assertNotNull(noticeList);
        assertEquals(noticeList.size(),4);
    }
}