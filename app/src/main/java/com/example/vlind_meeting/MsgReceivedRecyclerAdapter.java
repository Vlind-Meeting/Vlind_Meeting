package com.example.vlind_meeting;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MsgReceivedRecyclerAdapter extends RecyclerView.Adapter<MsgReceivedRecyclerAdapter.ViewHolder> {

    private ArrayList<MsgReceivedRecyclerItem> mFriendList;

    @NonNull
    @Override
    public MsgReceivedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_received, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgReceivedRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mFriendList.get(position));
    }

    public void setFriendList(ArrayList<MsgReceivedRecyclerItem> list){
        this.mFriendList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.imgView_item);
            name = (TextView) itemView.findViewById(R.id.txt_main);
            message = (TextView) itemView.findViewById(R.id.txt_sub);
        }

        void onBind(MsgReceivedRecyclerItem item){
            profile.setImageResource(item.getResourceId());
            name.setText(item.getName());
            message.setText(item.getMessage());
        }
    }
}