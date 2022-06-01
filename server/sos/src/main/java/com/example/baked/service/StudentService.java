package com.example.baked.service;

import java.util.*;

import com.example.baked.model.Address;
import com.example.baked.model.Class;
import com.example.baked.model.FullName;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Student;
import com.example.baked.model.StudentAuthentication;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.PeriodRepository;
import com.example.baked.repository.RequestFromStudentRepository;
import com.example.baked.repository.StudentAuthenticationRepository;
import com.example.baked.repository.StudentRepository;
import com.example.baked.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@SessionAttributes("student")
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private StudentAuthenticationRepository studentAuthenticationRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private RequestFromStudentRepository requestFromStudentRepository;

    @Autowired
    private ClassService classService = new ClassService();

    @Autowired
    private GeneratorService generatorService = new GeneratorService();

    @Autowired
    private RequestFromStudentService requestFromStudentService = new RequestFromStudentService();

    @ModelAttribute("student")
    public Student getStudent() {
        return new Student();
    }

    public StudentRepository getStudentRepository() {
        return this.studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentAuthenticationRepository getStudentAuthenticationRepository() {
        return this.studentAuthenticationRepository;
    }

    public void setStudentAuthenticationRepository(StudentAuthenticationRepository studentAuthenticationRepository) {
        this.studentAuthenticationRepository = studentAuthenticationRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<String> getAllStudentsID() {
        List<Student> students = new ArrayList<Student>(studentRepository.findAll());
        List<String> studentsID = new ArrayList<String>();
        for (Student s : students) {
            studentsID.add(s.getStudent_id());
        }
        return studentsID;
    }

    public List<StudentAuthentication> getAllStudentsAuthentication() {
        System.out.println(studentAuthenticationRepository.findAll());
        return studentAuthenticationRepository.findAll();
    }

    public String studentLogin(String username, String password, Model model) {

        if (username == null || password == null) {
            return "student/student-login.html";
        }

        StudentAuthentication studentAuth = studentAuthenticationRepository.getAuthByUsername(username);

        // Password is undefined
        if (studentAuth == null) {
            return "student/student-login.html";
        }

        if (studentAuth.getPassword() == null) {
            return "student/student-login.html";
        }

        // Password is correct!
        if (studentAuth.getPassword().equals(password)) {
            model.addAttribute("student", studentRepository.getStudentByStudentID(studentAuth.getStudent_id()));
            return "redirect:/student-home";
        }

        // Password is incorrect!
        return "student/student-login.html";
    }

    public String studentHome(Model model) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("user", student);
        model.addAttribute("classes", classRepository.getClassByStudentID(student.getStudent_id()));
        model.addAttribute("tutors", tutorRepository.findAll());

        return "student/student-home.html";
    }

    public String getStudentDetailTutor(Model model, String id) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("user", student);
        model.addAttribute("tutor_profile", tutorRepository.getTutorByTutorID(id));

        List<Class> classes = classRepository.getClassByTutorID(id);

        List<String> periods_id = new ArrayList<>();
        for (Class c : classes) {
            for (String p : c.getPeriods()) {
                periods_id.add(p);
            }
        }

        List<Period> full_periods = periodRepository.getPeriodByTutorID(id);

        for (int i = full_periods.size() - 1; i >= 0; i--) {
            for (String p_id : periods_id) {
                if (full_periods.get(i).getPeriod_id().equals(p_id)) {
                    full_periods.remove(i);
                    break;
                }
            }
        }

        model.addAttribute("periods", full_periods);

        return "student/student-detail-tutor.html";
    }

    public String studentRequestTutor(
            String province_city,
            String ward_district,
            String home_number,
            String phone,
            String email,
            String grade,
            String subject,
            List<String> periods,
            int salary,
            String requirement,
            String tutor_id,
            Model model) {

        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        Student student = (Student) model.getAttribute("student");

        Address address = new Address(province_city, ward_district, home_number);
        String request_id = generatorService.generateRequestFromStudentId();
        String student_id = student.getStudent_id();

        List<String> subjects = new ArrayList<>();
        subjects.add(subject);

        String class_id = generatorService.generateClassIdForRequest();
        RequestFromStudent new_request = new RequestFromStudent(request_id, tutor_id, student_id, grade, subjects,
                address, salary, requirement, periods);

        requestFromStudentRepository.save(new_request);

        return "redirect:/student-home";
    }

    public String studentRequest(Model model) {
        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        model.addAttribute("user", model.getAttribute("student"));
        model.addAttribute("requestsfromstudents", requestFromStudentService.getRequestsFromStudentsByStudentId(model));

        return "student/student-request.html";
    }

    public String studentLogout(Model model) {
        return "redirect:/";
    }

    public String studentClassDetail(String class_id, Model model) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }


        model.addAttribute("class", classRepository.getClassByClassID(class_id));

        model.addAttribute("user", student);

        Class current_class = classRepository.getClassByClassID(class_id);

        List<Period> periods = new ArrayList<>();

        for (String p: current_class.getPeriods()) {
            periods.add(periodRepository.getPeriodByPeriodID(p));
        }

        model.addAttribute("periods", periods.toString().replaceAll("(^\\[|\\]$)", ""));


        return "student/student-class-detail.html";
    }

    public String studentClass(Model model) {
        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        Student student = (Student) model.getAttribute("student");

        model.addAttribute("user", model.getAttribute("student"));
        model.addAttribute("classes", classRepository.getClassByStudentID(student.getStudent_id()));
        return "student/student-class.html";
    }

    public String studentProfile(Model model) {
        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        

        Student student = (Student) model.getAttribute("student");
        model.addAttribute("user", studentAuthenticationRepository.getAuthByStudentID(student.getStudent_id()));
        model.addAttribute("student_profile", student);


        return "student/student-profile.html";
    }

}
