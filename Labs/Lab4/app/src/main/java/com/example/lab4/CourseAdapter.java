package com.example.lab4;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.lab4.data.AppRepository;
import com.example.lab4.model.Course;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

public class CourseAdapter extends FirestoreRecyclerAdapter<Course,CourseViewHolder>
{
    private Context mContext;
    private AppRepository mappRepository;

    public CourseAdapter(FirestoreRecyclerOptions<Course> options, Context context, AppRepository appRepository)
    {
        super(options);
        this.mContext = context;
        this.mappRepository = appRepository;
    }

    @Override
    protected void onBindViewHolder(@NonNull final CourseViewHolder courseHolder, int i, @NonNull final Course course)
    {
        courseHolder.setCourse(course.getCourseName(), course.getInstructor(),course.getSemester());

        //context menu
        courseHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
                //set the menu title
                menu.setHeaderTitle("Delete " + course.getCourseName());

                //add the choices to the menu
                menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        //get recipe that was pressed
                        int position = courseHolder.getAdapterPosition();

                        //get document id
                        String id = getSnapshots().getSnapshot(position).getId();

                        //delete from repository
                        mappRepository.deleteCourse(id);

                        Snackbar.make(v, "Course removed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        return false;
                    }
                });
                menu.add(2, 2, 2, "No");
            }
        });
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.course_list, parent, false);
        CourseViewHolder recipeHolder = new CourseViewHolder(itemView);
        return recipeHolder;
    }
}
