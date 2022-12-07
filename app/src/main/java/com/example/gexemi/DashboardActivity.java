package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    TextView tv_qr, tv_policy,tv_users, tv_myprofile,dashboard_username,dashboard_vendorID,support;
    Dialog mDialog;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//Wa.me/919892580308
        getSupportActionBar().setTitle("Goelectronix Technologies Pvt Ltd");
        tv_qr = findViewById(R.id.TV_qr);
        tv_policy = findViewById(R.id.TV_policy);
        tv_users = findViewById(R.id.TV_user);
        tv_myprofile = findViewById(R.id.TV_myprofile);
        dashboard_username = findViewById(R.id.dashboard_username);
        dashboard_vendorID = findViewById(R.id.dashboard_vendorID);
        support = findViewById(R.id.support);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send/?phone=919892580308&text=*Hii%20Am%20retailer%20need%20support*"));
                startActivity(intent);
            }
        });
        mDialog = new Dialog(this);

        preferences = getSharedPreferences("VendorDetails",MODE_PRIVATE);
        dashboard_username.setText(preferences.getString("VendorName",""));
        dashboard_vendorID.setText("VendorID : "+preferences.getString("VendorID",""));

        tv_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.qr_popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();
            }
        });

        tv_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,Policy.class);
                startActivity(intent);
            }
        });

        tv_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent useractivity = new Intent(DashboardActivity.this,User_activity.class);
                startActivity(useractivity);
            }
        });

        tv_myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });


    }
}