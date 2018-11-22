package com.example.user.yellowridesafety;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParentTrackChild extends AppCompatActivity {
    TextView lat,lon,plac,dat,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_track_child);

        lat = (TextView) findViewById(R.id.lattitude);
        lon = (TextView) findViewById(R.id.longitude);
        dat = (TextView) findViewById(R.id.date);
        plac=(TextView)findViewById(R.id.plc);
        time = (TextView) findViewById(R.id.ti);

        getdata vw=new getdata();
        vw.execute();
    }
        private class getdata extends AsyncTask<String,String,String> {

            @Override
            protected String doInBackground(String... strings) {
                WebServiceCaller wb = new WebServiceCaller();
                wb.setSoapObject("ViewLocation");


                wb.callWebService();
                return wb.getResponse();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(ParentTrackChild.this, s, Toast.LENGTH_SHORT).show();


                try {
                    JSONArray j = new JSONArray(s);
                    JSONObject jo = j.getJSONObject(0);
                    String lattitude= jo.getString("Latti");
                    String longitude = jo.getString("Longi");
                    String date = jo.getString("Dat");
                    String tim= jo.getString("Tim");
                    String place = jo.getString("Plac");

                    lat.setText("Lattitude: "+lattitude);
                    lon.setText("Longittude: "+longitude);
                    plac.setText(place);
                    dat.setText(date);
                    time.setText(tim);
                } catch (Exception e) {
                    e.printStackTrace();

                }


            }
        }
}
