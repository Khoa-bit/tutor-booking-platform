package com.example.baked.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "RequestFromTutor")
public class RequestFromTutor {
    @Field("request_id")
    private String request_id;

    @Field("tutor_id")
    private String tutor_id;

    @Field("student_id")
    private String student_id;

    @Field("class_id")
    private String class_id;

    @Field("grade")
    private String grade;

    @Field("subjects")
    private List<String> subjects;

    @Field("address")
    private Address address;

    @Field("salary")
    private int salary;

    @Field("requirement")
    private String requirement;

    @Field("periods")
    private List<String> periods;
}
