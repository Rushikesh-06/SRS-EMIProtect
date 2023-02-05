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
import com.example.gexemi.Adapter.AlluserAdapter;
import com.example.gexemi.Adapter.LockuserAdapter;
import com.example.gexemi.R;
import com.example.gexemi.UserClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LockUser_Fragment extends Fragment {

    String alluser_api = "http://goelectronix.in/api/app/VendorCustomers";
    SearchView searchView;
    List<UserClass> users;
    LockuserAdapter lockuserAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lock_user_, container, false);
        TextView no_record = view.findViewById(R.id.no_record);
        RecyclerView recyclerView =  view.findViewById(R.id.lockuser_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        users =new ArrayList<>();

        searchView = view.findViewById(R.id.searchview);
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
            params.put("CustomerStatus",3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, alluser_api, params, new Response.Listener<JSONObject>() {
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
                            String Cust_status = object.getString("customerStatus");
                            String Date = object.getString("date");
                            String IMEI = object.getString("imeiNumber");

                            users.add(new UserClass(result_username, result_custid, result_phoneno, serialno, Cust_status,Date,IMEI));
                        }
                        if (users.size() == 0) {
                            no_record.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            no_record.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            TextView count = view.findViewById(R.id.count);
                            count.setText("Total Lock Users : "+users.size());
                        }
                        lockuserAdapter = new LockuserAdapter(getContext(),users);
                        recyclerView.setAdapter(lockuserAdapter);

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
        List<UserClass> filteredList = new ArrayList<>();
        for (UserClass user : users ){
            if (user.getUsername().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(user);
            }else if (user.getPhoneno().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(user);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }else{
            lockuserAdapter.setfilteredList(filteredList);
        }
    }
}