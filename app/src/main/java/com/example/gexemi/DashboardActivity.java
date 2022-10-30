package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    TextView tv_qr, tv_policy,tv_users, tv_myprofile;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tv_qr = findViewById(R.id.TV_qr);
        tv_policy = findViewById(R.id.TV_policy);
        tv_users = findViewById(R.id.TV_user);
        tv_myprofile = findViewById(R.id.TV_myprofile);
        mDialog = new Dialog(this);

        tv_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.qr_popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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


    }
}