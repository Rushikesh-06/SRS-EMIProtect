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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

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
        dashboard_vendorID.setText("VendorID : "+preferences.getString("Vendorcode",""));

        tv_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.qr_popup);
                ImageView IV_qrcode = mDialog.findViewById(R.id.IV_qrcode);
               JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,"http://goelectronix.in/api/app/GetConfiguration",new JSONObject(), new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       try {
                           if (response.getBoolean("success")){
                               Glide.with(DashboardActivity.this).load(response.getString("qrScanCodeURL")).diskCacheStrategy(DiskCacheStrategy.NONE)
                                       .skipMemoryCache(true).into(IV_qrcode);
                           } else {
                               Toast.makeText(DashboardActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }

//

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });
                Volley.newRequestQueue(DashboardActivity.this).add(jsonObjectRequest);

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