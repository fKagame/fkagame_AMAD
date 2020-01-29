package com.example.alumni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumni.model.Student;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>
{
    private List<Student> mStudentList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener
    {
        void onItemClick(int position);
    }

    public MyListAdapter(List<Student> mStudentList, Context mContext,ItemClickListener mItemClickListener)
    {
        this.mStudentList = mStudentList;
        this.mContext = mContext;
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Student student = mStudentList.get(position);
        holder.mTextView.setText(student.getName());
        holder.concentr.setText(student.getProgram());
    }

    @Override
    public int getItemCount()
    {
        return mStudentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView mTextView;
        TextView concentr;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            concentr = itemView.findViewById(R.id.concentration);
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            mItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}
