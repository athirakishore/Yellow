package com.example.user.yellowridesafety;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParentLogin extends AppCompatActivity implements View.OnClickListener {

    EditText Uname,Passw;
    Button login,newuser;
    SharedPreferences sh;
    String sh_name="MyData";
    String id="",psw="",a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        Uname= (EditText) findViewById(R.id.unameEditText);
        Passw= (EditText) findViewById(R.id.pswEditText);
        login= (Button) findViewById(R.id.logButton);
        newuser= (Button) findViewById(R.id.newButton);
        login.setOnClickListener(this);
        newuser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view==login) {
            a = Uname.getText().toString();
            String b = Passw.getText().toString();
            selectdata sd = new selectdata();
            sd.execute(a, b);
        }
        else
        {
            Intent intent=new Intent(ParentLogin.this,ParentRegistration.class);
            startActivity(intent);
        }


    }

    private class selectdata extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb = new WebServiceCaller();
            wb.setSoapObject("selectoperation");
            wb.addProperty("username", strings[0]);
            wb.addProperty("password", strings[1]);
            wb.callWebService();
            return wb.getResponse();
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(s);

                JSONObject jsonObject;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                //    jo.put("usertype","parent");
                    String type=jsonObject.getString("usertype");

                    if(type.equals("parent")){
                        sh = getSharedPreferences(sh_name, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString("id", id);
                        editor.putString("username", name);
                        editor.commit();
                        Toast.makeText(ParentLogin.this, "id="+id+"name="+name, Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(ParentLogin.this, ParentHome.class);
                        startActivity(in);
                    }
                    else if(type.equals("driver")){
                        sh = getSharedPreferences(sh_name, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString("id", id);
                        editor.putString("username", name);
                        editor.commit();
                        Toast.makeText(ParentLogin.this, "id="+id+"name="+name, Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(ParentLogin.this, DriverHome.class);
                        startActivity(in);
                    }


                }


            } catch (Exception e) {

            }

        }
    }

}


