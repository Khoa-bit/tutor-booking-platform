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

    public RequestFromTutor() {
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getTutor_id() {
        return this.tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getClass_id() {
        return this.class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<String> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRequirement() {
        return this.requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public List<String> getPeriods() {
        return this.periods;
    }

    public void setPeriods(List<String> periods) {
        this.periods = periods;
    }

}
