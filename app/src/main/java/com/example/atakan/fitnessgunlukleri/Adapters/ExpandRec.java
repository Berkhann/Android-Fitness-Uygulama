package com.example.atakan.fitnessgunlukleri.Adapters;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.atakan.fitnessgunlukleri.Models.ListPageModel;
import com.example.atakan.fitnessgunlukleri.Models.programımModel;
import com.example.atakan.fitnessgunlukleri.Programs;
import com.example.atakan.fitnessgunlukleri.R;
import com.example.atakan.fitnessgunlukleri.itemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExpandRec extends RecyclerView.Adapter<ExpandRec.BilgiYerleskesi>{

    private HashMap<String, List> bilgiler_ana_kategori;
    private int sira_layout_kategori;
    private Context context_kategori;
    private String tablename;
    programımModel programım = new programımModel();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<String> default_list =  new ArrayList();
    Set<String> prog_default = new HashSet<String>(default_list);
    String prog_name;




    public static class BilgiYerleskesi extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        TextView text;
        Button savebutton;
        com.example.atakan.fitnessgunlukleri.itemClickListener itemClickListener;

        public BilgiYerleskesi(View v) {

            super(v);
            text = v.findViewById(R.id.prog_item_text_ana);
            savebutton = v.findViewById(R.id.saveButton);





            itemView.setOnClickListener(this);

        }
        public void setItemClickListener(com.example.atakan.fitnessgunlukleri.itemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);

        }


    }

    public ExpandRec(HashMap<String, List> icerik, int siraLayout, Context context,String tablename,programımModel programım,SharedPreferences sharedPreferences,SharedPreferences.Editor editor,String prog_name)//Constructor ile Gelen Değerleri Globalde olanlara atadık.
    {
        this.programım = programım;
        this.bilgiler_ana_kategori = icerik;
        this.sira_layout_kategori = siraLayout;
        this.context_kategori = context;
        this.tablename=tablename;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
        this.prog_name = prog_name;

    }

    @NonNull
    @Override
    public ExpandRec.BilgiYerleskesi onCreateViewHolder(@NonNull ViewGroup v, int i) {

        View view = LayoutInflater.from(v.getContext()).inflate(sira_layout_kategori,v,false);

        return new ExpandRec.BilgiYerleskesi(view);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ExpandRec.BilgiYerleskesi bilgiYerleskesi,  final int i) {


        bilgiYerleskesi.text.setText(bilgiler_ana_kategori.get(tablename).get(i).toString());//Önceden ayarlanılan Textview Set edildi.



        bilgiYerleskesi.setItemClickListener(new itemClickListener() {


            @Override
            public void onClick(View view, int position, boolean isLongClick) {



                if (tablename.equals("ÖN KOL"))
                {
                    if(bilgiYerleskesi.text.getCurrentTextColor() == Color.parseColor("#0E4994")) {
                        Set<String> s = sharedPreferences.getStringSet("ÖN KOL",prog_default);
                        s.remove(bilgiler_ana_kategori.get("ÖN KOL").get(i).toString());
                        editor.putStringSet("ÖN KOL",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#135E11"));
                    }
                    else
                    {
                        Set<String> s = sharedPreferences.getStringSet("ÖN KOL",prog_default);
                        s.add(bilgiler_ana_kategori.get("ÖN KOL").get(i).toString());
                        editor.putStringSet("ÖN KOL",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#0E4994"));
                    }
                }
                else if (tablename.equals("ARKA KOL"))
                {
                    if(bilgiYerleskesi.text.getCurrentTextColor() == Color.parseColor("#0E4994")){
                        Set<String> s = sharedPreferences.getStringSet("ARKA KOL",prog_default);
                        s.remove(bilgiler_ana_kategori.get("ARKA KOL").get(i).toString());
                        editor.putStringSet("ARKA KOL",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#135E11"));

                    }else{
                        Set<String> s = sharedPreferences.getStringSet("ARKA KOL",prog_default);
                        s.add(bilgiler_ana_kategori.get("ARKA KOL").get(i).toString());
                        editor.putStringSet("ARKA KOL",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#0E4994"));
                    }

                }
                else if (tablename.equals("GÖĞÜS"))
                {
                    if(bilgiYerleskesi.text.getCurrentTextColor() == Color.parseColor("#0E4994")){
                        Set<String> s = sharedPreferences.getStringSet("GÖĞÜS",prog_default);
                        s.remove(bilgiler_ana_kategori.get("GÖĞÜS").get(i).toString());
                        editor.putStringSet("GÖĞÜS",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#135E11"));

                    }else{
                        Set<String> s = sharedPreferences.getStringSet("GÖĞÜS",prog_default);
                        s.add(bilgiler_ana_kategori.get("GÖĞÜS").get(i).toString());
                        editor.putStringSet("GÖĞÜS",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#0E4994"));
                    }

                }
                else if (tablename.equals("SIRT"))
                {
                    if(bilgiYerleskesi.text.getCurrentTextColor() == Color.parseColor("#0E4994")){
                        Set<String> s = sharedPreferences.getStringSet("SIRT",prog_default);
                        s.remove(bilgiler_ana_kategori.get("SIRT").get(i).toString());
                        editor.putStringSet("SIRT",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#135E11"));

                    }else{
                        Set<String> s = sharedPreferences.getStringSet("SIRT",prog_default);
                        s.add(bilgiler_ana_kategori.get("SIRT").get(i).toString());
                        editor.putStringSet("SIRT",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#0E4994"));
                    }


                }
                else if (tablename.equals("OMUZ"))
                {
                    if(bilgiYerleskesi.text.getCurrentTextColor() == Color.parseColor("#0E4994")){
                        Set<String> s = sharedPreferences.getStringSet("OMUZ",prog_default);
                        s.remove(bilgiler_ana_kategori.get("OMUZ").get(i).toString());
                        editor.putStringSet("OMUZ",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#135E11"));

                    }else{
                        Set<String> s = sharedPreferences.getStringSet("SIRT",prog_default);
                        s.add(bilgiler_ana_kategori.get("OMUZ").get(i).toString());
                        editor.putStringSet("OMUZ",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#0E4994"));
                    }


                }
                else if (tablename.equals("BACAK"))
                {
                    if(bilgiYerleskesi.text.getCurrentTextColor() == Color.parseColor("#0E4994")){
                        Set<String> s = sharedPreferences.getStringSet("BACAK",prog_default);
                        s.remove(bilgiler_ana_kategori.get("BACAK").get(i).toString());
                        editor.putStringSet("BACAK",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#135E11"));

                    }else{
                        Set<String> s = sharedPreferences.getStringSet("BACAK",prog_default);
                        s.add(bilgiler_ana_kategori.get("BACAK").get(i).toString());
                        editor.putStringSet("BACAK",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#0E4994"));
                    }

                }
                else if (tablename.equals("KARIN"))
                {
                    if(bilgiYerleskesi.text.getCurrentTextColor() == Color.parseColor("#0E4994")){
                        Set<String> s = sharedPreferences.getStringSet("KARIN",prog_default);
                        s.remove(bilgiler_ana_kategori.get("KARIN").get(i).toString());
                        editor.putStringSet("KARIN",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#135E11"));

                    }else{
                        Set<String> s = sharedPreferences.getStringSet("KARIN",prog_default);
                        s.add(bilgiler_ana_kategori.get("KARIN").get(i).toString());
                        editor.putStringSet("KARIN",s);
                        editor.apply();
                        bilgiYerleskesi.text.setTextColor(Color.parseColor("#0E4994"));
                    }

                }






            }
        });


    }

    public programımModel program_dön()
    {
        return programım;
    }

    @Override
    public int getItemCount() {
        return bilgiler_ana_kategori.get(tablename).size();
    }





}

