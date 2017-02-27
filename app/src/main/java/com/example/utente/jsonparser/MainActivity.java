package com.example.utente.jsonparser;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Utente on 27/02/2017.
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView studentsRv;
    LinearLayoutManager layoutManager;
    StudentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentsRv = (RecyclerView) findViewById(R.id.students_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new StudentAdapter();
        studentsRv.setLayoutManager(layoutManager);
        studentsRv.setAdapter(adapter);
        fetchStudentFromJSON();
    }

    private void fetchStudentFromJSON(){
        ArrayList<Student> students = new ArrayList<>();
        try{
            JSONArray studentsJSONArray = new JSONArray(readLocalJson());
            for (int i = 0; i < studentsJSONArray.length(); i++) {
                JSONObject jsonStudent = studentsJSONArray.getJSONObject(i);
                students.add(new Student(jsonStudent));
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        adapter.setDataSet(students);
    }

    private String readLocalJson(){
        Writer writer = new  StringWriter();
        char [] buffer = new char[1024];
        try(InputStream is = getResources().openRawResource(R.raw.students)){
            Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            int n;
            while((n = reader.read(buffer))!=-1){
                writer.write(buffer,0,n);
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return writer.toString();

    }
}
