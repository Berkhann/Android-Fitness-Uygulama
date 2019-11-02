package com.example.atakan.fitnessgunlukleri;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.atakan.fitnessgunlukleri.Models.MainPageModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button program_yarat,programlar_button;
    private List<MainPageModel> model= new ArrayList<>();
    private List<MainPageModel> model1= new ArrayList<>();
    TextView onkol,arkakol,gogus,sırt,omuz,bacak,karın;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FloatingActionButton button_Fab = findViewById(R.id.fab);

        /********************************************************************************/
        button_Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(Gravity.START)) navDrawer.openDrawer(Gravity.START);
                else navDrawer.closeDrawer(Gravity.END);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ANASAYFA");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(Gravity.START)) navDrawer.openDrawer(Gravity.START);
                else navDrawer.closeDrawer(Gravity.END);
            }
        });

        /********************************************************************************************/

        programlar_button = findViewById(R.id.programlar_button);
        programlar_button.setOnClickListener(this);
        program_yarat = findViewById(R.id.ex1_button);
        program_yarat.setOnClickListener(this);
        onkol = findViewById(R.id.text_onkol);
        onkol.setOnClickListener(this);
        arkakol = findViewById(R.id.text_arkakol);
        arkakol.setOnClickListener(this);
        gogus = findViewById(R.id.text_gogus);
        gogus.setOnClickListener(this);
        sırt = findViewById(R.id.text_sırt);
        sırt.setOnClickListener(this);
        omuz = findViewById(R.id.text_omuz);
        omuz.setOnClickListener(this);
        bacak = findViewById(R.id.text_bacak);
        bacak.setOnClickListener(this);
        karın = findViewById(R.id.text_karın);
        karın.setOnClickListener(this);







    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == program_yarat.getId()){
            Intent i = new Intent(MainActivity.this,MyProgram.class);
            startActivity(i);
        }else if(view.getId() == programlar_button.getId()){
            TextView t = (TextView) view;

            Intent i = new Intent(MainActivity.this,ReadyProgram.class);
            //i.putExtra("tablename",t.getText());

            startActivity(i);
        }
        else{
            TextView t = (TextView) view;

            Intent i = new Intent(MainActivity.this,ListsActivity.class);
            i.putExtra("tablename",t.getText().toString());

            startActivity(i);
        }


    }
}
