package com.example.atakan.fitnessgunlukleri;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.atakan.fitnessgunlukleri.Adapters.ExpandRec;
import com.example.atakan.fitnessgunlukleri.Adapters.ListPageAdapter;
import com.example.atakan.fitnessgunlukleri.Adapters.MainPageAdapter;
import com.example.atakan.fitnessgunlukleri.Models.programımModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Programs extends AppCompatActivity {
    programımModel program ;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(Programs.this,MainActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.saveButton:


                    return true;
            }
            return false;
        }
    };

    MainPageAdapter expAdapter;
    Set<String> set1;
    SwipeMenuListView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView= findViewById(R.id.listView);
        final Intent i = getIntent();
        set1= new HashSet<String>();
        set1 = (Set<String>) i.getSerializableExtra("prog_list");
        List<String> list = new ArrayList<String>(set1);

        expAdapter = new MainPageAdapter(Programs.this,list);

        recyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);


        SwipeMenuAdapter sw = new SwipeMenuAdapter(getApplicationContext(), (ListAdapter) expAdapter);

        recyclerView.setAdapter(sw);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background

                // set item width
                deleteItem.setWidth(200);
                // set a icon
                deleteItem.setIcon(R.drawable.garbage);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        recyclerView.setMenuCreator(creator);

        recyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        String s = expAdapter.getText(position);
                        SharedPreferences sharedPref1 = getSharedPreferences("prog_listem", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPref1.edit();
                        List<String> default_list =  new ArrayList();
                        Set<String> prog_default = new HashSet<String>(default_list);
                        List<String> list = new ArrayList<String>(sharedPref1.getStringSet("prog_list",prog_default));
                        list.remove(s);
                        Set<String> set1 = new HashSet<String>(list);
                        editor1.putStringSet("prog_list",set1);
                        editor1.apply();

                        SharedPreferences sharedPref = getSharedPreferences(s, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.remove(s);

                        expAdapter = new MainPageAdapter(Programs.this,list);
                        recyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
                        SwipeMenuAdapter sw = new SwipeMenuAdapter(getApplicationContext(), (ListAdapter) expAdapter);
                        recyclerView.setAdapter(sw);




                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String tablename = expAdapter.getText(position).toString();

                Intent i = new Intent(Programs.this,Selected_Prog.class);
                i.putExtra("tablename",tablename);
                startActivity(i);
            }
        });



    }



}
