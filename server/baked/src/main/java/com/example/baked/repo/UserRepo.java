package com.example.baked.repo;

import com.example.baked.model.AuthUser;
import com.example.baked.model.Role;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepo extends MongoRepository<AuthUser, String> {
  AuthUser findByUsername(String username);

  @Query(value = "{}", fields = "{username: 1, userMetadata: 1}")
  List<AuthUser> findAllUserMetadata();

  @Query(value = "{roles: 'ROLE_STUDENT'}", fields = "{username: 1, userMetadata: 1}")
  List<AuthUser> findAllStudentMetadata();

  @Query(value = "{roles: 'ROLE_TUTOR'}", fields = "{username: 1, userMetadata: 1}")
  List<AuthUser> findAllTutorMetadata();

  List<AuthUser> findAllByRolesContaining(Role role);
}
