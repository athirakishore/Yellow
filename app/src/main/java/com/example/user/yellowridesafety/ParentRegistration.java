package com.example.user.yellowridesafety;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ParentRegistration extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    Button B1,C1;
    EditText name,admno,addr,age,phone,email,username,password,cpsw;
    RadioButton R1,R2;
    RadioGroup G;
    String gender;
    String Name,Admno,Address,Age,Ph,Email,User,Pass,Cpsw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registration);

        name=(EditText)findViewById(R.id.nameEditText);
        admno=(EditText)findViewById(R.id.admnoEditText);
        addr=(EditText)findViewById(R.id.adresEditText);
        age=(EditText)findViewById(R.id.ageEditText);
        phone=(EditText)findViewById(R.id.phoneEditText);
        email=(EditText)findViewById(R.id.emailEditText);
        username=(EditText)findViewById(R.id.userEditText);
        password=(EditText)findViewById(R.id.passwordEditText);
        cpsw=(EditText)findViewById(R.id.cnfpassEditText);

        R1= (RadioButton) findViewById(R.id.maleRadioButton);
        R2= (RadioButton) findViewById(R.id.femaleRadioButton);
        G= (RadioGroup) findViewById(R.id.gender);
        G.setOnCheckedChangeListener(this);
        B1=(Button)findViewById(R.id.regButton);
        C1=(Button)findViewById(R.id.canButton);
        C1.setOnClickListener(this);
        B1.setOnClickListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i==R.id.maleRadioButton)
        {
            gender=R1.getText().toString();
        }
        else
        {
            gender=R2.getText().toString();
        }
    }

    @Override
    public void onClick(View view) {


        Name=name.getText().toString();
        Admno=admno.getText().toString();
        Address=addr.getText().toString();
        Age=age.getText().toString();
        Ph=phone.getText().toString();
        Email=email.getText().toString();
        User=username.getText().toString();
        Pass=password.getText().toString();
        Cpsw=cpsw.getText().toString();

        if(view==B1)
        {
        if(Name.equals(""))
        {
            name.setError("Enter Name");
        }
        else if(Admno.equals(""))
        {
            admno.setError("Enter Admission No");
        }
        else if(Address.equals(""))
        {
            addr.setError("Enter Address");
        }
        else if(Age.equals(""))
        {
            age.setError("Enter Age");
        }
        else if(Ph.equals(""))
        {
            phone.setError("Enter phn number");
        }
        else if(Email.equals(""))
        {
            email.setError("Enter Email");
        }
        else if(User.equals(""))
        {
            username.setError("Enter Username");
        }
        else if(Pass.equals(""))
        {
            password.setError("Enter Password");
        }
        else if(Cpsw.equals(""))
        {
            cpsw.setError("Enter Current Password");
        }
        else if(!Pass.equals(Cpsw))
        {
            cpsw.setError("Password Missmatch");
        }

        else{

                insertdata id=new insertdata();
                id.execute(Name,Admno,Address,Age,gender,Ph,Email,User,Pass);
        }
        }
      else if(view==C1){
            name.setText("");
            admno.setText("");
            addr.setText("");
            age.setText("");
            phone.setText("");
            email.setText("");
            username.setText("");
            password.setText("");
            cpsw.setText("");
        }
    }



    private class insertdata extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb=new WebServiceCaller();
            wb.setSoapObject("ParentRegis");
            wb.addProperty("name", strings[0]);
            wb.addProperty("admno",strings[1]);
            wb.addProperty("address",strings[2]);
            wb.addProperty("age",strings[3]);
            wb.addProperty("gender",strings[4]);
            wb.addProperty("phone",strings[5]);
            wb.addProperty("email",strings[6]);
            wb.addProperty("username",strings[7]);
            wb.addProperty("password",strings[8]);
            wb.callWebService();
            return wb.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ParentRegistration.this,s,Toast.LENGTH_SHORT).show();
        }
    }

}
