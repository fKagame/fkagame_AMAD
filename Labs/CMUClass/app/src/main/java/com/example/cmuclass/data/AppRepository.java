package com.example.cmuclass.data;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.cmuclass.model.Student;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository
{
    private static AppRepository mInstance;

    private AppDatabase mAppDatabase;

    //for background thread
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context)
    {
        if (mInstance == null){
            mInstance = new AppRepository(context);
        }
        return mInstance;
    }

    private AppRepository(Context context)
    {
        mAppDatabase = AppDatabase.getInstance(context);
    }

    public LiveData<List<Student>> getAllStudents()
    {
        return mAppDatabase.studentDAO().getAllStudents();
    }

    public void insertStudent(final Student newStudent)
    {
        executor.execute(new Runnable() {
            @Override
            public void run()
            {
                mAppDatabase.studentDAO().insertStudent(newStudent);
                Log.d("Log:--","Student has been inserted");
            }
        });
    }

    public void deleteStudent(final Student newStudent)
    {
        executor.execute(new Runnable() {
            @Override
            public void run()
            {
                mAppDatabase.studentDAO().deleteStudent(newStudent);
                Log.d("Log:--","A student has been deleted");
            }
        });
    }

    public void deleteAll()
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.studentDAO().deleteAll();
            }
        });
        Log.d("Log:--","All student have been deleted");
    }
}
