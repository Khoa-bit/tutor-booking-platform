package com.example.baked.repo;

import com.example.baked.model.AuthUser;
import com.example.baked.model.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepo extends MongoRepository<AuthUser, String> {
  @Query(fields = "{password: 0}")
  Optional<AuthUser> findByUsername(String username);

  @Query(value = "{username:  ?0}")
  Optional<AuthUser> _findByUsernameWithPassword(String username);

  @Query(value = "{}", fields = "{password: 0}")
  List<AuthUser> findAllUserMetadata();

  @Query(value = "{roles: 'ROLE_STUDENT'}", fields = "{password: 0}")
  List<AuthUser> findAllStudentMetadata();

  @Query(value = "{roles: 'ROLE_TUTOR'}", fields = "{password: 0}")
  List<AuthUser> findAllTutorMetadata();

  List<AuthUser> findAllByRolesContaining(Role role);
}
