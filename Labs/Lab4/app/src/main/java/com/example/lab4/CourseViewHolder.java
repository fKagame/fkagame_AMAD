package com.example.lab4;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseViewHolder extends RecyclerView.ViewHolder
{
    private TextView course_name, instructor_name, semester;

    public CourseViewHolder(@NonNull View itemView)
    {
        super(itemView);
        course_name = itemView.findViewById(R.id.txt_name);
        instructor_name = itemView.findViewById(R.id.txt_professor);
        semester = itemView.findViewById(R.id.txt_semester);
    }

    public void setCourse(String courseName, String instr, String sem)
    {
        course_name.setText(courseName);
        instructor_name.setText(instr);
        semester.setText(sem);
    }



}
