package com.example.gexemi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gexemi.MoreDeatilsActivity;
import com.example.gexemi.R;
import com.example.gexemi.UserClass;

import java.util.List;

public class LockuserAdapter extends RecyclerView.Adapter<LockuserAdapter.LockuserHolder> {

    private Context context;
    List<UserClass> userClassList;

    public LockuserAdapter(Context context, List<UserClass> users ) {
        this.context = context;
        userClassList = users;
    }

    @NonNull
    @Override
    public LockuserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new LockuserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LockuserHolder holder, int position) {
        UserClass user = userClassList.get(position);
        holder.lock_username.setText(user.getUsername());
        holder.lock_custid.setText(""+user.getCustid());
        holder.lock_phoneno.setText(user.getPhoneno());
        holder.date.setText(user.getDate());
        String current_userstatus = user.getCust_status();

        if (current_userstatus.equals("UNLOCKED")){
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

    public class LockuserHolder extends RecyclerView.ViewHolder {

        TextView lock_username,lock_custid,lock_phoneno,item_moredetails,userstatus,date;
        ImageView btn_call;

        public LockuserHolder(@NonNull View itemView) {
            super(itemView);

            lock_username = itemView.findViewById(R.id.item_username);
            lock_custid = itemView.findViewById(R.id.item_custid);
            lock_phoneno = itemView.findViewById(R.id.item_phoneno);
            item_moredetails = itemView.findViewById(R.id.item_moredetails);
            userstatus = itemView.findViewById(R.id.userstatus);
            btn_call = itemView.findViewById(R.id.btn_call);
            date = itemView.findViewById(R.id.date);

        }
    }
}
