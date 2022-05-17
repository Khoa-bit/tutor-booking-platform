package com.example.baked.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Student")
public class Student {
    @Field("student_id")
    private String student_id;

    @Field("fullname")
    private FullName fullname;

    @Field("gender")
    private String gender;

    @Field("date_of_birth")
    private Birth date_of_birth;

    @Field("address")
    private Address address;

    @Field("parent_name")
    private FullName parent_name;

    @Field("emails")
    private List<String> emails;

    @Field("phones")
    private List<String> phones;

    @Field("classes")
    private List<String> classes;

    @Field("about")
    private String about;

    @Field("periods")
    private List<String> periods;

    public Student() {
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public FullName getFullname() {
        return this.fullname;
    }

    public void setFullname(FullName fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Birth getDate_of_birth() {
        return this.date_of_birth;
    }

    public void setDate_of_birth(Birth date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FullName getParent_name() {
        return this.parent_name;
    }

    public void setParent_name(FullName parent_name) {
        this.parent_name = parent_name;
    }

    public List<String> getEmails() {
        return this.emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return this.phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getClasses() {
        return this.classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getPeriods() {
        return this.periods;
    }

    public void setPeriods(List<String> periods) {
        this.periods = periods;
    }

}
