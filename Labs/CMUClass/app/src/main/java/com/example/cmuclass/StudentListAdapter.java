package com.example.cmuclass;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cmuclass.model.*;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder>
{
    private List<Student> mStudentList;
    private StudentViewModel mStudentViewModel;
    private Context mContext;

    public StudentListAdapter(StudentViewModel mStudentViewModel, Context mContext)
    {
        this.mStudentViewModel = mStudentViewModel;
        mStudentList = mStudentViewModel.getStudentList().getValue();
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public StudentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View studentView = inflater.inflate(R.layout.student_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentListAdapter.ViewHolder holder, int position)
    {
        final Student student = mStudentList.get(position);
        holder.txtName.setText(student.getStudentName());
        holder.txtProgram.setText(student.getProgram());
        holder.txtNationality.setText(student.getNationality());

        //context menu
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()
        {
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo)
            {
                //set the menu title
                menu.setHeaderTitle("Delete " + student.getStudentName());
                //add the choices to the menu
                menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = holder.getAdapterPosition();
                        Student removeStudent = mStudentList.remove(position);
                        mStudentViewModel.deleteStudent(removeStudent);
                        Snackbar.make(v, "Student removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return false;
                    }
                });
                menu.add(2, 2, 2, "No");
            }
        });

    }

    @Override
    public int getItemCount()
    {
        if (mStudentList != null)
            return mStudentList.size();
        else
            return 0;
    }

    public void setStudents(List<Student> students)
    {
        mStudentList = students;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName,txtNationality, txtProgram;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtNationality = itemView.findViewById(R.id.txt_nationality);
            txtProgram = itemView.findViewById(R.id.txt_program);
        }
    }
}
