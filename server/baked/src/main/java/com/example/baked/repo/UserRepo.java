package com.example.baked.repo;

import com.example.baked.model.AuthUser;
import com.example.baked.model.Role;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<AuthUser, String> {
  AuthUser findByUsername(String username);

  ArrayList<AuthUser> findAllByRolesContaining(Role role);
}
