package com.example.atakan.fitnessgunlukleri;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class ContentDesignPopUp extends Activity {
    TextView text_hareket_isim;
    GifImageView gifImageView;
    String hareket_isim;
    String gif_isim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_design_pop_up);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Intent in = getIntent();

        gif_isim = in.getStringExtra("gif_isim");
        if(gif_isim == null){
            gif_isim = "deneme";
        }

        Context c = getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/"+gif_isim, null, c.getPackageName());

        hareket_isim = in.getStringExtra("hareket_isim");

        text_hareket_isim = (TextView)findViewById(R.id.text_hareket_isim);
        text_hareket_isim.setText(hareket_isim);

        gifImageView = (GifImageView)findViewById(R.id.gif);
        gifImageView.setImageResource(R.drawable.legpress);

    }
}
