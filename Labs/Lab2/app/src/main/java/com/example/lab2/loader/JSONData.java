package com.example.lab2.loader;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2.model.DataReady;
import com.example.lab2.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONData
{
    private static String site = "https://api.weatherbit.io/v2.0/current?city=";
    private static String api_key = "e3bc625e2c93485e8fcd0f755a0f6ef7";
    private static String city;
    public static Weather weather = new Weather();


    public JSONData(String city)
    {
        this.city = city;
    }

    public static void setCity(String city)
    {
        JSONData.city = city;
    }

    public static String getCity()
    {
        return city;
    }

    public static void getJSON(Context context, final DataReady dataReady)
    {

        String url = site +  city + "&key=" + api_key;
        // create Volley request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                parseJSON(response);
                dataReady.whenReady(weather);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage(), error);
            }
        });
        queue.add(stringRequest);
    }

    private static Weather parseJSON(String response)
    {
        if (response != null) {
            try {
                Log.d("Response",response);
                //create JSONObject
                JSONObject jsonObject = new JSONObject(response);

                //create JSONArray with the value from the characters key
                JSONArray resultsArray = jsonObject.getJSONArray("data");

                //loop through each object in the array
                JSONObject dataObj = resultsArray.getJSONObject(0);

                //get values
                String sunRise = "Sunrise at :"+dataObj.getString("sunrise");
                String temperature = dataObj.getString("temp");
                String date = "Time of record: "+dataObj.getString("datetime");

                JSONObject weatherObj = dataObj.getJSONObject("weather");
                String w_icon = weatherObj.getString("icon");
                String w_description = weatherObj.getString("description");
                String wind_direction = "Wind direction: "+dataObj.getInt("wind_dir")+"";

                weather.setSunRise(sunRise);
                weather.setWeatherDescription(w_description);
                weather.setCity(city);
                weather.setId(0);
                weather.setWeatherTime(date);
                weather.setIcon(w_icon);
                weather.setSunRise(sunRise);
                weather.setWindDirection(wind_direction);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return weather;
    }

}
