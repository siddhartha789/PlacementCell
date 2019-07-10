package com.example.peace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class cscompanyadapter extends BaseAdapter {

    ArrayList<cscompany> ldata;
    int rlayout;
    Context c;

    public cscompanyadapter(ArrayList<cscompany> ldata, int rlayout, Context c) {
        this.ldata = ldata;
        this.rlayout = rlayout;
        this.c = c;
    }

    @Override
    public int getCount() {
        return ldata.size();
    }

    @Override
    public Object getItem(int position) {
        return ldata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(c).inflate(rlayout,null);
        TextView t1=(TextView)convertView.findViewById(R.id.t1);
        TextView t2=(TextView)convertView.findViewById(R.id.t2);
        TextView t3=(TextView)convertView.findViewById(R.id.t3);
        t1.setText(""+ldata.get(position).getCname());
        t2.setText(ldata.get(position).getContact());
        t3.setText(ldata.get(position).getEmail());

        return convertView;
    }
}
