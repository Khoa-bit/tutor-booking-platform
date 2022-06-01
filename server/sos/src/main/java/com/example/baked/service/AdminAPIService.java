package com.example.baked.service;

import java.util.*;

import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Student;
import com.example.baked.model.StudentAuthentication;
import com.example.baked.model.Tutor;
import com.example.baked.model.TutorAuthentication;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.PeriodRepository;
import com.example.baked.repository.RequestFromStudentRepository;
import com.example.baked.repository.StudentAuthenticationRepository;
import com.example.baked.repository.StudentRepository;
import com.example.baked.repository.TutorAuthenticationRepository;
import com.example.baked.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAPIService {
    /////////////////// Import repositories //////////////
    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TutorAuthenticationRepository tutorAuthenticationRepository;

    @Autowired
    private StudentAuthenticationRepository studentAuthenticationRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private RequestFromStudentRepository requestFromStudentRepository;

    /////////////////// API Service //////////////
    public List<Tutor> apiGetAllTutors() {
        return tutorRepository.findAll();
    }

    public List<Student> apiGetAllStudents() {
        return studentRepository.findAll();
    }

    public List<TutorAuthentication> apiGetAllTutorsAuthentication() {
        return tutorAuthenticationRepository.findAll();
    }

    public List<StudentAuthentication> apiGetAllStudentsAuthentication() {
        return studentAuthenticationRepository.findAll();
    }

    public List<Class> apiGetAllClasses() {
        return classRepository.findAll();
    }

    public List<Period> apiGetAllPeriods() {
        return periodRepository.findAll();
    }

    public List<RequestFromStudent> apiGetAllRequestsFromStudents() {
        return requestFromStudentRepository.findAll();
    }
}
