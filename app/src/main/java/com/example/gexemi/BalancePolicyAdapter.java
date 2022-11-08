package com.example.gexemi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BalancePolicyAdapter extends RecyclerView.Adapter<BalancePolicyAdapter.PolicyHolder> {

    private Context context;
    List<PolicyClass> policylist;

    public BalancePolicyAdapter(Context context, List<PolicyClass> policies) {
        this.context = context;
        policylist = policies;
    }

    @NonNull
    @Override
    public PolicyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.policy_item,parent,false);
        return new PolicyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PolicyHolder holder, int position) {

        PolicyClass policy = policylist.get(position);
        holder.policy_number.setText(policy.getPolicyNumber());
        holder.vendor_name.setText(policy.getVendorName());
        holder.vendor_code.setText(policy.getVendorCode());

    }

    @Override
    public int getItemCount() {
        return policylist.size();
    }

    public class PolicyHolder extends RecyclerView.ViewHolder {

        TextView policy_number,vendor_name,vendor_code;

        public PolicyHolder(@NonNull View itemView) {
            super(itemView);

            policy_number = itemView.findViewById(R.id.policy_number);
            vendor_code = itemView.findViewById(R.id.vendor_code);
            vendor_name = itemView.findViewById(R.id.vendor_name);
        }
    }
}
