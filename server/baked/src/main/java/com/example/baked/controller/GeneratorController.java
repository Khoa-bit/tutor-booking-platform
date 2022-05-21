package com.example.baked.controller;

import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.RequestFromTutor;
import com.example.baked.model.Student;
import com.example.baked.model.Tutor;
import com.example.baked.repo.ClassRepository;
import com.example.baked.repo.PeriodRepository;
import com.example.baked.repo.RequestFromStudentRepository;
import com.example.baked.repo.RequestFromTutorRepository;
import com.example.baked.repo.StudentRepository;
import com.example.baked.repo.TutorRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GeneratorController {

  @Autowired private StudentRepository studentRepository;

  @Autowired private TutorRepository tutorRepository;

  @Autowired private ClassRepository classRepository;

  @Autowired private PeriodRepository periodRepository;

  @Autowired private RequestFromStudentRepository requestFromStudentRepository;

  @Autowired private RequestFromTutorRepository requestFromTutorRepository;

  @GetMapping(value = "api/test/studentId")
  @ResponseBody
  public List<String> getStudentIdList() {
    List<Student> students = studentRepository.findAll();
    List<String> studentIdList = new ArrayList<>();

    for (Student s : students) {
      studentIdList.add(s.getStudent_id());
    }

    return studentIdList;
  }

  @GetMapping(value = "api/test/tutorId")
  @ResponseBody
  public List<String> getTutorIdList() {
    List<Tutor> tutors = tutorRepository.findAll();
    List<String> tutorIdList = new ArrayList<>();

    for (Tutor s : tutors) {
      tutorIdList.add(s.getTutor_id());
    }

    return tutorIdList;
  }

  @GetMapping(value = "api/test/classId")
  @ResponseBody
  public List<String> getClassIdList() {
    List<Class> classes = classRepository.findAll();
    List<String> classIdList = new ArrayList<>();

    for (Class s : classes) {
      classIdList.add(s.getClass_id());
    }

    return classIdList;
  }

  @GetMapping(value = "api/test/periodId")
  @ResponseBody
  public List<String> getPeriodIdList() {
    List<Period> periods = periodRepository.findAll();
    List<String> periodIdList = new ArrayList<>();

    for (Period s : periods) {
      periodIdList.add(s.getPeriod_id());
    }

    return periodIdList;
  }

  @GetMapping(value = "api/test/reqFrStudentId")
  @ResponseBody
  public List<String> getRequestFromStudentIdList() {
    List<RequestFromStudent> requestFromStudents = requestFromStudentRepository.findAll();
    List<String> requestFromStudentList = new ArrayList<>();

    for (RequestFromStudent s : requestFromStudents) {
      requestFromStudentList.add(s.getRequest_id());
    }

    return requestFromStudentList;
  }

  @GetMapping(value = "api/test/reqFrTutorId")
  @ResponseBody
  public List<String> getRequestFromTutorIdList() {
    List<RequestFromTutor> requestFromTutors = requestFromTutorRepository.findAll();
    List<String> requestFromTutorList = new ArrayList<>();

    for (RequestFromTutor s : requestFromTutors) {
      requestFromTutorList.add(s.getRequest_id());
    }

    return requestFromTutorList;
  }

  @GetMapping(value = "api/test/generator")
  @ResponseBody
  public String idGenerator() {
    Random random = new Random();

    int min1 = 48;
    int max1 = 57;
    int min2 = 97;
    int max2 = 122;

    int length = 5;

    String str = "";
    for (int i = 0; i < length; i++) {
      boolean is_number = random.nextBoolean();
      if (is_number) {
        char c = (char) (random.nextInt(max1 - min1 + 1) + min1);
        str += Character.toString(c);
      } else {
        char c = (char) (random.nextInt(max2 - min2 + 1) + min2);
        str += Character.toString(c);
      }
    }

    return str;
  }

  /////////////////// Generate id ////////////////////////
  @GetMapping(value = "api/test/generator/studentId")
  @ResponseBody
  public String generateStudentId() {
    String id = "S";
    List<String> studentIdList = getStudentIdList();
    while (true) {
      id += idGenerator();
      for (String exist_id : studentIdList) {
        if (exist_id.equals(id)) {
          continue;
        }
      }
      break;
    }
    return id;
  }

  @GetMapping(value = "api/test/generator/tutorId")
  @ResponseBody
  public String generateTutorId() {
    String id = "T";
    List<String> tutorIdList = getTutorIdList();
    while (true) {
      id += idGenerator();
      for (String exist_id : tutorIdList) {
        if (exist_id.equals(id)) {
          continue;
        }
      }
      break;
    }
    return id;
  }

  @GetMapping(value = "api/test/generator/classId")
  @ResponseBody
  public String generateClassId() {
    String id = "C";
    List<String> classIdList = getClassIdList();
    while (true) {
      id += idGenerator();
      for (String exist_id : classIdList) {
        if (exist_id.equals(id)) {
          continue;
        }
      }
      break;
    }
    return id;
  }

  @GetMapping(value = "api/test/generator/periodId")
  @ResponseBody
  public String generatePeriodId() {
    String id = "P";
    List<String> periodIdList = getPeriodIdList();
    while (true) {
      id += idGenerator();
      for (String exist_id : periodIdList) {
        if (exist_id.equals(id)) {
          continue;
        }
      }
      break;
    }
    return id;
  }

  @GetMapping(value = "api/test/generator/requestFromStudentId")
  @ResponseBody
  public String generateRequestFromStudentId() {
    String id = "RS";
    List<String> requestFromStudentIdList = getRequestFromStudentIdList();
    while (true) {
      id += idGenerator();
      for (String exist_id : requestFromStudentIdList) {
        if (exist_id.equals(id)) {
          continue;
        }
      }
      break;
    }
    return id;
  }

  @GetMapping(value = "api/test/generator/requestFromTutorId")
  @ResponseBody
  public String generateRequestFromTutorId() {
    String id = "RT";
    List<String> requestFromTutorIdList = getRequestFromTutorIdList();
    while (true) {
      id += idGenerator();
      for (String exist_id : requestFromTutorIdList) {
        if (exist_id.equals(id)) {
          continue;
        }
      }
      break;
    }
    return id;
  }
}
