package com.example.atakan.fitnessgunlukleri;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.atakan.fitnessgunlukleri.Models.ReadyPageModel;
import com.example.atakan.fitnessgunlukleri.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifTextView;

public class ContentDesign extends AppCompatActivity {

    ReadyPageModel readyPageModel;
    TextView textView_hareket_adi,textView;
    GifImageView gifImageView;
    String gif_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_design);

        Intent i = getIntent();

        Context c = getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/"+"legpress", null, c.getPackageName());

        //Toast.makeText(this, i.getStringExtra("tablename"), Toast.LENGTH_SHORT).show();

        //List list = i.getStringArrayListExtra("liste");
        //readyPageModel = (ReadyPageModel) i.getSerializableExtra("ser");

        gif_name = i.getStringExtra("gif_name");

        textView_hareket_adi = (TextView)findViewById(R.id.textView_hareket_adi);
        textView = (TextView)findViewById(R.id.textView_content);
        textView_hareket_adi.setText(i.getStringExtra("name"));
        textView.setText(gif_name);

        gifImageView = (GifImageView)findViewById(R.id.gif);
        gifImageView.setImageResource(id);
    }
}
