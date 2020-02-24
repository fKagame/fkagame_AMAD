package com.example.cmuclass.model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cmuclass.data.AppRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel
{
    private LiveData<List<Student>> studentList;
    private Context context;
    private AppRepository appRepository;

    public StudentViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();
        appRepository = AppRepository.getInstance(context);
        studentList = appRepository.getAllStudents();

    }

    public LiveData<List<Student>> getStudentList()
    {
        return studentList;
    }

    public void insertStudent(Student newStudent)
    {
        studentList.getValue().add(newStudent);
        appRepository.insertStudent(newStudent);
    }

    public void deleteStudent(Student student)
    {
        appRepository.deleteStudent(student);
    }

    public void deleteAll()
    {
        appRepository.deleteAll();
    }
}
