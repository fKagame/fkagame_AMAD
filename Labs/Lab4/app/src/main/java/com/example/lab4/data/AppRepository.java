package com.example.lab4.data;

import com.example.lab4.model.Course;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AppRepository
{

    // Cloud Firestore instance
    private FirebaseFirestore db;

    //recipe collection
    private CollectionReference courseref;

    public AppRepository()
    {
        db = FirebaseFirestore.getInstance();
        courseref = db.collection("courses");
    }

    //options to set up the adapter
    public FirestoreRecyclerOptions<Course> getOptions()
    {
        Query myquery = courseref;
        FirestoreRecyclerOptions<Course> options = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(myquery, Course.class)
                .build();
        return options;
    }

    public void insertCourse(Course newCourse)
    {
        courseref.add(newCourse);
    }

    public void deleteCourse(String id)
    {
        courseref.document(id).delete();
    }
}
