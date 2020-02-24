package com.example.cmuclass.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cmuclass.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context)
    {
        if (instance == null){
            synchronized (new Object())
            {
                if (instance == null)
                {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase.db").build();
                }
            }
        }
        return instance;
    }
    public abstract StudentDAO studentDAO();
}
