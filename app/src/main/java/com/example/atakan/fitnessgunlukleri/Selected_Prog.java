package com.example.atakan.fitnessgunlukleri;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.atakan.fitnessgunlukleri.Adapters.ExpandRec;
import com.example.atakan.fitnessgunlukleri.Adapters.MainPageAdapter;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseAccess;
import com.example.atakan.fitnessgunlukleri.Database.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Selected_Prog extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    List listDataHeader;
    HashMap<String, List> listHash;
    List<Integer> list_rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected__prog);

        Intent i = getIntent();
        final String tablename = i.getStringExtra("tablename");

        sharedPref= getSharedPreferences(tablename, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        get_program_content();

        SwipeMenuListView listView = null;

        list_rec= new ArrayList<>();
        list_rec.add(R.id.list_ÖNKOL);
        list_rec.add(R.id.list_ARKAKOL);
        list_rec.add(R.id.list_GÖĞÜS);
        list_rec.add(R.id.list_SIRT);
        list_rec.add(R.id.list_OMUZ);
        list_rec.add(R.id.list_BACAK);
        list_rec.add(R.id.list_KARIN);

        final List<Integer> list_text = new ArrayList<>();

        list_text.add(R.id.text_onkol);
        list_text.add((R.id.text_arkakol));
        list_text.add((R.id.text_gogus));
        list_text.add((R.id.text_sırt));
        list_text.add((R.id.text_omuz));
        list_text.add((R.id.text_bacak));
        list_text.add((R.id.text_karın));

        TextView listtext = null;

        List<String> default_list =  new ArrayList();
        final Set<String> prog_default = new HashSet<String>(default_list);

        for(int k = 0 ; k<list_rec.size() ;k++) {

            listView = findViewById(list_rec.get(k));

            listtext = findViewById(list_text.get(k));
            String text = listtext.getText().toString();
            listtext.setText(text+" ("+listHash.get(listDataHeader.get(k)).size()+")");

            final MainPageAdapter[] mainPageAdapter = {new MainPageAdapter(Selected_Prog.this, (List<String>) listHash.get(listDataHeader.get(k)))};
            listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

            SwipeMenuAdapter sw = new SwipeMenuAdapter(getApplicationContext(), mainPageAdapter[0]);

            listView.setAdapter(sw);

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

                    // create "delete" item
                    SwipeMenuItem infoItem = new SwipeMenuItem(
                            getApplicationContext());
                    // set item background

                    // set item width
                    infoItem.setWidth(200);
                    // set a icon
                    infoItem.setIcon(R.drawable.information);
                    // add to menu
                    menu.addMenuItem(infoItem);
                }
            };


            listView.setMenuCreator(creator);

            final int sayi = k;

            listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    switch (index) {
                        case 0:
                            String s = mainPageAdapter[0].getText(position);
                            SharedPreferences sharedPref1 = getSharedPreferences(tablename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPref1.edit();
                            List<String> list = new ArrayList<String>(sharedPref1.getStringSet( listDataHeader.get(sayi).toString(),prog_default));
                            list.remove(s);
                            Set<String> set1 = new HashSet<String>(list);
                            editor1.putStringSet(listDataHeader.get(sayi).toString(),set1);
                            editor1.apply();

                            SwipeMenuListView listView = findViewById(list_rec.get(sayi));
                             mainPageAdapter[0] = new MainPageAdapter(Selected_Prog.this,list);
                            listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
                            SwipeMenuAdapter sw = new SwipeMenuAdapter(getApplicationContext(), (ListAdapter) mainPageAdapter[0]);
                            listView.setAdapter(sw);

                            int size = listHash.get(listDataHeader.get(sayi)).size();
                            listHash.get(listDataHeader.get(sayi)).remove(s);

                            TextView listtext = findViewById(list_text.get(sayi));
                            String text = listtext.getText().toString();
                            listtext.setText(text.replace(Integer.toString(size),Integer.toString(listHash.get(listDataHeader.get(sayi)).size())));
                            break;
                        case 1:
                            String hareket_adi = mainPageAdapter[0].getText(position);
                            Intent in = new Intent(Selected_Prog.this,ContentDesignPopUp.class);
                            in.putExtra("hareket_isim",hareket_adi);
                            startActivity(in);
                            break;
                    }
                    // false : close the menu; true : not close the menu
                    return false;
                }
            });




        }




        TextView text_prog_name = findViewById(R.id.program_adi);
        text_prog_name.setText(tablename.toUpperCase());
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





    public void get_program_content()
    {
        List<String> default_list =  new ArrayList();
        Set<String> prog_default = new HashSet<String>(default_list);

        listDataHeader = new ArrayList<>();   //Ana elemanlar için yeni bir ArrayList oluşturduk.
        listHash = new HashMap<>();           //HashMap oluşturduk.

        listDataHeader.add("ÖN KOL");
        listDataHeader.add("ARKA KOL");//ArrayList'e yani ana kategorilere eleman ekledik.
        listDataHeader.add("GÖĞÜS");     //ArrayList'e yani ana kategorilere eleman ekledik.
        listDataHeader.add("SIRT");
        listDataHeader.add("OMUZ");
        listDataHeader.add("BACAK");
        listDataHeader.add("KARIN");


        List<String> list = new ArrayList<String>(sharedPref.getStringSet("ÖN KOL",prog_default));
        listHash.put((String) listDataHeader.get(0), list );

        list = new ArrayList<String>(sharedPref.getStringSet("ARKA KOL",prog_default));
        listHash.put((String) listDataHeader.get(1), list);

        list = new ArrayList<String>(sharedPref.getStringSet("GÖĞÜS",prog_default));
        listHash.put((String) listDataHeader.get(2), list);

        list = new ArrayList<String>(sharedPref.getStringSet("SIRT",prog_default));
        listHash.put((String) listDataHeader.get(3), list);

        list = new ArrayList<String>(sharedPref.getStringSet("OMUZ",prog_default));
        listHash.put((String) listDataHeader.get(4), list);

        list = new ArrayList<String>(sharedPref.getStringSet("BACAK",prog_default));
        listHash.put((String) listDataHeader.get(5), list);

        list = new ArrayList<String>(sharedPref.getStringSet("KARIN",prog_default));
        listHash.put((String) listDataHeader.get(6),list);





    }

    TextView Text_deneme,Text_deneme1,Text_deneme2,Text_deneme3,Text_deneme4,Text_deneme5,Text_deneme6;

    public void onClick(View v) {
        if(v.getId()==Text_deneme.getId()) {
            ListView Rec = findViewById(R.id.list_ÖNKOL);
            if (Rec.getVisibility() == View.VISIBLE) {
                Rec.setVisibility(View.GONE);
            } else {
                Rec.setVisibility(View.VISIBLE);
                Rec = findViewById(list_rec.get(1));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(2));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(3));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(4));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(5));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(6));
                Rec.setVisibility(View.GONE);
            }
        }
        else if(v.getId()==Text_deneme1.getId()) {
            ListView Rec = findViewById(R.id.list_ARKAKOL);
            if (Rec.getVisibility() == View.VISIBLE) {
                Rec.setVisibility(View.GONE);
            } else {
                Rec.setVisibility(View.VISIBLE);
                Rec = findViewById(list_rec.get(0));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(2));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(3));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(4));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(5));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(6));
                Rec.setVisibility(View.GONE);
            }
        }
        else if(v.getId()==Text_deneme2.getId()) {
            ListView Rec = findViewById(R.id.list_GÖĞÜS);
            if (Rec.getVisibility() == View.VISIBLE) {
                Rec.setVisibility(View.GONE);
            } else {
                Rec.setVisibility(View.VISIBLE);
                Rec = findViewById(list_rec.get(1));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(0));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(3));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(4));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(5));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(6));
                Rec.setVisibility(View.GONE);
            }
        }
        else if(v.getId()==Text_deneme3.getId()) {
            ListView Rec = findViewById(R.id.list_SIRT);
            if (Rec.getVisibility() == View.VISIBLE) {
                Rec.setVisibility(View.GONE);
            } else {
                Rec.setVisibility(View.VISIBLE);
                Rec = findViewById(list_rec.get(1));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(2));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(0));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(4));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(5));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(6));
                Rec.setVisibility(View.GONE);
            }
        }
        else if(v.getId()==Text_deneme4.getId()) {
            ListView Rec = findViewById(R.id.list_OMUZ);
            if (Rec.getVisibility() == View.VISIBLE) {
                Rec.setVisibility(View.GONE);
            } else {
                Rec.setVisibility(View.VISIBLE);
                Rec = findViewById(list_rec.get(1));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(2));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(3));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(0));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(5));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(6));
                Rec.setVisibility(View.GONE);
            }
        }
        else if(v.getId()==Text_deneme5.getId()) {
            ListView Rec = findViewById(R.id.list_BACAK);
            if (Rec.getVisibility() == View.VISIBLE) {
                Rec.setVisibility(View.GONE);
            } else {
                Rec.setVisibility(View.VISIBLE);
                Rec = findViewById(list_rec.get(1));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(2));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(3));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(4));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(0));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(6));
                Rec.setVisibility(View.GONE);
            }
        }
        else if(v.getId()==Text_deneme6.getId()) {
            ListView Rec = findViewById(R.id.list_KARIN);
            if (Rec.getVisibility() == View.VISIBLE) {
                Rec.setVisibility(View.GONE);
            } else {
                Rec.setVisibility(View.VISIBLE);
                Rec = findViewById(list_rec.get(1));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(2));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(3));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(4));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(5));
                Rec.setVisibility(View.GONE);
                Rec = findViewById(list_rec.get(0));
                Rec.setVisibility(View.GONE);
            }
        }

    }

}