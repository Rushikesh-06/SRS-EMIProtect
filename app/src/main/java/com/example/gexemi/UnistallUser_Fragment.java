package com.example.gexemi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnistallUser_Fragment extends Fragment {


TextView moredetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_unistall_user_, container, false);
        moredetails = view.findViewById(R.id.moredetails);

        moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoreDeatilsActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }
}