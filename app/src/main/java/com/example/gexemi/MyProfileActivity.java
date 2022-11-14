package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    String updateprofile_api = "http://goelectronix.in/api/app/UpdateVendor";

    TextView profile_username,profile_shopname,profile_phoneno,TV_profile_mailid,profile_location,profile_address,
            profile_pincode,profile_pancardno,profile_adharcardno,profile_gstno,profile_vendorcode,TV_profile_display_contactno,
            TV_profile_displayname,TV_profile_cust_paymentno;
    ImageView profile_uploadimage;

    //updation fields
    Button btn_updatevendor;
    EditText ET_profile_mailid,ET_profile_display_contactno,ET_profile_cust_paymentno,ET_profile_displayname;
    ImageView edit_displaycontactno,edit_email,edit_displaypaymentno,edit_displayname;
    String updated_mailID = "", updated_displayname = "", updated_displaycontactno= "", updated_displaypaymentno ="";
    ProgressDialog dialog ;
    String response_tvmailid , response_tvdisplaycontactno,response_tvdispplaypaymentno,response_tvdisplayname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        edit_displaycontactno = findViewById(R.id.edit_displaycontactno);
        edit_email = findViewById(R.id.edit_email);
        edit_displaypaymentno = findViewById(R.id.edit_displaypaymentno);
        edit_displayname = findViewById(R.id.edit_displayname);
        btn_updatevendor = findViewById(R.id.btn_updatevendor);
        dialog = new ProgressDialog(MyProfileActivity.this);

        profile_username = findViewById(R.id.profile_username);
        profile_shopname = findViewById(R.id.profile_shopname);
        profile_phoneno = findViewById(R.id.profile_phoneno);
        TV_profile_mailid = findViewById(R.id.TV_profile_mailid);
        ET_profile_mailid = findViewById(R.id.ET_profile_mailid);
        profile_location = findViewById(R.id.profile_location);
        profile_address = findViewById(R.id.profile_address);
        profile_pincode = findViewById(R.id.profile_pincode);
        profile_pancardno = findViewById(R.id.profile_pancardno);
        profile_adharcardno = findViewById(R.id.profile_adharcardno);
        profile_gstno = findViewById(R.id.profile_gstno);
        profile_vendorcode = findViewById(R.id.profile_vendorcode);
        TV_profile_display_contactno = findViewById(R.id.TV_profile_display_contactno);
        ET_profile_displayname = findViewById(R.id.ET_profile_displayname);
        ET_profile_display_contactno = findViewById(R.id.ET_profile_display_contactno);
        TV_profile_displayname = findViewById(R.id.TV_profile_displayname);
        profile_uploadimage = findViewById(R.id.profile_uploadimage);
        TV_profile_cust_paymentno = findViewById(R.id.TV_profile_cust_paymentno);
        ET_profile_cust_paymentno = findViewById(R.id.ET_profile_cust_paymentno);



        ET_profile_mailid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updated_mailID = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ET_profile_display_contactno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updated_displaycontactno = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ET_profile_displayname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updated_displayname = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ET_profile_cust_paymentno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updated_displaypaymentno = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //getdata from volley API

        getdatafromvolley();

        //Update Vendor

        edit_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TV_profile_mailid.setVisibility(View.GONE);
                btn_updatevendor.setVisibility(View.VISIBLE);
                ET_profile_mailid.setVisibility(View.VISIBLE);
            }
        });

        edit_displayname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TV_profile_displayname.setVisibility(View.GONE);
                btn_updatevendor.setVisibility(View.VISIBLE);
                ET_profile_displayname.setVisibility(View.VISIBLE);
            }
        });

        edit_displaycontactno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TV_profile_display_contactno.setVisibility(View.GONE);
                btn_updatevendor.setVisibility(View.VISIBLE);
                ET_profile_display_contactno.setVisibility(View.VISIBLE);
            }
        });

        edit_displaypaymentno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TV_profile_cust_paymentno.setVisibility(View.GONE);
                btn_updatevendor.setVisibility(View.VISIBLE);
                ET_profile_cust_paymentno.setVisibility(View.VISIBLE);
            }
        });

        btn_updatevendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Updating");
                dialog.setCancelable(false);
                dialog.show();
                updatevendor();
            }
        });



    }

    private void getdatafromvolley() {

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

                        response_tvmailid = response.getString("emailID");
                        response_tvdisplaycontactno = response.getString("displayContactNumber");
                        response_tvdispplaypaymentno = response.getString("paymentNumber");
                        response_tvdisplayname = response.getString("displayContactName");

                        profile_username.setText(response.getString("vendorName"));
                        profile_shopname.setText(response.getString("shopName"));
                        profile_phoneno.setText(response.getString("vendorMobileNumber"));
                        TV_profile_mailid.setText(response.getString("emailID"));
                        profile_location.setText(response.getString("location"));
                        profile_pincode.setText(response.getString("pincode"));
                        profile_address.setText(response.getString("address"));
                        profile_gstno.setText(response.getString("gstinNumber"));
                        profile_vendorcode.setText(response.getString("vendorCode"));
                        profile_pancardno.setText(response.getString("panNumber"));
                        profile_adharcardno.setText(response.getString("aadhaarNumber"));
                        TV_profile_display_contactno.setText(response.getString("displayContactNumber"));
                        TV_profile_displayname.setText(response.getString("displayContactName"));
                        TV_profile_cust_paymentno.setText(response.getString("paymentNumber"));

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

    private void updatevendor() {

        JSONObject update_params = new JSONObject();
        //get value from local database from login API
        try {

            update_params.put("VendorCode",profile_vendorcode.getText().toString());

            if (!updated_mailID.isEmpty()){
                update_params.put("EmailID",updated_mailID);
            }else{
                update_params.put("EmailID",response_tvmailid);
            }
            if (!updated_displayname.isEmpty()){
                update_params.put("DisplayContactName",updated_displayname);
            }else{
                update_params.put("DisplayContactName",response_tvdisplayname);
            }
            if (!updated_displaycontactno.isEmpty()){
                update_params.put("DisplayContactNumber",updated_displaycontactno);
            }else{
                update_params.put("DisplayContactNumber",response_tvdisplaycontactno);
            }
            if (!updated_displaypaymentno.isEmpty()){
                update_params.put("paymentNumber",updated_displaypaymentno);
            }else{
                update_params.put("paymentNumber",response_tvdispplaypaymentno);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest update_objectRequest = new JsonObjectRequest(Request.Method.POST, updateprofile_api, update_params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Myprofile_response",response.toString());

                try {
                    if(response.getBoolean("success")== true) {

                        dialog.dismiss();

                        getdatafromvolley();



                        TV_profile_displayname.setVisibility(View.VISIBLE);
                        TV_profile_mailid.setVisibility(View.VISIBLE);
                        TV_profile_cust_paymentno.setVisibility(View.VISIBLE);
                        TV_profile_display_contactno.setVisibility(View.VISIBLE);

                        ET_profile_mailid.setVisibility(View.GONE);
                        ET_profile_displayname.setVisibility(View.GONE);
                        ET_profile_display_contactno.setVisibility(View.GONE);
                        ET_profile_cust_paymentno.setVisibility(View.GONE);

                        btn_updatevendor.setVisibility(View.GONE);

                    }else {
                        dialog.dismiss();
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

        Volley.newRequestQueue(MyProfileActivity.this).add(update_objectRequest);

    }
}