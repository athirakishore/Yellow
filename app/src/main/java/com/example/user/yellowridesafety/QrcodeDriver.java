package com.example.user.yellowridesafety;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrcodeDriver extends AppCompatActivity implements View.OnClickListener, ZXingScannerView.ResultHandler {
    Button qc;
    ZXingScannerView scannerView;
    Geocoder geocoder;
    List<Address> addresses;
    String specialLocation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_driver);
        qc = (Button) findViewById(R.id.qrcodebutton);
        qc.setOnClickListener(this);

    }
    private class getdata extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb = new WebServiceCaller();
            wb.setSoapObject("QrcodeReading");
            wb.addProperty("studid", strings[0]);
            wb.addProperty("pickupaddress",strings[1]);
//            wb.addProperty("dropdownaddress",strings[2]);

            wb.callWebService();
            return wb.getResponse();



        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(QrcodeDriver.this, s, Toast.LENGTH_SHORT).show();

            }
        }

        @Override
    public void onClick(View view) {


        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();


    }


    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();

    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, ""+result.getText(), Toast.LENGTH_SHORT).show();
        scannerView.resumeCameraPreview(this);
        String text = result.getText();
        String  loc= getLocation();
        getdata ss=new getdata();
        ss.execute(text,loc);
    }
    public String getLocation() {

        try {
            GpsTracker j=new GpsTracker(QrcodeDriver.this);

            Location loc;
            loc = j.getLocation();

            double a=loc.getLatitude();
            double b=loc.getLongitude();
            String lat=Double.toString(a);
            String longit=Double.toString(b);
//                Log.d("longitude",longit);
//                Log.d("latitude",lat);



            geocoder = new Geocoder(QrcodeDriver.this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(a,b, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state1 = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            SharedPreferences sh=getSharedPreferences("PHONEIMEI",MODE_PRIVATE);
            String id=sh.getString("id","");
            Log.d("adressline",address);


            specialLocation="City:"+address+city;

        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return specialLocation;
    }

}