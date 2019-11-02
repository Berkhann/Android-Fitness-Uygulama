package com.example.atakan.fitnessgunlukleri;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.atakan.fitnessgunlukleri.Adapters.ListPageAdapter;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseAccess;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseOpenHelper;
import com.example.atakan.fitnessgunlukleri.Models.ListPageModel;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity {
    private List<ListPageModel> model= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        Intent i = getIntent();

        String tablename = i.getStringExtra("tablename");

        RecyclerView recyc = findViewById(R.id.recycler_view);

        DatabaseOpenHelper DatabaseOpenHelper = new DatabaseOpenHelper(this);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        tablename = tablename.replace(" ","");
       Cursor cursor = databaseAccess.getQuotes(tablename);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ListPageModel m = new ListPageModel();
            m.id = cursor.getInt(0);
            m.name = cursor.getString(1);

            model.add(m);

            cursor.moveToNext();
        }
        cursor.close();


        ListPageAdapter bilgi_k = new ListPageAdapter(model,R.layout.list_card,getApplicationContext(),tablename);
        recyc.setLayoutManager((new LinearLayoutManager(this)));
        recyc.setAdapter(bilgi_k);


    }
}
