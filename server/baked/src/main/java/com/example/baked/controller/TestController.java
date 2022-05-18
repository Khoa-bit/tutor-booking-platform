package com.example.baked.controller;

import org.springframework.stereotype.Controller;
import java.util.*;

import com.example.baked.model.Tutor;
import com.example.baked.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    
    @Autowired
    private static CustomTutorRepoImp tutorRepo;
    
    @GetMapping(value = "/")
    public void homepage() {
        List<Tutor> tutor = tutorRepo.getTutorOnFeatures(null, "master", null);
        System.out.print(tutor);
    }
}
