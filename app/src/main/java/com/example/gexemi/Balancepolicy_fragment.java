package com.example.gexemi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Balancepolicy_fragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balancepolicy_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager());

        return view;
    }
}