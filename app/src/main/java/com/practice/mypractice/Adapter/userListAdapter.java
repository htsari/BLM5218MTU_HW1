package com.practice.mypractice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.mypractice.Model.userModel;
import com.practice.mypractice.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class userListAdapter extends RecyclerView.Adapter<userListAdapter.ItemHolder> {
    List<userModel> userlList;
    Context context;
    @NonNull
    @Override
    public userListAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlistrow, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull userListAdapter.ItemHolder holder, int position) {
        userModel user = userlList.get(position);
        holder.fullName.setText(user.getName() + " " + user.getLastName());
        holder.pass.setText("Password: " + user.getPassword());
    }

    @Override
    public int getItemCount() {
        return userlList != null ? userlList.size() : 0 ;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView fullName, pass;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            pass = itemView.findViewById(R.id.password);
        }
    }

    public userListAdapter(List<userModel> userlList, Context context) {
        this.userlList = userlList;
        this.context = context;
    }
}
