package com.example.lab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lab4.data.AppRepository;
import com.example.lab4.model.Course;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
{
    private AppRepository appRepository;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setuprepo();
        setupadapter();
        FloatingActionButton fab = findViewById(R.id.add_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //create edit text
                final View d = getLayoutInflater().inflate(R.layout.add_course, null);
                //add edit text to dialog
                dialog.setView(d);
                //set dialog title
                dialog.setTitle("Add");

                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        //get item entered
                        d.findViewById(R.id.add_course);
                        String newCourse = ((EditText) d.findViewById(R.id.add_course)).getText().toString();
                        String newProfessor = ((EditText) d.findViewById(R.id.add_professor)).getText().toString();
                        String newSemester = ((EditText) d.findViewById(R.id.add_semester)).getText().toString();

                        if (!newCourse.isEmpty())
                        {
                            Course course = new Course(newCourse,newProfessor,newSemester);
                            appRepository.insertCourse(course);
                        }
                        Snackbar.make(v, "Course added", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
    }

    private void setuprepo()
    {
        appRepository = new AppRepository();
    }
    private void setupadapter()
    {
        FirestoreRecyclerOptions<Course> options = appRepository.getOptions();
        courseAdapter = new CourseAdapter(options, this, appRepository);
        recyclerView.setAdapter(courseAdapter);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        courseAdapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        courseAdapter.stopListening();
    }

}
