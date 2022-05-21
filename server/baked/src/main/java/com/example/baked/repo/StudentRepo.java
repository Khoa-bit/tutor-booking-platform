package com.example.baked.repo;

import com.example.baked.model.Student;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, String> {

  List<Student> findByFirstname(String firstname);

  List<Student> findByLastname(String lastname);
}
