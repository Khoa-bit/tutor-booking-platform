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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @Autowired
    private CustomTutorRepo tutorRepo;
    @Autowired
    private CustomStudentRepo studentRepo;
    
    @GetMapping(value = "/")
    public String homepage() {
        //List<Tutor> tutor = tutorRepo.getTutorOnFeatures("", "master", "Math");
        //List<Tutor> tutor = tutorRepo.getTutorOnQualification("master");
        //List<Tutor> tutor = tutorRepo.getTutorOnPopularity();
        //List<Tutor> tutor = tutorRepo.getTutorOnMainSearch("Long An", "Math", "8");
        String string = studentRepo.getStudentByID("Stvagm");
        //String string = new String();
        /*for (Tutor item : tutor) {
            string = string + item.getFullname().toString() + "\n";
        }*/
        //return tutor.get(0).getFullname().toString();
        return string;
        //return "hello";
    }
}
