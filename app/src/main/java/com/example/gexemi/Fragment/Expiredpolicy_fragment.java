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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gexemi.Adapter.AssignpolicyAdapter;
import com.example.gexemi.Adapter.BalancePolicyAdapter;
import com.example.gexemi.Adapter.ExpiredpolicyAdapter;
import com.example.gexemi.Adapter.UninstallpolicyAdapter;
import com.example.gexemi.PolicyClass;
import com.example.gexemi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Expiredpolicy_fragment extends Fragment {

    String expiredapi_url = "http://goelectronix.in/api/app/VendorPolicies";
    SearchView searchView;
    ExpiredpolicyAdapter expiredpolicyAdapter;
    List<PolicyClass> policies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expiredpolicy_fragment, container, false);
        searchView = view.findViewById(R.id.searchview);
        TextView no_record = view.findViewById(R.id.no_record);
        RecyclerView expiredrecycler =  view.findViewById(R.id.expired_recyclerview);
        expiredrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        policies =new ArrayList<>();

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MenuItemCompat.expandActionView(searchView.);
                searchView.requestFocus();
                Log.e("TAG", "onClick: Searchview Calling " );
            }
        });

        setupSearchView();

        SharedPreferences preferences;
        preferences = getContext().getSharedPreferences("VendorDetails", Context.MODE_PRIVATE);
        String mVendorID = preferences.getString("VendorID","");

        JSONObject params = new JSONObject();

        //get value from local database from login API
        try {
            params.put("VendorID",Integer.parseInt(mVendorID));
            params.put("PolicyStatus",4);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, expiredapi_url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getBoolean("success")== true) {

                        JSONArray response_result = response.getJSONArray("policyList");

                        for (int i = 0; i < response_result.length(); i++) {

                            JSONObject object = response_result.getJSONObject(i);
                            String policynumber =  object.getString("policyNumber");
                            String customerName =  object.getString("customerName");
                            String date =  object.getString("date");
                            String phoneno =  object.getString("phoneNumber");

                            String vendorname =  preferences.getString("VendorName","");

                            policies.add(new PolicyClass(policynumber,vendorname,customerName,date,phoneno));
                        }
                        if (policies.size() == 0) {
                            no_record.setVisibility(View.VISIBLE);
                            expiredrecycler.setVisibility(View.GONE);
                        } else {
                            no_record.setVisibility(View.GONE);
                            expiredrecycler.setVisibility(View.VISIBLE);
                        }
                        expiredpolicyAdapter = new ExpiredpolicyAdapter(getContext(),policies);
                        expiredrecycler.setAdapter(expiredpolicyAdapter);

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

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });
    }

    private void filterlist(String newText) {
        List<PolicyClass> filteredList = new ArrayList<>();
        for (PolicyClass policy : policies){
            if (policy.getCust_name().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(policy);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }else{

            expiredpolicyAdapter.setfilteredList(filteredList);
        }
    }
}