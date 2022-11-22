package com.example.gexemi.Fragment;

import android.content.Intent;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gexemi.Adapter.AlluserAdapter;
import com.example.gexemi.Adapter.UninstalluserAdapter;
import com.example.gexemi.MoreDeatilsActivity;
import com.example.gexemi.R;
import com.example.gexemi.UserClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UnistallUser_Fragment extends Fragment {

    String uninstalluser_api = "http://goelectronix.in/api/app/VendorCustomers";
//    TextView moredetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_unistall_user_, container, false);

        /*moredetails = view.findViewById(R.id.moredetails);
        moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoreDeatilsActivity.class);
                startActivity(intent);
            }
        });*/

        RecyclerView recyclerView =  view.findViewById(R.id.uninstalluser_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<UserClass> users =new ArrayList<>();


        JSONObject params = new JSONObject();

        //get value from local database from login API
        try {
            params.put("VendorID",7);
            params.put("CustomerStatus",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, uninstalluser_api, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getBoolean("success")== true) {

                        JSONArray response_result = response.getJSONArray("userList");
                        for (int i = 0; i < response_result.length(); i++) {

                            JSONObject object = response_result.getJSONObject(i);
                            String result_username = object.getString("name");
                            Integer result_custid = object.getInt("customerID");
                            String result_phoneno = object.getString("mobileNumber");
                            String serialno = object.getString("serialNumber");


                            users.add(new UserClass(result_username,result_custid,result_phoneno,serialno));
                        }
                        recyclerView.setAdapter(new UninstalluserAdapter(getContext(),users));

                    }else {
                        Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
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

        Volley.newRequestQueue(getContext()).add(objectRequest);




        return view;
    }
}