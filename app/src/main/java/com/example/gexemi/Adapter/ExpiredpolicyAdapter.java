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

public class ExpiredpolicyAdapter extends RecyclerView.Adapter<ExpiredpolicyAdapter.ExpiredpolicyHolder> {

    private Context context;
    List<PolicyClass> policylist;

    public ExpiredpolicyAdapter(Context context, List<PolicyClass> policies) {
        this.context = context;
        policylist = policies;
    }

    @NonNull
    @Override
    public ExpiredpolicyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.balancepolicy_item,parent,false);
        return new ExpiredpolicyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpiredpolicyHolder holder, int position) {

        PolicyClass policy = policylist.get(position);
        holder.policy_number.setText(policy.getPolicyNumber());
        holder.date.setText(policy.getDate());
    }

    @Override
    public int getItemCount() {
        return policylist.size();
    }

    public class ExpiredpolicyHolder extends RecyclerView.ViewHolder {

        TextView policy_number,vendor_name,date,date_title;

        public ExpiredpolicyHolder(@NonNull View itemView) {
            super(itemView);

            policy_number = itemView.findViewById(R.id.policy_number);
            date = itemView.findViewById(R.id.Date);
        }
    }
}
