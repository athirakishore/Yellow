package com.example.user.yellowridesafety;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParentViewQr extends AppCompatActivity {
    TextView st, pt, pd, pa, dt, da;
    String sh_name = "MyData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_qr);
        st = (TextView) findViewById(R.id.stid);
        pt = (TextView) findViewById(R.id.ptt);
        pd = (TextView) findViewById(R.id.pdd);
        pa = (TextView) findViewById(R.id.paa);
        dt = (TextView) findViewById(R.id.dtt);
        da = (TextView) findViewById(R.id.daa);

        SharedPreferences sh = getSharedPreferences(sh_name, MODE_PRIVATE);
        String id1 = sh.getString("id", " ");
        ParentViewQr.view v = new ParentViewQr.view();
        v.execute(id1);


    }

    private class view extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller w = new WebServiceCaller();
            w.setSoapObject("fetches");
            w.addProperty("Id", strings[0]);
            w.callWebService();
            return w.getResponse();
        }


        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ParentViewQr.this, "" + s, Toast.LENGTH_SHORT).show();
            try {
                JSONArray j = new JSONArray(s);
                JSONObject jo = j.getJSONObject(0);
                String stu = jo.getString("Studid");
                String ptime = jo.getString("Pickuptime");
                String pdate = jo.getString("Pickupdate");
                String paddr = jo.getString("Pickupaddress");
                String dtime = jo.getString("Dropdowntime");
                String daddr = jo.getString("Dropdownaddress");
                st.setText(stu);
                pt.setText(ptime);
                pd.setText(pdate);
                pa.setText(paddr);
                dt.setText(dtime);
                da.setText(daddr);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}

