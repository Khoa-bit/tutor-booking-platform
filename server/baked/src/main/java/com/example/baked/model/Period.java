package com.example.baked.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Period")
public class Period {
    @Field("period_id")
    private String period_id;

    @Field("tutor_id")
    private String tutor_id;

    @Field("student_id")
    private String student_id;

    @Field("start_time")
    private int start_time;

    @Field("end_time")
    private int end_time;

    @Field("day")
    private int day;

    public Period() {
    }

    public String getPeriod_id() {
        return this.period_id;
    }

    public void setPeriod_id(String period_id) {
        this.period_id = period_id;
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

    public int getStart_time() {
        return this.start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
