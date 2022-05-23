package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.Tutor;

public interface CustomTutorRepo {
    List<Tutor> getTutorOnFeatures(String major, String qualification, String subject);
    List<Tutor> getTutorOnQualification(String qualification);
}