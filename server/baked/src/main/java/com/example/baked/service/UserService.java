package com.example.baked.service;

import com.example.baked.controller.error.BadRequestException;
import com.example.baked.model.AuthUser;
import com.example.baked.model.Role;
import com.example.baked.model.UserMetadata;
import com.example.baked.repo.UserRepo;
import com.example.baked.util.SecurityUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
  private final UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthUser authUser =
        userRepo
            ._findByUsernameWithPassword(username)
            .orElseThrow(
                () -> {
                  log.error("Username {} not found", username);
                  throw new UsernameNotFoundException(
                      String.format("Username %s not found", username));
                });
    log.info("Username {} found", username);

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authUser
        .getRoles()
        .forEach(appRole -> authorities.add(new SimpleGrantedAuthority(appRole.toString())));
    return new User(authUser.getUsername(), authUser.getPassword(), authorities);
  }

  public AuthUser saveAuthUser(AuthUser authUser) throws RuntimeException {
    log.info("Saving new AuthUser {} to the database", authUser.getUsername());
    if (userRepo.findByUsername(authUser.getUsername()).isPresent()) {
      throw new RuntimeException(
          "Username %s has already existed".formatted(authUser.getUsername()));
    }

    authUser.setPassword(SecurityUtil.encodePassword(authUser.getPassword()));

    UserMetadata userMetadata = authUser.getUserMetadata();

    if (authUser.getRoles().contains(Role.ROLE_STUDENT) ^ userMetadata.getStudent() != null) {
      throw new RuntimeException("ROLE_STUDENT without Student object");
    }

    if (authUser.getRoles().contains(Role.ROLE_TUTOR) ^ userMetadata.getTutor() != null) {
      throw new RuntimeException("ROLE_TUTOR without Tutor object");
    }

    return userRepo.save(authUser);
  }

  public void addRoleToUser(String username, Role role) {
    log.info("Adding new AuthRole {} to AuthUser {}", role, username);
    AuthUser authUser = userRepo.findByUsername(username).orElseThrow(BadRequestException::new);
    authUser.getRoles().add(role);
    userRepo.save(authUser);
  }

  public AuthUser getAuthUser(String username) {
    log.info("Fetching AuthUser {}", username);
    return userRepo.findByUsername(username).orElseThrow(BadRequestException::new);
  }

  public List<AuthUser> getAuthUsers() {
    log.info("Fetching all AuthUsers");
    return userRepo.findAll();
  }

  public List<AuthUser> getUserMetadata() {
    log.info("Fetching all UserMetadata");
    return userRepo.findAllUserMetadata();
  }

  public List<AuthUser> getAllTutorMetadata() {
    log.info("Fetching all UserMetadata");
    return userRepo.findAllTutorMetadata();
  }

  public List<AuthUser> getAllStudentMetadata() {
    log.info("Fetching all UserMetadata");
    return userRepo.findAllStudentMetadata();
  }

  public Optional<AuthUser> getUserMetadataById(String id) {
    log.info("Fetching all UserMetadata by id: {}", id);
    return userRepo.findAuthUserByIdWithoutPassword(id);
  }

  public Optional<AuthUser> getStudentMetadataById(String id) {
    log.info("Fetching all UserMetadata by id: {}", id);
    return userRepo.findStudentByIdWithoutPassword(id);
  }

  public Optional<AuthUser> getTutorMetadataById(String id) {
    log.info("Fetching all UserMetadata by id: {}", id);
    return userRepo.findTutorByIdWithoutPassword(id);
  }

  public Optional<AuthUser> getUserMetadataByUsername(String username) {
    log.info("Fetching all UserMetadata by username: {}", username);
    return userRepo.findByUsername(username);
  }

  public Optional<AuthUser> getStudentMetadataByUsername(String username) {
    log.info("Fetching all UserMetadata by username: {}", username);
    return userRepo.findStudentByUsername(username);
  }

  public Optional<AuthUser> getTutorMetadataByUsername(String username) {
    log.info("Fetching all UserMetadata by username: {}", username);
    return userRepo.findTutorByUsername(username);
  }
}
