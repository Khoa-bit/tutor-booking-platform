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

    /////////////////// API Service (Read) //////////////
    // Read all
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

    // Read by id itself or username (for authentication)
    public Tutor apiGetTutorByTutorId(String tutor_id) {
        return tutorRepository.getTutorByTutorID(tutor_id);
    }

    public Student apiGetStudentByStudentId(String student_id) {
        return studentRepository.getStudentByStudentID(student_id);
    }

    public TutorAuthentication apiGetTutorAuthenticationByTutorId(String tutor_id) {
        return tutorAuthenticationRepository.getAuthByTutorID(tutor_id);
    }

    public TutorAuthentication apiGetTutorAuthenticationByUsername(String username) {
        return tutorAuthenticationRepository.getAuthByUsername(username);
    }

    public StudentAuthentication apiGetStudentAuthenticationByStudentId(String student_id) {
        return studentAuthenticationRepository.getAuthByStudentID(student_id);
    }

    public StudentAuthentication apiGetStudentAuthenticationByUsername(String username) {
        return studentAuthenticationRepository.getAuthByUsername(username);
    }

    public Class apiGetClassByClassId(String class_id) {
        return classRepository.getClassByClassID(class_id);
    }

    public Period apiGetPeriodByPeriodId(String period_id) {
        return periodRepository.getPeriodByPeriodID(period_id);
    }

    public RequestFromStudent apiGetRequestFromStudentByRequestFromStudentId(String request_id) {
        return requestFromStudentRepository.getRequestByRequestID(request_id);
    }

    /////////////////// API Service (Delete) //////////////
    // Delete by id itself or username (for Authentication)
    public String apiDeleteTutorByTutorId(String tutor_id) {
        if (tutorRepository.deleteTutorByTutorID(tutor_id) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeleteStudentByStudentId(String student_id) {
        if (studentRepository.deleteStudentByStudentID(student_id) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeleteTutorAuthenticationByTutorId(String tutor_id) {
        if (tutorAuthenticationRepository.deleteAuthByTutorID(tutor_id) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeleteTutorAuthenticationByUsername(String username) {
        if (tutorAuthenticationRepository.deleteAuthByUsername(username) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeleteStudentAuthenticationByStudentId(String student_id) {
        if (studentAuthenticationRepository.deleteAuthByStudentID(student_id) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeleteStudentAuthenticationByUsername(String username) {
        if (studentAuthenticationRepository.deleteAuthByUsername(username) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeleteClassByClassId(String class_id) {
        if (classRepository.deleteClassByClassID(class_id) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeletePeriodByPeriodId(String period_id) {
        if (periodRepository.deletePeriodByPeriodID(period_id) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }

    public String apiDeleteRequestFromStudentByRequestFromStudentId(String request_id) {
        if (requestFromStudentRepository.deleteRequestByRequestID(request_id) == null) {
            return "Error!";
        } else {
            return "OK";
        }
    }
}
