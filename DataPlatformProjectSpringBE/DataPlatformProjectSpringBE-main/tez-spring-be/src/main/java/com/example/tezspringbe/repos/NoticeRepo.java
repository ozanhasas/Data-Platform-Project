package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Notice;

import org.springframework.data.mongodb.repository.MongoRepository;




public interface NoticeRepo extends MongoRepository<Notice,String> {

}
