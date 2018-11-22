package com.example.user.yellowridesafety;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 10/22/2018.
 */

public class Viewchildadapter extends RecyclerView.Adapter<Viewchildadapter.MyViewHolder> {
    List<beanviewchildabsent> beanclasslist;
    Context context;

    public Viewchildadapter(List<beanviewchildabsent> beanlist, ViewChildAbsent viewChildAbsent) {
        beanclasslist=beanlist;
        context=viewChildAbsent;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlestudentabsent,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        beanviewchildabsent bn = beanclasslist.get(position);

holder.nam.setText(bn.getName());
holder.admn.setText(bn.getAdmno());
holder.dat.setText(bn.getDate());
    }

    @Override
    public int getItemCount() {
        return beanclasslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dat,admn,nam;
        public MyViewHolder(View itemView) {
            super(itemView);
            dat= (TextView) itemView.findViewById(R.id.absdate);
           admn= (TextView) itemView.findViewById(R.id.absadmno);
           nam= (TextView) itemView.findViewById(R.id.absname);



        }
    }
}
