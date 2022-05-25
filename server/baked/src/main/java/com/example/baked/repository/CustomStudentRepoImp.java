package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class CustomStudentRepoImp implements CustomStudentRepo{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public String getStudentByID(String student) {
        // TODO Auto-generated method stub
        Query query = new Query();
        if (student != "")
            query.addCriteria(Criteria.where("student_id").is(student));
        List<Student> std = mongoTemplate.find(query, Student.class, "Student");
        ObjectMapper objectMapper = new ObjectMapper();
        String str = "";
        try {
            str = objectMapper.writeValueAsString(std.get(0));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }
    
}
