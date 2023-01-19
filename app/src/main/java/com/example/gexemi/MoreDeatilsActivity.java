package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoreDeatilsActivity extends AppCompatActivity {

    private  String TAG = getClass().getSimpleName() ;
    TextView MD_username,MD_registerno,MD_currentphoneno,sim2,MD_mailID,MD_emidate,MD_downpayment, MD_emiamount;
    TextView MD_emitenure, MD_financecompany,MD_deviceaname,MD_imeino,MD_currentstatus,MD_unlockcode,MD_deviceamount,additionalcomment;

    ImageView MD_custphoto;
    Button btn_lockuser,btn_unlockuser,btn_uninstalluser, btn_syncCust;

    String Cust_detailAPI = "http://goelectronix.in/api/app/CustomerDetails";
    String UserAction_API = "http://goelectronix.in/api/app/CustomerStatusSync";

    String IMEI_No;
    String deviceID;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Integer userstatusID = 0;

    String cust_currentstatus;
    String serialno;
    ProgressDialog mdialog;
    private String syncapi =  "http://goelectronix.in/api/app/CustomerStatusSync";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_deatils);

        mdialog = new ProgressDialog(MoreDeatilsActivity.this);
        mdialog.setMessage("Please Wait");
        mdialog.setCancelable(false);
        mdialog.show();

        preferences = getSharedPreferences("VendorDetails", MODE_PRIVATE);
        editor = preferences.edit();

        MD_username = findViewById(R.id.MD_username);
        MD_registerno = findViewById(R.id.MD_registerno);
        MD_currentphoneno = findViewById(R.id.MD_currentphoneno);
        sim2 = findViewById(R.id.sim2);
        MD_mailID = findViewById(R.id.MD_mailID);
