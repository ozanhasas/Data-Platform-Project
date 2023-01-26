package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Admins;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author meteh
 * @create 9.06.2022 14:22
 */
@DataMongoTest
@RunWith(SpringRunner.class)
class AdminsRepoTest {

    @Autowired
    private AdminsRepo adminsRepo;

    @Test
    public void getAdminDataTest() {
        List<Admins> adminList = adminsRepo.findAll();
        assertFalse(adminList.isEmpty());
        assertTrue(adminList.get(0).getUsername().equals("admin"));
    }

}