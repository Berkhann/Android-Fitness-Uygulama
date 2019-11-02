package com.example.atakan.fitnessgunlukleri;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.atakan.fitnessgunlukleri.Adapters.ExpandRec;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseAccess;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseOpenHelper;
import com.example.atakan.fitnessgunlukleri.Models.programımModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class popUpMyprog extends Activity  {

    programımModel programım = new programımModel();
    public List listDataHeader;
    public HashMap<String, List> listHash;
    String tablename;
    programımModel program;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popuplayout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Intent i = getIntent();

        tablename = i.getStringExtra("tablename");
        String prog_name = i.getStringExtra("prog_name");

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        RecyclerView recyclerView = findViewById(R.id.popUpRec);

        initData();

        SharedPreferences sharedPref = getSharedPreferences(prog_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        ExpandRec listAdapter = new ExpandRec(listHash, R.layout.prog_list_card_ana, getApplicationContext(), tablename,programım,sharedPref,editor,prog_name);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        recyclerView.setAdapter(listAdapter);


    }



    private void initData() {


        listDataHeader = new ArrayList<>();   //Ana elemanlar için yeni bir ArrayList oluşturduk.
        listHash = new HashMap<>();           //HashMap oluşturduk.


            List lelele = yardimci(tablename.replace(" ",""));
            listHash.put((String) tablename, lelele);




    }

    public  List yardimci(String tablename)
    {
        DatabaseOpenHelper DatabaseOpenHelper = new DatabaseOpenHelper(this);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Cursor cursor = databaseAccess.getQuotes(tablename);

        List model = new ArrayList<>();   //Alt elemanlar için yeni bir ArrayList oluşturduk.
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            model.add(cursor.getString(1));

            cursor.moveToNext();
        }
        cursor.close();
        return model;
    }



}
