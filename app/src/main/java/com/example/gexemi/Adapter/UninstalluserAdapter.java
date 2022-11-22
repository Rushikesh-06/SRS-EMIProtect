package com.example.gexemi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gexemi.MoreDeatilsActivity;
import com.example.gexemi.R;
import com.example.gexemi.UserClass;

import java.util.List;

public class UninstalluserAdapter extends RecyclerView.Adapter<UninstalluserAdapter.UninstalluserHolder> {

    private Context context;
    List<UserClass> userClassList;

    public UninstalluserAdapter(Context context, List<UserClass> users ) {
        this.context = context;
        userClassList = users;
    }

    @NonNull
    @Override
    public UninstalluserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new UninstalluserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UninstalluserHolder holder, int position) {

        UserClass user = userClassList.get(position);
        holder.uninstall_username.setText(user.getUsername());
        holder.uninstall_custid.setText(""+user.getCustid());
        holder.uninstall_phoneno.setText(user.getPhoneno());

        holder.item_moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoreDeatilsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("Username",user.getUsername());
                bundle.putString("CustID",user.getCustid().toString());
                bundle.putString("Phoneno",user.getPhoneno());
                bundle.putString("Serialno",user.getSerialNo());

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userClassList.size();

    }

    public class UninstalluserHolder extends RecyclerView.ViewHolder {

        TextView uninstall_username,uninstall_custid,uninstall_phoneno,item_moredetails;


        public UninstalluserHolder(@NonNull View itemView) {
            super(itemView);

            uninstall_username = itemView.findViewById(R.id.item_username);
            uninstall_custid = itemView.findViewById(R.id.item_custid);
            uninstall_phoneno = itemView.findViewById(R.id.item_phoneno);
            item_moredetails = itemView.findViewById(R.id.item_moredetails);

        }
    }
}
