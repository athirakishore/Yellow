package com.example.user.yellowridesafety;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChildAbsent extends AppCompatActivity implements View.OnClickListener {
    EditText admno;
    String id[];
    String name[];
    EditText reas;
    Button sub,can;
    Spinner sp;
    String Admno;
    String id1;
    String sh_name = "MyData";
    String  parent_id;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_absent);

        sp = (Spinner) findViewById(R.id.sp);
        reas = (EditText) findViewById(R.id.reasonEditText);
        admno = (EditText) findViewById(R.id.admnoEditText);
        sub = (Button) findViewById(R.id.subButton);
        can = (Button) findViewById(R.id.canButton);
        sub.setOnClickListener(this);
        can.setOnClickListener(this);


        SharedPreferences sh = getSharedPreferences(sh_name, MODE_PRIVATE);
        id1 = sh.getString("id", " ");
getdata ins= new getdata();
ins.execute(id1);
    }
        private class getdata extends AsyncTask<String,String,String> {

            @Override
            protected String doInBackground(String... strings) {
                WebServiceCaller wb = new WebServiceCaller();
                wb.setSoapObject("fillstudent");
                wb.addProperty("parentid", strings[0]);

                wb.callWebService();
                return wb.getResponse();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(ChildAbsent.this, s, Toast.LENGTH_SHORT).show();
                Log.d("alliswell",s);
                try {
                    JSONArray jsonArray = null;
                    jsonArray = new JSONArray(s);

                    JSONObject jsonObject;
                     id = new String[jsonArray.length()];
                     name = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        id[i] = jsonObject.getString("Admno");
                        name[i] = jsonObject.getString("Stud");


                    }
adapter=new ArrayAdapter<String>(ChildAbsent.this,android.R.layout.simple_spinner_item,name);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    sp.setAdapter(adapter);

                } catch (Exception e) {


                }
            }
        }

            public void onClick(View view) {

                Admno = id[sp.getSelectedItemPosition()];
                if (view == sub) {
                    if (Admno.equals("")) {
                        admno.setError("Enter absent details");
                    } else {

                                 insertdata ins=new insertdata();
                                 String rs= reas.getText().toString();
                                 ins.execute(Admno,rs,id1);
                    }
                } else if (view == can) {
                    reas.setText("");

                }

            }

            class insertdata extends AsyncTask<String, String, String> {

                @Override
                protected String doInBackground(String... strings) {
                    WebServiceCaller wb = new WebServiceCaller();
                    wb.setSoapObject("ChildAbsent");
                    wb.addProperty("adm", strings[0]);
                    wb.addProperty("reason",strings[1]);
                    wb.addProperty("id",strings[2]);

                    wb.callWebService();
                    return wb.getResponse();
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Toast.makeText(ChildAbsent.this, "insert"+s, Toast.LENGTH_SHORT).show();
                }
            }
        }
