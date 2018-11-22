package com.example.user.yellowridesafety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParentHome extends AppCompatActivity implements View.OnClickListener {

    Button home, myprofile, track, report, complaint, noti, logo, vwqrr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);

        vwqrr= (Button) findViewById(R.id.vwqrbtn);
        logo= (Button) findViewById(R.id.log);
        home = (Button) findViewById(R.id.homebtn);
        myprofile = (Button) findViewById(R.id.viewbtn);
        myprofile.setOnClickListener(this);
        track = (Button) findViewById(R.id.trackbtn);
        report = (Button) findViewById(R.id.reportbtn);
        complaint = (Button) findViewById(R.id.compbtn);
        noti = (Button) findViewById(R.id.notibtn);
        report.setOnClickListener(this);
        home.setOnClickListener(this);
        complaint.setOnClickListener(this);
        track.setOnClickListener(this);
        noti.setOnClickListener(this);
        logo.setOnClickListener(this);
        vwqrr.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == home) {

            Intent h = new Intent(this, ParentHome.class);
            startActivity(h);
        } else if (view == myprofile) {
            Intent my = new Intent(this, EditParentReg.class);
            startActivity(my);
        } else if (view == complaint) {
            Intent s = new Intent(this, ParentComplaints.class);
            startActivity(s);
        } else if (view == report) {
            Intent sse = new Intent(this, ChildAbsent.class);
            startActivity(sse);
        } else if (view == track) {
            Intent v = new Intent(this, ParentTrackChild.class);
            startActivity(v);

        } else if (view == noti) {
            Intent v = new Intent(this, Viewparentnotifications.class);
            startActivity(v);

        }else if (view == logo) {
            Intent v = new Intent(this, ParentLogin.class);
            startActivity(v);

        }else if (view == vwqrr) {
            Intent vp = new Intent(this, ParentViewQr.class);
            startActivity(vp);

        }

        }
    }




