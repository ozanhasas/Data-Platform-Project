package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Admins;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminsRepo extends MongoRepository<Admins,String>{
}
