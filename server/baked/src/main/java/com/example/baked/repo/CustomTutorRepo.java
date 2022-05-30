package com.example.baked.repo;

import com.example.baked.model.AuthUser;
import java.util.List;

public interface CustomTutorRepo {
  List<AuthUser> getTutorOnMainSearch(String city, String subject, String grade);
}
