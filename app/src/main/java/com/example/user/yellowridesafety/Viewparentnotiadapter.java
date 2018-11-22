package com.example.user.yellowridesafety;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 11/6/2018.
 */

public class Viewparentnotiadapter extends RecyclerView.Adapter<Viewparentnotiadapter.MyViewHolder> {
    Context c;
    List<beanviewparentnoti>listnoti;

    public Viewparentnotiadapter(List<beanviewparentnoti> lst, Viewparentnotifications viewparentnotifications) {

        this.listnoti=lst;
        c=viewparentnotifications;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlenotificationparent,parent,false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        beanviewparentnoti obj= listnoti.get(position);
        holder.d.setText(obj.getNotidate());
        holder.t.setText(obj.getNotitime());
        holder.m.setText(obj.getNotimsg());

    }

    @Override
    public int getItemCount() {
        return listnoti.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView d,t,m;
        public MyViewHolder(View itemView) {
            super(itemView);

            d= (TextView) itemView.findViewById(R.id.notidate);
            t= (TextView) itemView.findViewById(R.id.notitime);
            m= (TextView) itemView.findViewById(R.id.notimsg);

        }
    }
}
