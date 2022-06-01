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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminAPIController {

    /////////////////// Import services //////////////
    @Autowired
    private AdminAPIService adminAPIService = new AdminAPIService();

    /////////////////// API Read /////////////////
    // Read All
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

    // Read by ID itself
    /*@GetMapping(value = "/api/tutor/{tutor_id}")
    @ResponseBody
    public Tutor apiGetTutorByTutorId(@PathVariable String tutor_id) {
        return ;
    }*/

    @GetMapping(value = "/api/tutor/{tutor_id}")
    @ResponseBody
    public Tutor apiGetTutorByTutorId(@PathVariable String tutor_id) {
        return adminAPIService.apiGetTutorByTutorId(tutor_id);
    }

    @GetMapping(value = "/api/student/{student_id}")
    @ResponseBody
    public Student apiGetStudentByStudentId(@PathVariable String student_id) {
        return adminAPIService.apiGetStudentByStudentId(student_id);
    }

    @GetMapping(value = "/api/tutor-authentication/{tutor_id}")
    @ResponseBody
    public TutorAuthentication apiGetTutorAuthenticationByTutorId(@PathVariable String tutor_id) {
        return adminAPIService.apiGetTutorAuthenticationByTutorId(tutor_id);
    }

    @GetMapping(value = "/api/tutor-authentication-by-username/{username}")
    @ResponseBody
    public TutorAuthentication apiGetTutorAuthenticationByUsername(@PathVariable String username) {
        return adminAPIService.apiGetTutorAuthenticationByUsername(username);
    }

    @GetMapping(value = "/api/student-authentication/{student_id}")
    @ResponseBody
    public StudentAuthentication apiGetStudentAuthenticationByStudentId(@PathVariable String student_id) {
        return adminAPIService.apiGetStudentAuthenticationByStudentId(student_id);
    }

    @GetMapping(value = "/api/student-authentication-by-username/{username}")
    @ResponseBody
    public StudentAuthentication apiGetStudentAuthenticationByUsername(@PathVariable String username) {
        return adminAPIService.apiGetStudentAuthenticationByUsername(username);
    }

    @GetMapping(value = "/api/class/{class_id}")
    @ResponseBody
    public Class apiGetClassByClassId(@PathVariable String class_id) {
        return adminAPIService.apiGetClassByClassId(class_id);
    }

    @GetMapping(value = "/api/period/{period_id}")
    @ResponseBody
    public Period apiGetPeriodByPeriodId(@PathVariable String period_id) {
        return adminAPIService.apiGetPeriodByPeriodId(period_id);
    }

    @GetMapping(value = "/api/request-from-student/{request_id}")
    @ResponseBody
    public RequestFromStudent apiGetRequestFromStudentByRequestFromStudentId(@PathVariable String request_id) {
        return adminAPIService.apiGetRequestFromStudentByRequestFromStudentId(request_id);
    }



    /////////////////// API Delete /////////////////
    // Delete by id itself or username (for Authentication)
    @DeleteMapping(value = "/api/delete/tutor")
    @ResponseBody
    public String apiDeleteTutorByTutorId(@RequestParam String tutor_id) {
        return adminAPIService.apiDeleteTutorByTutorId(tutor_id);
    }

    @DeleteMapping(value = "/api/delete/student")
    @ResponseBody
    public String apiDeleteStudentByStudentId(@RequestParam String student_id) {
        return adminAPIService.apiDeleteStudentByStudentId(student_id);
    }

    @DeleteMapping(value = "/api/delete/tutor-authentication")
    @ResponseBody
    public String apiDeleteTutorAuthenticationByTutorId(@RequestParam String tutor_id) {
        return adminAPIService.apiDeleteTutorAuthenticationByTutorId(tutor_id);
    }

    @DeleteMapping(value = "/api/delete/tutor-authentication-by-username")
    @ResponseBody
    public String apiDeleteTutorAuthenticationByUsername(@RequestParam String username) {
        return adminAPIService.apiDeleteTutorAuthenticationByUsername(username);
    }

    @DeleteMapping(value = "/api/delete/student-authentication")
    @ResponseBody
    public String apiDeleteStudentAuthenticationByStudentId(@RequestParam String student_id) {
        return adminAPIService.apiDeleteStudentAuthenticationByStudentId(student_id);
    }

    @DeleteMapping(value = "/api/delete/student-authentication-by-username")
    @ResponseBody
    public String apiDeleteStudentAuthenticationByUsername(@RequestParam String username) {
        return adminAPIService.apiDeleteStudentAuthenticationByUsername(username);
    }

    @DeleteMapping(value = "/api/delete/class")
    @ResponseBody
    public String apiDeleteClassByClassId(@RequestParam String class_id) {
        return adminAPIService.apiDeleteClassByClassId(class_id);
    }

    @DeleteMapping(value = "/api/delete/period")
    @ResponseBody
    public String apiDeletePeriodByPeriodId(@RequestParam String period_id) {
        return adminAPIService.apiDeletePeriodByPeriodId(period_id);
    }

    @DeleteMapping(value = "/api/delete/request-from-student")
    @ResponseBody
    public String apiDeleteRequestFromStudentByRequestFromStudentId(@RequestParam String request_id) {
        return adminAPIService.apiDeleteRequestFromStudentByRequestFromStudentId(request_id);
    }
}
