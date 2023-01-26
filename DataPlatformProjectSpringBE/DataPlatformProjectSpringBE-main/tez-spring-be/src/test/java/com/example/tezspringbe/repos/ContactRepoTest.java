package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Contact;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author meteh
 * @create 9.06.2022 14:24
 */
@DataMongoTest
@RunWith(SpringRunner.class)
class ContactRepoTest {
    @Autowired
    private ContactRepo contactRepo;

    @Test
    public void insertTest() {
        Contact result  = contactRepo.insert(prepareContact());
        assertNotNull(result);
    }
    private Contact prepareContact() {
        return new Contact("exampleName","exampleEmail@gmail.com","contentExample");
    }

}