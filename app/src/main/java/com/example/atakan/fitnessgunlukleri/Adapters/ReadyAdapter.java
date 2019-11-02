package com.example.atakan.fitnessgunlukleri.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.atakan.fitnessgunlukleri.R;

import java.util.List;

/**
 * Created by ATAKAN on 18.09.2019.
 */

public class ReadyAdapter extends BaseAdapter {

    List list;
    Context context;
    public ReadyAdapter(Context context, List<String> list){
        this.context =context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        v = View.inflate(context,R.layout.prog_list_card_ana,null);

        TextView textView = (TextView)v.findViewById(R.id.prog_item_text_ana);
        textView.setText(list.get(i).toString());
        return v;
    }
}
