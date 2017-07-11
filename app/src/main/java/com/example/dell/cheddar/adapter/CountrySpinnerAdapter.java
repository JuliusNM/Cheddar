package com.example.dell.cheddar.adapter;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.cheddar.model.CountryData;
import com.example.dell.cheddar.R;

import java.util.ArrayList;

/**
 * Created by Dell on 6/26/2017.
 */

public class CountrySpinnerAdapter extends ArrayAdapter<CountryData> {


    Activity context;
    ArrayList<CountryData> countries;
    LayoutInflater inflater;
    public CountrySpinnerAdapter(Context context,int id, ArrayList<CountryData>countries){
        super(context,id,countries);
        this.countries=countries;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.groupid=groupid;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(R.layout.country_spinner,parent,false);
        ImageView imageView=(ImageView)itemView.findViewById(R.id.img);
        imageView.setImageResource(countries.get(position).getImageId());
        TextView textView=(TextView)itemView.findViewById(R.id.txt);
        textView.setText(countries.get(position).getText());
        return itemView;
    }
    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}
