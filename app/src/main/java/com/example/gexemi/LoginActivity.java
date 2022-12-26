package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
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

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    ImageView password_icon;
    Button btn_login;
    TextView support;
    private boolean passwordshowing = false;
    SessionManage session;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    String login_api = "http://goelectronix.in/api/app/VendorLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Goelectronix Technologies Pvt Ltd");
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        password_icon = findViewById(R.id.password_icon);
        btn_login = findViewById(R.id.btn_signin);
        support = findViewById(R.id.support);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send/?phone=919892580308&text=*Hii%20Am%20retailer%20need%20support*"));
                startActivity(intent);
            }
        });

        preferences = getSharedPreferences("VendorDetails", MODE_PRIVATE);
        editor = preferences.edit();

        session = new SessionManage(LoginActivity.this);
        if (session.getLoginStatus()) {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }

        password_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordshowing) {
                    passwordshowing = false;
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password_icon.setImageResource(R.drawable.ic_password_show);
                } else {
                    passwordshowing = true;
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password_icon.setImageResource(R.drawable.ic_password_hide);
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });


    }


    private void Login() {

        ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Please Wait ...");
        dialog.setCancelable(false);
        dialog.show();

        JSONObject params = new JSONObject();
        //get value from local database from login API
        try {
            params.put("EmailID", username.getText().toString());
            params.put("Password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, login_api, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();
                try {
                    if (response.getBoolean("success") == true) {

                        session.addLoginStatus(true);

                        String Vendorcode = response.getString("vendorCode");
                        Integer VendorID = response.getInt("vendorID");
                        String VendorUserID = response.getString("vendorUserID");
                        String VendorName = response.getString("vendorName");
                        String VendorMobileNumber = response.getString("vendorMobileNumber");
                        String ShopName = response.getString("shopName");
                        String EmailId = response.getString("emailID");

                        editor.putString("Vendorcode", Vendorcode);
                        editor.putString("VendorID", VendorID.toString());
                        editor.putString("VendorUserID", VendorUserID);
                        editor.putString("VendorName", VendorName);
                        editor.putString("VendorMobileNumber", VendorMobileNumber);
                        editor.putString("ShopName", ShopName);
                        editor.putString("EmailId", EmailId);

                        editor.commit();


                        Intent intent =new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        String error = response.getString("message");
                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "Login Failed...Check MailID and Password", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(LoginActivity.this).add(objectRequest);

    }

}