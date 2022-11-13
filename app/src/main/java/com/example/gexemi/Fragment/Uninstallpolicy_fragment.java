package com.example.gexemi.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gexemi.Adapter.BalancePolicyAdapter;
import com.example.gexemi.Adapter.UninstallpolicyAdapter;
import com.example.gexemi.PolicyClass;
import com.example.gexemi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Uninstallpolicy_fragment extends Fragment {

    String uninstallapi_url = "http://goelectronix.in/api/app/VendorPolicies";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_uninstallpolicy_fragment, container, false);

        RecyclerView uninstallrecycler =  view.findViewById(R.id.uninstall_recyclerview);
        uninstallrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        List<PolicyClass> policies =new ArrayList<>();

        JSONObject params = new JSONObject();

        //get value from local database from login API
        try {
            params.put("VendorID",7);
            params.put("CustomerStatus",1);
            params.put("PolicyStatus",3);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, uninstallapi_url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getBoolean("success")== true) {

                        JSONArray response_result = response.getJSONArray("policyList");

                        for (int i = 0; i < response_result.length(); i++) {

                            JSONObject object = response_result.getJSONObject(i);
                            String policynumber =  object.getString("policyNumber");
                            String policystatus =  object.getString("policyStatus");
                            Integer policyID =  object.getInt("policyID");

                            //get name from login page API
                            String vendorname =  "Rushikesh_vendor";
                            String shop_name = "Uninstall Vendor";

                            policies.add(new PolicyClass(policynumber,vendorname,shop_name,policyID));
                        }
                        uninstallrecycler.setAdapter(new UninstallpolicyAdapter(getContext(),policies));

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