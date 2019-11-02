package com.example.atakan.fitnessgunlukleri;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atakan.fitnessgunlukleri.Database.DatabaseAccess;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseOpenHelper;
import com.example.atakan.fitnessgunlukleri.Models.ReadyPageModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadyProgram extends AppCompatActivity implements View.OnClickListener{
    TextView text_baslangic,text_ortaseviye1,text_ortaseviye2,text_ortaseviye3,text_ortaseviye4;
    List liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_program);
        text_baslangic = (TextView)findViewById(R.id.text_baslangic);
        text_ortaseviye1 = (TextView)findViewById(R.id.text_ortaseviye1);
        text_ortaseviye2 = (TextView)findViewById(R.id.text_ortaseviye2);
        text_ortaseviye3 = (TextView)findViewById(R.id.text_ortaseviye3);
        text_ortaseviye4 = (TextView)findViewById(R.id.text_ortaseviye4);
        text_baslangic.setOnClickListener(this);
        text_ortaseviye1.setOnClickListener(this);
        text_ortaseviye2.setOnClickListener(this);
        text_ortaseviye3.setOnClickListener(this);
        text_ortaseviye4.setOnClickListener(this);
        liste = new ArrayList<>();


    }




    @Override
    public void onClick(View view) {
        if(view.getId() == text_baslangic.getId()){
            Intent i = new Intent(ReadyProgram.this,ReadyProgramList.class);
            liste = ReadyYardimci(text_baslangic.getText().toString());
            i.putExtra("tablename", (Serializable) text_baslangic.getText());
            i.putStringArrayListExtra("liste", (ArrayList<String>) liste);
            startActivity(i);
        }
        else if(view.getId() == text_ortaseviye1.getId()){
            Intent i = new Intent(ReadyProgram.this,ReadyProgramList.class);
            String s = (String) text_ortaseviye1.getText();
            s= s.replace(" ","");
            liste = ReadyYardimci(s);
            i.putExtra("tablename", s);
            i.putStringArrayListExtra("liste", (ArrayList<String>) liste);
            startActivity(i);
        }
        else if(view.getId() == text_ortaseviye2.getId()){
            Intent i = new Intent(ReadyProgram.this,ReadyProgramList.class);
            String s = (String) text_ortaseviye2.getText();
            s= s.replace(" ","");
            liste = ReadyYardimci(s);
            i.putExtra("tablename", s);
            i.putStringArrayListExtra("liste", (ArrayList<String>) liste);
            startActivity(i);
        }
        else if(view.getId() == text_ortaseviye3.getId()){
            Intent i = new Intent(ReadyProgram.this,ReadyProgramList.class);
            String s = (String) text_ortaseviye3.getText();
            s= s.replace(" ","");
            liste = ReadyYardimci(s);
            i.putExtra("tablename", s);
            i.putStringArrayListExtra("liste", (ArrayList<String>) liste);
            startActivity(i);
        }
        else if(view.getId() == text_ortaseviye4.getId()){
            Intent i = new Intent(ReadyProgram.this,ReadyProgramList.class);
            String s = (String) text_ortaseviye4.getText();
            s= s.replace(" ","");
            liste = ReadyYardimci(s);
            i.putExtra("tablename", s);
            i.putStringArrayListExtra("liste", (ArrayList<String>) liste);
            startActivity(i);
        }
    }
    public  List ReadyYardimci(String tablename)
    {
        DatabaseOpenHelper DatabaseOpenHelper = new DatabaseOpenHelper(this);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Cursor cursor = databaseAccess.getQuotes(tablename);

        List model = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            model.add(cursor.getString(1));

            cursor.moveToNext();
        }
        cursor.close();
        return model;
    }
}
