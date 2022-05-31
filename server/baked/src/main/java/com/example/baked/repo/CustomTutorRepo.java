package com.example.baked.repo;

import com.example.baked.model.AuthUser;
import java.util.List;

public interface CustomTutorRepo {
  List<AuthUser> getTutorOnMainSearch(String city, String subject, String grade);

  List<AuthUser> getTutorOnMainSearch2(String city, String subject, String grade, String district);

  List<AuthUser> getTutorOnPopularity();
}
