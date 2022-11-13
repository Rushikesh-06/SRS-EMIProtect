package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyProfileActivity extends AppCompatActivity {

    String myprofile_api = "http://goelectronix.in/api/app/VendorDetails";

    TextView profile_username,profile_shopname,profile_phoneno,profile_mailid,profile_location,profile_address,
            profile_pincode,profile_pancardno,profile_adharcardno,profile_gstno,profile_vendorcode,profile_cust_contactno,
            profile_displayname;
    ImageView profile_uploadimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profile_username = findViewById(R.id.profile_username);
        profile_shopname = findViewById(R.id.profile_shopname);
        profile_phoneno = findViewById(R.id.profile_phoneno);
        profile_mailid = findViewById(R.id.profile_mailid);
        profile_location = findViewById(R.id.profile_location);
        profile_address = findViewById(R.id.profile_address);
        profile_pincode = findViewById(R.id.profile_pincode);
        profile_pancardno = findViewById(R.id.profile_pancardno);
        profile_adharcardno = findViewById(R.id.profile_adharcardno);
        profile_gstno = findViewById(R.id.profile_gstno);
        profile_vendorcode = findViewById(R.id.profile_vendorcode);
        profile_cust_contactno = findViewById(R.id.profile_cust_contactno);
        profile_displayname = findViewById(R.id.profile_displayname);
        profile_uploadimage = findViewById(R.id.profile_uploadimage);

        //getdata from volley API

        JSONObject params = new JSONObject();
        //get value from local database from login API
        try {
            params.put("VendorCode","MH7");
            params.put("VendorID",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, myprofile_api, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Myprofile_response",response.toString());

                try {
                    if(response.getBoolean("success")== true) {

                        profile_username.setText(response.getString("vendorName"));
                        profile_shopname.setText(response.getString("shopName"));
                        profile_phoneno.setText(response.getString("vendorMobileNumber"));
                        profile_mailid.setText(response.getString("emailID"));
                        profile_location.setText(response.getString("location"));
                        profile_pincode.setText(response.getString("pincode"));
                        profile_address.setText(response.getString("address"));
                        profile_gstno.setText(response.getString("gstinNumber"));
                        profile_vendorcode.setText(response.getString("vendorCode"));

                    }else {
                        Toast.makeText(MyProfileActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("Data",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error",error.getMessage());
            }
        });

        Volley.newRequestQueue(MyProfileActivity.this).add(objectRequest);



    }
}