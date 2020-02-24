package com.example.cmuclass.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cmuclass.model.Student;

import java.util.List;

@Dao
public interface StudentDAO
{
    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("DELETE FROM Student")
    int deleteAll();
}
