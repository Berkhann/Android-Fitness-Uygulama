package com.example.atakan.fitnessgunlukleri;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atakan.fitnessgunlukleri.Adapters.ExpandRec;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseAccess;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseOpenHelper;
import com.example.atakan.fitnessgunlukleri.Models.programımModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyProgram extends AppCompatActivity implements View.OnClickListener {


    public RecyclerView listView;
    public RecyclerView listView2;
    public ExpandRec listAdapter;
    public List listDataHeader;
    public HashMap<String, List> listHash;
    TextView Text_deneme,Text_deneme1,Text_deneme2,Text_deneme3,Text_deneme4,Text_deneme5,Text_deneme6;
    TextView tt;
    List<Integer> list_rec;
    programımModel programım = new programımModel();
    programımModel program;


    List<String> default_list =  new ArrayList();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(MyProgram.this,MainActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_dashboard:

                    Intent i1 = new Intent(MyProgram.this,Programs.class);
                    Set<String> prog_default = new HashSet<String>(default_list);
                    SharedPreferences sharedPref1 = getSharedPreferences("prog_listem", Context.MODE_PRIVATE);
                    Set<String> s = sharedPref1.getStringSet("prog_list", prog_default);
                    i1.putExtra("prog_list", (Serializable) sharedPref1.getStringSet("prog_list", prog_default));
                    startActivity(i1);
                    return true;
                case R.id.saveButton:
                    EditText editText = findViewById(R.id.program_name);
                    if(editText.getText().toString().equals("")) {
                        Toast.makeText(MyProgram.this, "Program adı giriniz.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent i2 = new Intent(MyProgram.this, Programs.class);
                        program = new programımModel();

                        prog_default = new HashSet<String>(default_list);
                        kaydet(editText.getText().toString());
                        sharedPref1 = getSharedPreferences("prog_listem", Context.MODE_PRIVATE);
                        s = sharedPref1.getStringSet("prog_list", prog_default);
                        i2.putExtra("prog_list", (Serializable) sharedPref1.getStringSet("prog_list", prog_default));
                        startActivity(i2);

                        Toast.makeText(MyProgram.this, "Program Kaydedildi.", Toast.LENGTH_SHORT).show();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_program);


        final Button button = findViewById(R.id.save_progname);
        final ScrollView scrollView = findViewById(R.id.scroll);
        final EditText editText = findViewById(R.id.program_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);

            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Text_deneme = findViewById(R.id.text_onkol);
        Text_deneme.setOnClickListener(this);
        Text_deneme1 = findViewById(R.id.text_arkakol);
        Text_deneme1.setOnClickListener(this);
        Text_deneme2 = findViewById(R.id.text_gogus);
        Text_deneme2.setOnClickListener(this);
        Text_deneme3 = findViewById(R.id.text_sırt);
        Text_deneme3.setOnClickListener(this);
        Text_deneme4 = findViewById(R.id.text_omuz);
        Text_deneme4.setOnClickListener(this);
        Text_deneme5 = findViewById(R.id.text_bacak);
        Text_deneme5.setOnClickListener(this);
        Text_deneme6 = findViewById(R.id.text_karın);
        Text_deneme6.setOnClickListener(this);




    }


    public void kaydet(String prog_name)
    {




        Set<String> prog_default = new HashSet<String>(default_list);

        SharedPreferences sharedPref1 = getSharedPreferences("prog_listem", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref1.edit();
        List<String> list = new ArrayList<String>(sharedPref1.getStringSet("prog_list",prog_default));
        list.add(prog_name);
        Set<String> set1 = new HashSet<String>(list);
        editor1.putStringSet("prog_list",set1);
        editor1.apply();





    }






    @Override
    public void onClick(View v) {

        TextView t = (TextView) v;

            Intent i = new Intent(MyProgram.this,popUpMyprog.class);
        EditText editText = findViewById(R.id.program_name);
            i.putExtra("tablename",t.getText().toString());
            i.putExtra("prog_name",editText.getText().toString());

            startActivity(i);


    }










}
