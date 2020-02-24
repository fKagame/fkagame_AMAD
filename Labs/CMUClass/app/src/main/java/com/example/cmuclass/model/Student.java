package com.example.cmuclass.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity public class Student
{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String studentName;
    private String program;
    private String nationality;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public void setProgram(String program)
    {
        this.program = program;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public Student()
    {
        studentName = "Tartan";
        program = "CMU";
        nationality = "African";
    }
    public Student(String name, String program, String nationality)
    {
        this.studentName = name;
        this.program = program;
        this.nationality = nationality;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public String getProgram()
    {
        return program;
    }

    public String getNationality()
    {
        return nationality;
    }
}
