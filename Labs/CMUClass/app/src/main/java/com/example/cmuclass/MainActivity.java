package com.example.cmuclass;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cmuclass.model.Student;
import com.example.cmuclass.model.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private List<Student> mStudentList = new ArrayList<>();
    private StudentViewModel studentViewModel;
    private StudentListAdapter listAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.addStudent);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View view)
            {
                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //create edit text
                final View d = getLayoutInflater().inflate(R.layout.add_student, null);
                //add edit text to dialog
                dialog.setView(d);
                //set dialog title
                dialog.setTitle("Add");
                //sets OK action
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        //get item entered
                        d.findViewById(R.id.editTName);
                        String newName = ((EditText) d.findViewById(R.id.editTName)).getText().toString();
                        String newProgram = ((EditText) d.findViewById(R.id.editProgram)).getText().toString();
                        String newNationality = ((EditText) d.findViewById(R.id.editNationality)).getText().toString();

                        if (!newName.isEmpty())
                        {
                            Student newStudent = new Student(newName,newProgram,newNationality);
                            // add item
                            mStudentList.add(newStudent);
                            //call new insert method
                            studentViewModel.insertStudent(newStudent);
                        }
                        Snackbar.make(view, "Student added", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });
                //sets cancel action
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        // cancel
                    }
                });
                //present alert dialog
                dialog.show();
            }
        });

        //create a viewmodel
        studentViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(this.getApplication())).get(StudentViewModel.class);

        //get the recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the observer
        final Observer<List<Student>> itemObserver = new Observer<List<Student>>()
        {
            @Override
            public void onChanged(List<Student> students)
            {
                mStudentList.clear();
                mStudentList.addAll(students);

                if (listAdapter == null)
                {
                    //define the adapter
                    listAdapter = new StudentListAdapter(studentViewModel, MainActivity.this);
                    //assign the adapter to the recycle view
                    recyclerView.setAdapter(listAdapter);
                }
                else
                {
                    listAdapter.setStudents(mStudentList);
                    listAdapter.notifyDataSetChanged();
                }
            }
        };
        //set the observer
        studentViewModel.getStudentList().observe(this, itemObserver);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_delete)
        {
            mStudentList.clear();
            studentViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
