package com.example.gexemi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gexemi.MoreDeatilsActivity;
import com.example.gexemi.PolicyClass;
import com.example.gexemi.R;
import com.example.gexemi.UserClass;

import java.util.List;

public class AlluserAdapter extends RecyclerView.Adapter<AlluserAdapter.AlluserHolder> {

    private Context context;
    List<UserClass> userClassList;

    public AlluserAdapter(Context context, List<UserClass> users ) {
        this.context = context;
        userClassList = users;
    }

    @NonNull
    @Override
    public AlluserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new AlluserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlluserHolder holder, int position) {

        UserClass user = userClassList.get(position);
        holder.all_username.setText(user.getUsername());
        holder.all_custid.setText(""+user.getCustid());
        holder.all_phoneno.setText(user.getPhoneno());
        holder.date.setText(user.getDate());
        String current_userstatus = user.getCust_status();

        if (current_userstatus.equals("UNLOCKED")) {
            holder.userstatus.setBackgroundResource(R.drawable.unlockstatus);
        }else if (current_userstatus.equals("ACTIVATE")){
            holder.userstatus.setBackgroundResource(R.drawable.unlockstatus);
        }else if(current_userstatus.equals("LOCKED")){
            holder.userstatus.setBackgroundResource(R.drawable.lockstatus);
        }

        holder.item_moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoreDeatilsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("Username",user.getUsername());
                bundle.putString("CustID",user.getCustid().toString());
                bundle.putString("Phoneno",user.getPhoneno());
                bundle.putString("Serialno",user.getSerialNo());

//                Log.e("serialno :" ,user.getSerialNo());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno =  user.getPhoneno();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + phoneno;
                callIntent.setData(Uri.parse(temp));
                context.startActivity(callIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return userClassList.size();
    }

    public class AlluserHolder extends RecyclerView.ViewHolder {

        TextView all_username,all_custid,all_phoneno,item_moredetails,userstatus,date;
        ImageView btn_call;

        public AlluserHolder(@NonNull View itemView) {
            super(itemView);

            all_username = itemView.findViewById(R.id.item_username);
            all_custid = itemView.findViewById(R.id.item_custid);
            all_phoneno = itemView.findViewById(R.id.item_phoneno);
            item_moredetails = itemView.findViewById(R.id.item_moredetails);
            userstatus = itemView.findViewById(R.id.userstatus);
            date = itemView.findViewById(R.id.date);
            btn_call = itemView.findViewById(R.id.btn_call);

        }
    }
}
