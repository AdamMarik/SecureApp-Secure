package com.bmiapp;

import java.sql.Timestamp;

public class Input {
    private int id;
    private int userId;
    private double weight;
    private double height;
    private int age;
    private String gender;
    private Timestamp date;
    private double bmi;
    private double bodyFatPercentage;

    // Constructors, getters, and setters

    public Input(Integer id, int userId, double weight, double height, int age, String gender, Timestamp date, double bodyFatPercentage, double bmi) {
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.date = date;
        this.bmi = bmi;
        this.bodyFatPercentage = bodyFatPercentage;
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(double bodyFatPercentage) {
        this.bodyFatPercentage = bodyFatPercentage;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
