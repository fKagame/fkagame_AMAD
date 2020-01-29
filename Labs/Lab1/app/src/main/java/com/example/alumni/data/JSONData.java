package com.example.alumni.data;

import android.content.Context;

import com.example.alumni.R;
import com.example.alumni.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONData
{
    public static List<Student> studentList;

    static {
        studentList = new ArrayList<>();
    }

    public static List<Student> getJSON(Context context){
        String json = null;
        json = loadJSONFromRes(context); //load JSON from resource
        studentList = parseJSON(json); //parse JSON
        return studentList;
    }

    private static String loadJSONFromRes(Context context)
    {
        //opens the raw JSON file and assigns it to an InputStream instance
        InputStream inputStream = context.getResources().openRawResource(R.raw.studentlist);
        //stores the JSON as a String
        String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();

        // https://stackoverflow.com/questions/6349759/using-json-file-in-android-app-resources
        // This uses the Java class Scanner, leading to less lines of code than some other methods of reading a simple text / json file. The delimiter pattern \A means 'the beginning of the input'. .next() reads the next token, which is the whole file in this case.

//        InputStream inputStream = null;
//        String jsonString = null;
//        try {
//            inputStream = context.getResources().openRawResource(R.raw.harrypotter);
//
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            jsonString = new String(buffer, "UTF-8");
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return jsonString;
    }

    private static List<Student> parseJSON(String jsonString)
    {
        if (jsonString != null) {
            try {
                //create JSONObject
                JSONObject jsonObject = new JSONObject(jsonString);

                //create JSONArray with the value from the characters key
                JSONArray characterArray = jsonObject.getJSONArray("Student");

                //loop through each object in the array
                for (int i =0; i < characterArray.length(); i++) {
                    JSONObject charObject = characterArray.getJSONObject(i);

                    //get values for name and info keys
                    String name = charObject.getString("name");
                    String info = charObject.getString("info");
                    String bio = charObject.getString("bio");
                    String tel = charObject.getString("tel");
                    String program = charObject.getString("program");

                    //create new Character object
                    Student std = new Student(name, info, bio, tel, program);

                    //add character object to our ArrayList
                    studentList.add(std);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return studentList;
    }
}
