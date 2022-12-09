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

public class UninstallpolicyAdapter extends RecyclerView.Adapter<UninstallpolicyAdapter.UninstallpolicyHolder> {

    private Context context;
    List<PolicyClass> policylist;

    public UninstallpolicyAdapter(Context context, List<PolicyClass> policies) {
        this.context = context;
        policylist = policies;
    }

    @NonNull
    @Override
    public UninstallpolicyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.policy_item,parent,false);
        return new UninstallpolicyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UninstallpolicyHolder holder, int position) {

        PolicyClass policy = policylist.get(position);
        holder.policy_number.setText(policy.getPolicyNumber());
        holder.vendor_name.setText(policy.getCust_name());
        holder.date.setText(policy.getDate());
        holder.phoneno.setText(policy.getPhoneNo());
        holder.date_title.setText("Uninstall Date");
    }

    @Override
    public int getItemCount() {
        return policylist.size();
    }

    public class UninstallpolicyHolder extends RecyclerView.ViewHolder {

        TextView policy_number,vendor_name,date,date_title,phoneno;

        public UninstallpolicyHolder(@NonNull View itemView) {
            super(itemView);

            policy_number = itemView.findViewById(R.id.policy_number);
            vendor_name = itemView.findViewById(R.id.vendor_name);
            date = itemView.findViewById(R.id.Date);
            phoneno = itemView.findViewById(R.id.phoneno);
            date_title = itemView.findViewById(R.id.date_title);
        }
    }
}
