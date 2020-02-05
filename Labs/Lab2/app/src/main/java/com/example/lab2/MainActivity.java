package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lab2.loader.JSONData;
import com.example.lab2.model.DataReady;
import com.example.lab2.model.Weather;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DataReady
{


   private String city;
   private  Spinner spinner;
   private  ImageView iv, iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.city_image);
        iv2 = (ImageView) findViewById(R.id.weather_icon);
        spinner = findViewById(R.id.spinner_city);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.EACities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        city = spinner.getSelectedItem().toString();
        JSONData.setCity(city);
        Weather weather = JSONData.weather;
        JSONData.getJSON(this,this);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public void whenReady(Weather weather)
    {

        TextView dscr,sRise,wind;
        if(city.equalsIgnoreCase("kigali"))
            iv.setImageResource(R.drawable.kigali);
        else if(city.equalsIgnoreCase("Dar es Salama"))
            iv.setImageResource(R.drawable.tz);
        else if(city.equalsIgnoreCase("kampala"))
            iv.setImageResource(R.drawable.kampala);
        else if(city.equalsIgnoreCase("nairobi"))
            iv.setImageResource(R.drawable.nairobi);
        else if(city.equalsIgnoreCase("bujumbura"))
            iv.setImageResource(R.drawable.bujumbura);
        else if(city.equalsIgnoreCase("mombasa"))
            iv.setImageResource(R.drawable.nairobi);
        else
            iv.setImageResource(R.drawable.kigali);

        dscr = findViewById(R.id.txt_descr);
        sRise = findViewById(R.id.txt_sun);
        wind = findViewById(R.id.txt_wind);

        dscr.setText(weather.getWeatherDescription());
        sRise.setText(weather.getSunRise());
        wind.setText(weather.getWindDirection());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        int orientation =  this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
        {

        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            iv.getLayoutParams().height = 200;
            iv.getLayoutParams().width = 200;
            iv2.getLayoutParams().height = 100;
            iv2.getLayoutParams().width = 100;
        }

    }

}
