package com.example.lab4.model;

public class Course
{
    private String courseName;
    private String instructor;
    private String semester;

    public Course()
    {
        /*courseName = "Pfun";
        instructor = "Cathy";
        semester = "Fall";*/
    }

    public Course(String courseName, String instructor, String semester)
    {
        this.courseName = courseName;
        this.instructor = instructor;
        this.semester = semester;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getInstructor()
    {
        return instructor;
    }

    public void setInstructor(String instructor)
    {
        this.instructor = instructor;
    }

    public String getSemester()
    {
        return semester;
    }

    public void setSemester(String semester)
    {
        this.semester = semester;
    }
}
