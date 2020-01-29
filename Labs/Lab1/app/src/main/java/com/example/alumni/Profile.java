package com.example.alumni;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.example.alumni.data.JSONData;
import com.example.alumni.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumni.R;

public class Profile extends AppCompatActivity
{
    private FloatingActionButton btn, cal;
    private TextView progr;
    private TextView bio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        int id = (Integer)getIntent().getExtras().get("id");
        Student student = JSONData.studentList.get(id);
        final String phone = student.getTel();
        String program = student.getProgram();
        String bioData = student.getBio();
        final String web = student.getInfo();
        String name = student.getName();

        btn = findViewById(R.id.profile);
        progr = findViewById(R.id.prg);
        bio = findViewById(R.id.summary);
        cal = findViewById(R.id.call);

        progr.setText(program+", Phone Number: "+phone);
        bio.setText(name+" :  "+bioData);

        View.OnClickListener click = new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone));

                if (ActivityCompat.checkSelfPermission(Profile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(),"Enable call permission please",Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(callIntent);
            }

        };
        cal.setOnClickListener(click);


        //listener
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Profile.this,LinkedIn.class);
                intent.putExtra("web", web);
                startActivity(intent);
            }
        };
        btn.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alumni_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
