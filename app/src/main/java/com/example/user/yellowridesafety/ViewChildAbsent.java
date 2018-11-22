package com.example.user.yellowridesafety;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewChildAbsent extends AppCompatActivity {
    RecyclerView recycler;
    String date[];
    String adm[];
    String name[];
Viewchildadapter ch= null;
List<beanviewchildabsent> beanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_child_absent);
        recycler= (RecyclerView) findViewById(R.id.recycle1);
beanlist=new ArrayList<>();

        getdata ins= new getdata();
        ins.execute();
    }
    private class getdata extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb = new WebServiceCaller();
            wb.setSoapObject("viewstudentabsent");


            wb.callWebService();
            return wb.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ViewChildAbsent.this, s, Toast.LENGTH_SHORT).show();
            Log.d("alliswell", s);
            try {
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(s);

                JSONObject jsonObject;

                date = new String[jsonArray.length()];
                adm = new String[jsonArray.length()];
                name = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    date[i] = jsonObject.getString("Date");
                    adm[i] = jsonObject.getString("StudAdmno");
                    name[i] = jsonObject.getString("StudName");

                    beanviewchildabsent bean=new beanviewchildabsent();
                    bean.setAdmno(adm[i]);
                    bean.setDate(date[i]);
                    bean.setName(name[i]);
                    beanlist.add(bean);

                }
                ch=new Viewchildadapter(beanlist,ViewChildAbsent.this);
               recycler.setLayoutManager(new LinearLayoutManager(ViewChildAbsent.this));
               recycler.setAdapter(ch);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
