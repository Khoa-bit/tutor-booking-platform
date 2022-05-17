package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.Tutor;

public interface CustomRepo {
    List<Tutor> getTutorOnFeatures(String a, String b, String c);
}