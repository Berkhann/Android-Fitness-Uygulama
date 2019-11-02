package com.example.atakan.fitnessgunlukleri.Adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atakan.fitnessgunlukleri.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sanjeev k Saroj on 28/2/17.
 */

public class MainPageAdapter extends BaseAdapter {

    Activity activity;
    List<String> customListDataModelArrayList = new ArrayList<>();
    LayoutInflater layoutInflater = null;

    public MainPageAdapter(Activity activity, List<String> customListDataModelArray){
        this.activity=activity;
        this.customListDataModelArrayList = customListDataModelArray;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return customListDataModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return customListDataModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public String getText(int i)
    {
        return customListDataModelArrayList.get(i).toString();
    }


    private static class ViewHolder
    {
        TextView tx;

    }
    ViewHolder viewHolder = null;


    // this method  is called each time for arraylist data size.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View vi=view;
        final int pos = position;
        if(vi == null){

            // create  viewholder object for list_rowcell View.
            viewHolder = new ViewHolder();
            // inflate list_rowcell for each row
            vi = layoutInflater.inflate(R.layout.swipe_button_card,null);
            viewHolder.tx = vi.findViewById(R.id.prog_list_text_);

            /*We can use setTag() and getTag() to set and get custom objects as per our requirement.
            The setTag() method takes an argument of type Object, and getTag() returns an Object.*/
            vi.setTag(viewHolder);
        }else {

            /* We recycle a View that already exists */
            viewHolder= (ViewHolder) vi.getTag();
        }


        viewHolder.tx.setText(customListDataModelArrayList.get(pos).toString());



        return vi;
    }
}

