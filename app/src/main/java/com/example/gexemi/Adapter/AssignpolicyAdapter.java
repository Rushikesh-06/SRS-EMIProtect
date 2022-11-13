package com.example.gexemi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gexemi.PolicyClass;
import com.example.gexemi.R;

import java.util.List;

public class AssignpolicyAdapter extends RecyclerView.Adapter<AssignpolicyAdapter.Assignpolicyholder> {

    private Context context;
    List<PolicyClass> policylist;

    public AssignpolicyAdapter(Context context, List<PolicyClass> policies) {
        this.context = context;
        policylist = policies;
    }

    @NonNull
    @Override
    public Assignpolicyholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.policy_item,parent,false);
        return new Assignpolicyholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Assignpolicyholder holder, int position) {

        PolicyClass policy = policylist.get(position);
        holder.policy_number.setText(policy.getPolicyNumber());
        holder.vendor_name.setText(policy.getVendorName());
        holder.shop_name.setText(policy.getShop_name());

    }

    @Override
    public int getItemCount() {
        return policylist.size();
    }

    public class Assignpolicyholder extends RecyclerView.ViewHolder {

        TextView policy_number,vendor_name,shop_name;

        public Assignpolicyholder(@NonNull View itemView) {
            super(itemView);

            policy_number = itemView.findViewById(R.id.policy_number);
            vendor_name = itemView.findViewById(R.id.vendor_name);
            shop_name = itemView.findViewById(R.id.shop_name);

        }
    }
}
