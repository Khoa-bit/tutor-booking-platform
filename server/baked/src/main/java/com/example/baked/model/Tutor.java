package com.example.baked.model;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Tutor")
public class Tutor {
    @Field("tutor_id")
    private String tutor_id;

    @Field("fullname")
    private FullName fullname;

    @Field("gender")
    private String gender;

    @Field("date_of_birth")
    private Birth date_of_birth;

    @Field("address")
    private Address address;

    @Field("emails")
    private List<String> emails;

    @Field("phones")
    private List<String> phones;

    @Field("job")
    private String job;

    @Field("graduated_school")
    private String graduated_school;

    @Field("major")
    private String major;

    @Field("qualification")
    private String qualification;

    @Field("graduated_year")
    private int graduated_year;

    @Field("grades")
    private List<String> grades;

    @Field("subjects")
    private List<String> subjects;

    @Field("minimum_salary_requirement")
    private int minimum_salary_requirement;

    @Field("teaching_classes")
    private List<String> teaching_classes;

    @Field("about")
    private String about;

    @Field("periods")
    private List<String> periods;

    @Field("requestCount")
    private int requestCount;

    public Tutor() {
    }

    public String getTutor_id() {
        return this.tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
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

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGraduated_school() {
        return this.graduated_school;
    }

    public void setGraduated_school(String graduated_school) {
        this.graduated_school = graduated_school;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getQualification() {
        return this.qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getGraduated_year() {
        return this.graduated_year;
    }

    public void setGraduated_year(int graduated_year) {
        this.graduated_year = graduated_year;
    }

    public List<String> getGrades() {
        return this.grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    public List<String> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public int getMinimum_salary_requirement() {
        return this.minimum_salary_requirement;
    }

    public void setMinimum_salary_requirement(int minimum_salary_requirement) {
        this.minimum_salary_requirement = minimum_salary_requirement;
    }

    public List<String> getTeaching_classes() {
        return this.teaching_classes;
    }

    public void setTeaching_classes(List<String> teaching_classes) {
        this.teaching_classes = teaching_classes;
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
