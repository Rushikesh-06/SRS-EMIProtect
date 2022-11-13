package com.example.gexemi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return userClassList.size();
    }

    public class LockuserHolder extends RecyclerView.ViewHolder {

        TextView lock_username,lock_custid,lock_phoneno;

        public LockuserHolder(@NonNull View itemView) {
            super(itemView);

            lock_username = itemView.findViewById(R.id.item_username);
            lock_custid = itemView.findViewById(R.id.item_custid);
            lock_phoneno = itemView.findViewById(R.id.item_phoneno);
        }
    }
}
