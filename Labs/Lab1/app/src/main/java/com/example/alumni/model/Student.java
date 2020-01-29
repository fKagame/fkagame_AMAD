package com.example.alumni.model;

public class Student {
    private String name;
    private String picture;
    private String info;
    private String tel;
    private String bio;
    private String program;

    public Student(String name, String picture, String info, String tel, String bio, String program)
    {
        this.name = name;
        this.picture = picture;
        this.info = info;
        this.bio = bio;
        this.tel = tel;
        this.program = program;
    }

    public Student(String name, String info, String bio, String tel,String program)
    {
        this.name = name;
        this.info = info;
        this.bio = bio;
        this.tel = tel;
        this.program = program;
    }

    public Student(String name, String info)
    {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public String getTel()
    {
        return tel;
    }
    public void setBio(String bio)
    {
        this.bio = bio;
    }
    public String getBio()
    {
        return bio;
    }
}
