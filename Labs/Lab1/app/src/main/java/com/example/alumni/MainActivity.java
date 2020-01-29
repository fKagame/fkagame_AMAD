package com.example.alumni;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.alumni.data.JSONData;
import com.example.alumni.model.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyListAdapter.ItemClickListener
{

   static List<Student> studentList = JSONData.studentList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alumni_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get data
        if (studentList.isEmpty())
        {
            studentList = JSONData.getJSON(this);
        }
        Log.d("List","Student list size"+studentList.size());
        //get the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //define an adapter
        MyListAdapter adapter = new MyListAdapter(studentList, this, this);

        //assign the adapter to the recycle view
        recyclerView.setAdapter(adapter);

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int position)
    {
        Intent intent = new Intent(MainActivity.this,Profile.class);
        intent.putExtra("id", position);
        startActivity(intent);
    }
}
