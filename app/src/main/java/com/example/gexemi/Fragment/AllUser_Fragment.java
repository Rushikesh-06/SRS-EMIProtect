package com.example.gexemi.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gexemi.Adapter.AlluserAdapter;
import com.example.gexemi.Adapter.BalancePolicyAdapter;
import com.example.gexemi.PolicyClass;
import com.example.gexemi.R;
import com.example.gexemi.UserClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AllUser_Fragment extends Fragment {

    String alluser_api = "http://goelectronix.in/api/app/VendorCustomers";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_user_, container, false);


        TextView no_record = view.findViewById(R.id.no_record);
        RecyclerView recyclerView = view.findViewById(R.id.alluser_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<UserClass> users = new ArrayList<>();

        SharedPreferences preferences;
        preferences = getContext().getSharedPreferences("VendorDetails", Context.MODE_PRIVATE);
        String mVendorID = preferences.getString("VendorID", "");

        JSONObject params = new JSONObject();

        //get value from local database from login API
        try {
            params.put("VendorID", Integer.parseInt(mVendorID));
            params.put("CustomerStatus", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, alluser_api, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getBoolean("success") == true) {

                        JSONArray response_result = response.getJSONArray("userList");

                        for (int i = 0; i < response_result.length(); i++) {

                            JSONObject object = response_result.getJSONObject(i);

                            String result_username = object.getString("name");
                            Integer result_custid = object.getInt("customerID");
                            String result_phoneno = object.getString("mobileNumber");
                            String serialno = object.getString("serialNumber");
                            String Cust_status = object.getString("customerStatus");


                            users.add(new UserClass(result_username, result_custid, result_phoneno, serialno, Cust_status));
                        }
                        if (users.size() == 0) {
                            no_record.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            no_record.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                        recyclerView.setAdapter(new AlluserAdapter(getContext(), users));

                    } else {
                        Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("Data", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error", error.getMessage());
            }
        });

        Volley.newRequestQueue(getContext()).add(objectRequest).setRetryPolicy(new DefaultRetryPolicy(30000, 2, 0));


        return view;
    }
}