//        MD_loandate = findViewById(R.id.MD_loandate);
        MD_emidate = findViewById(R.id.MD_emidate);
        MD_downpayment = findViewById(R.id.MD_downpayment);
        MD_emiamount = findViewById(R.id.MD_emiamount);
        MD_emitenure = findViewById(R.id.MD_emitenure);
        MD_financecompany = findViewById(R.id.MD_financecompany);
        MD_deviceaname = findViewById(R.id.MD_deviceaname);
        MD_imeino = findViewById(R.id.MD_imeino);
        MD_currentstatus = findViewById(R.id.MD_currentstatus);
        MD_unlockcode = findViewById(R.id.MD_unlockcode);
        MD_custphoto = findViewById(R.id.MD_custphoto);
        MD_deviceamount = findViewById(R.id.MD_deviceamount);
        additionalcomment = findViewById(R.id.additionalcomment);

        btn_lockuser = findViewById(R.id.btn_lockuser);
        btn_unlockuser = findViewById(R.id.btn_unlockuser);
        btn_uninstalluser = findViewById(R.id.Btn_uninstall);
        btn_syncCust = findViewById(R.id.btn_syncCust);

        getCustDetails();

        btn_syncCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callsyncAPI();
                
            }
        });



        btn_lockuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MoreDeatilsActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        useraction(3);
                        // Do nothing but close the dialog
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
//                useraction(3);
            }
        });

        btn_unlockuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*useraction(4);*/

                AlertDialog.Builder builder = new AlertDialog.Builder(MoreDeatilsActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        useraction(4);
                        // Do nothing but close the dialog
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btn_uninstalluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MoreDeatilsActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        useraction(5);
                        // Do nothing but close the dialog
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

    private void callsyncAPI() {

        JSONObject params = new JSONObject();
        //get value from local database from login API
        try {
            params.put("IsSyncCustomer",true);
            params.put("DeviceID",deviceID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, syncapi, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("Sync API Response",response.toString());

                try {
                    if(response.getBoolean("success")== true) {
                        Toast.makeText(MoreDeatilsActivity.this, "Sync Success", Toast.LENGTH_SHORT).show();
                    }else {
                        mdialog.dismiss();
                        Toast.makeText(MoreDeatilsActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("Data",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mdialog.dismiss();
                Log.e("error",error.getMessage());
            }
        });

        Volley.newRequestQueue(MoreDeatilsActivity.this).add(objectRequest);


    }


    private void getCustDetails() {
        JSONObject params = new JSONObject();

        Bundle bundle = getIntent().getExtras();
        serialno = bundle.getString("Serialno");

        //get value from local database from login API
        try {
            params.put("SerialNumber",serialno);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, Cust_detailAPI, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getBoolean("success")== true) {
                        mdialog.dismiss();

                        //get and set all the values from API
                        MD_username.setText(response.getString("customerName"));
                        MD_registerno.setText(response.getString("mobileNumber"));
                        MD_mailID.setText(response.getString("emailID"));
                        MD_deviceaname.setText(response.getString("mobileBrand"));
                        MD_imeino.setText(response.getString("imeiNumber"));
                        MD_downpayment.setText(response.getString("downPayment"));
                        MD_emiamount.setText(response.getString("emiAmount"));
                        MD_financecompany.setText(response.getString("financiarName"));
                        MD_deviceamount.setText(response.getString("deviceAmount"));
                        MD_emidate.setText(response.getString("emiDate").split("T")[0]);
                        MD_emitenure.setText(response.getString("emiTenure"));
                        MD_currentstatus.setText(response.getString("customerStatus"));
                        MD_currentphoneno.setText(response.getString("simNumber1"));
                        sim2.setText(response.getString("simNumber2"));
                        additionalcomment.setText(response.getString("additionalComment"));

                        if (response.getString("customerStatus").equals("LOCKED")){
                            btn_lockuser.setVisibility(View.GONE);
                            btn_unlockuser.setVisibility(View.VISIBLE);
                        }else if (response.getString("customerStatus").equals("UNLOCKED")){
                            btn_lockuser.setVisibility(View.VISIBLE);
                            btn_unlockuser.setVisibility(View.GONE);
                        }else if (response.getString("customerStatus").equals("UNINSTALLED")) {
                            btn_lockuser.setVisibility(View.GONE);
                            btn_unlockuser.setVisibility(View.GONE);
                            btn_uninstalluser.setVisibility(View.GONE);
                        }
                        String photourl = response.getString("photoURL");
                        Glide.with(MoreDeatilsActivity.this).load(photourl).into(MD_custphoto);

                        IMEI_No = response.getString("imeiNumber");
                        deviceID = response.getString("deviceID");

                    }else {
                        mdialog.dismiss();
                        Toast.makeText(MoreDeatilsActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("Data",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mdialog.dismiss();
                Log.e("error",error.getMessage());
            }
        });


        //btn visibility



        Volley.newRequestQueue(MoreDeatilsActivity.this).add(objectRequest);

    }

    private void useraction(int i) {

        JSONObject useraction_params = new JSONObject();

        try {
            useraction_params.put("StatusID",i);
            useraction_params.put("IMEINumber",IMEI_No);
            useraction_params.put("DeviceID",deviceID);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest useraction_Request = new JsonObjectRequest(Request.Method.POST, UserAction_API,useraction_params , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e(TAG, "onResponse: "+response );
                try {
                    if(response.getBoolean("success")== true) {

                        getCustDetails();

                        if (i==3){
                            btn_lockuser.setVisibility(View.GONE);
                            btn_unlockuser.setVisibility(View.VISIBLE);
                        }else if (i==4){
                            btn_lockuser.setVisibility(View.VISIBLE);
                            btn_unlockuser.setVisibility(View.GONE);
                        }else if (i==5){
                            btn_uninstalluser.setVisibility(View.GONE);
                            btn_unlockuser.setVisibility(View.GONE);
                            btn_lockuser.setVisibility(View.GONE);

                            String uninstal_date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());

                            editor.putString("UninstalledDate",uninstal_date);
                        }


                        Toast.makeText(MoreDeatilsActivity.this, "Request Successfully Completed", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(MoreDeatilsActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error",error.getMessage());
            }
        });

        Volley.newRequestQueue(MoreDeatilsActivity.this).add(useraction_Request);


    }
}