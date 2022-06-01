package com.example.baked.controller;

import java.util.*;

import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Student;
import com.example.baked.model.StudentAuthentication;
import com.example.baked.model.Tutor;
import com.example.baked.model.TutorAuthentication;
import com.example.baked.service.AdminAPIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminAPIController {

    /////////////////// Import services //////////////
    @Autowired
    private AdminAPIService adminAPIService = new AdminAPIService();

    /////////////////// API //////////////
    @GetMapping(value = "/api/tutors")
    @ResponseBody
    public List<Tutor> apiGetAllTutors() {
        return adminAPIService.apiGetAllTutors();
    }

    @GetMapping(value = "/api/students")
    @ResponseBody
    public List<Student> apiGetAllStudents() {
        return adminAPIService.apiGetAllStudents();
    }

    @GetMapping(value = "/api/tutors-authentication")
    @ResponseBody
    public List<TutorAuthentication> apiGetAllTutorsAuthentication() {
        return adminAPIService.apiGetAllTutorsAuthentication();
    }

    @GetMapping(value = "/api/students-authentication")
    @ResponseBody
    public List<StudentAuthentication> apiGetAllStudentsAuthentication() {
        return adminAPIService.apiGetAllStudentsAuthentication();
    }

    @GetMapping(value = "/api/classes")
    @ResponseBody
    public List<Class> apiGetAllClasses() {
        return adminAPIService.apiGetAllClasses();
    }

    @GetMapping(value = "/api/periods")
    @ResponseBody
    public List<Period> apiGetAllPeriods() {
        return adminAPIService.apiGetAllPeriods();
    }

    @GetMapping(value = "/api/requests-from-students")
    @ResponseBody
    public List<RequestFromStudent> apiGetAllRequestsFromStudents() {
        return adminAPIService.apiGetAllRequestsFromStudents();
    }
}
