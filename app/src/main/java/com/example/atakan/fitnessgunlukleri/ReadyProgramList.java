package com.example.atakan.fitnessgunlukleri;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atakan.fitnessgunlukleri.Adapters.ReadyAdapter;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseAccess;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseOpenHelper;
import com.example.atakan.fitnessgunlukleri.Models.ReadyPageModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadyProgramList extends AppCompatActivity{

    ListView listView;
    ReadyAdapter readyAdapter;
    List<String> strings;
    List<String> strings2;
    String tablename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_program_list);

        Intent i = getIntent();

        tablename = i.getStringExtra("tablename");
        listView = (ListView)findViewById(R.id.readyliste);

        strings = i.getStringArrayListExtra("liste");
        strings2 = ReadyYardimcisi(tablename);


        readyAdapter = new ReadyAdapter(this,  strings);
        listView.setAdapter(readyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent in = new Intent(ReadyProgramList.this,ContentDesignPopUp.class);
                in.putExtra("hareket_isim",strings.get(i));
                in.putExtra("gif_isim",strings2.get(i));
                //in.putExtra("liste", (Serializable) strings);
                startActivity(in);
            }
        });
    }
    public  List ReadyYardimcisi(String tablename)
    {
        //DatabaseOpenHelper DatabaseOpenHelper = new DatabaseOpenHelper(this);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Cursor cursor = databaseAccess.getQuotes(tablename);

        List model = new ArrayList<>();   //Alt elemanlar için yeni bir ArrayList oluşturduk.
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            model.add(cursor.getString(2));

            cursor.moveToNext();
        }
        cursor.close();
        return model;
    }

}
