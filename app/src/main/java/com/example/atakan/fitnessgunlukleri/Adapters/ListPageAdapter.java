package com.example.atakan.fitnessgunlukleri.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.atakan.fitnessgunlukleri.ContentDesign;
import com.example.atakan.fitnessgunlukleri.Models.ListPageModel;
import com.example.atakan.fitnessgunlukleri.R;
import com.example.atakan.fitnessgunlukleri.itemClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListPageAdapter extends RecyclerView.Adapter<ListPageAdapter.BilgiYerleskesi>{

    private List<ListPageModel> bilgiler_ana_kategori;
    private int sira_layout_kategori;
    private Context context_kategori;
    private String tablename;

    public static class BilgiYerleskesi extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        TextView text;
        CircleImageView circleimg;
        com.example.atakan.fitnessgunlukleri.itemClickListener itemClickListener;

        public BilgiYerleskesi(View v) {

            super(v);
            text = v.findViewById(R.id.textView2);
            circleimg = v.findViewById(R.id.profile_image2);

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

    public ListPageAdapter(List<ListPageModel> icerik, int siraLayout, Context context,String tablename)//Constructor ile Gelen Değerleri Globalde olanlara atadık.
    {
        this.tablename = tablename;
        this.bilgiler_ana_kategori = icerik;
        this.sira_layout_kategori = siraLayout;
        this.context_kategori = context;
    }

    @NonNull
    @Override
    public ListPageAdapter.BilgiYerleskesi onCreateViewHolder(@NonNull ViewGroup v, int i) {

        View view = LayoutInflater.from(v.getContext()).inflate(sira_layout_kategori,v,false);

        return new ListPageAdapter.BilgiYerleskesi(view);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ListPageAdapter.BilgiYerleskesi bilgiYerleskesi,  final int i) {


        bilgiYerleskesi.text.setText(bilgiler_ana_kategori.get(i).name);//Önceden ayarlanılan Textview Set edildi.

        if(tablename.equals("ÖNKOL"))
        {
            bilgiYerleskesi.circleimg.setBackground(context_kategori.getDrawable(R.drawable.arm));
        }
        else if(tablename.equals("ARKAKOL"))
        {
            bilgiYerleskesi.circleimg.setBackground(context_kategori.getDrawable(R.drawable.arm));
        }
        else if(tablename.equals("GÖĞÜS"))
        {
            bilgiYerleskesi.circleimg.setBackground(context_kategori.getDrawable(R.drawable.chest));
        }
        else if(tablename.equals("BACAK"))
        {
            bilgiYerleskesi.circleimg.setBackground(context_kategori.getDrawable(R.drawable.leg));
        }
        else if(tablename.equals("KARIN"))
        {
            bilgiYerleskesi.circleimg.setBackground(context_kategori.getDrawable(R.drawable.abs));
        }
        else if(tablename.equals("OMUZ"))
        {
            bilgiYerleskesi.circleimg.setBackground(context_kategori.getDrawable(R.drawable.shoulder));
        }
        else if(tablename.equals("SIRT"))
        {
            bilgiYerleskesi.circleimg.setBackground(context_kategori.getDrawable(R.drawable.back));
        }

        bilgiYerleskesi.setItemClickListener(new itemClickListener() {


            @Override
            public void onClick(View view, int position, boolean isLongClick) {



                Intent in = new Intent(context_kategori, ContentDesign.class);

                in.putExtra("name",bilgiler_ana_kategori.get(position).name);
                in.putExtra("tablename",tablename);
                context_kategori.startActivity(in);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bilgiler_ana_kategori.size();
    }





}

