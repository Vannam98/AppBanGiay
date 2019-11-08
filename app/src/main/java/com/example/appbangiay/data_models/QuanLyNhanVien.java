package com.example.appbangiay.data_models;

public class QuanLyNhanVien {
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String job;



    public QuanLyNhanVien() {

    }

    public QuanLyNhanVien(String fullName, String username, String email, String password,String gender, String job) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.job = job;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

