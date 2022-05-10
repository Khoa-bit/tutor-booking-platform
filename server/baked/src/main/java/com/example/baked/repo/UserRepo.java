package com.example.baked.repo;

import com.example.baked.model.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<AuthUser, String> {
  AuthUser findByUsername(String username);
}
