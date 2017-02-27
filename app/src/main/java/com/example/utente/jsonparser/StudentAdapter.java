package com.example.utente.jsonparser;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Utente on 27/02/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter <StudentAdapter.StudentViewHolder> {
    private ArrayList<Student> dataSet = new ArrayList<>();

    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        StudentViewHolder holder = new StudentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StudentAdapter.StudentViewHolder holder, int position) {
        Student student = dataSet.get(position);
        holder.studentNameTv.setText(student.getName());
        holder.studentEmailTv.setText(student.getEmail());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    public void setDataSet(ArrayList<Student> students){
        dataSet = students;
        notifyDataSetChanged();
    }
    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameTv, studentEmailTv;
        ImageButton studentGitHub;
        public StudentViewHolder(final View v){
            super(v);

            studentNameTv = (TextView) v.findViewById(R.id.student_name);
            studentEmailTv = (TextView) v.findViewById(R.id.student_email);
            studentGitHub = (ImageButton) v.findViewById(R.id.student_github);
            studentGitHub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(dataSet.get(getAdapterPosition()).getGithub()));
                    itemView.getContext().startActivity(i);
                }
            });

        }
    }
}
