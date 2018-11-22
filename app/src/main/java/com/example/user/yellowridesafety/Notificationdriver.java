package com.example.user.yellowridesafety;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Notificationdriver extends AppCompatActivity implements View.OnClickListener {


    EditText msg;
    Button sub, can;
    String Noti;
    String sh_name = "MyData";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationdriver);

        msg = (EditText) findViewById(R.id.msgtxt);
        sub = (Button) findViewById(R.id.subButton);
        can = (Button) findViewById(R.id.canButton);

        sub.setOnClickListener(this);
        can.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        Noti = msg.getText().toString();
        if (view == sub) {
            if (Noti.equals("")) {
                msg.setError("Enter your notifications");
            } else {

                SharedPreferences sh = getSharedPreferences(sh_name, MODE_PRIVATE);
                String id1 = sh.getString("id", " ");

                insertdata id = new insertdata();
                id.execute(Noti, id1);
            }
        } else if (view == can) {
            msg.setText("");

        }

    }

    private class insertdata extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb = new WebServiceCaller();
            wb.setSoapObject("DriverNotifications");
            wb.addProperty("msg", strings[0]);
            wb.addProperty("dvrid", strings[1]);

            wb.callWebService();
            return wb.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Notificationdriver.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
