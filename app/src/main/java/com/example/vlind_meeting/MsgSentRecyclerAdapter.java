package com.example.vlind_meeting;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MsgSentRecyclerAdapter extends RecyclerView.Adapter<MsgSentRecyclerAdapter.ViewHolder> {

    private ArrayList<MsgSentRecyclerItem> mSentList;

    @NonNull
    @Override
    public MsgSentRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_sent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgSentRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mSentList.get(position));
    }

    public void setSentList(ArrayList<MsgSentRecyclerItem> list){
        this.mSentList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView nickname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.profile);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
        }

        void onBind(MsgSentRecyclerItem item){
            profile.setImageResource(item.getResourceId());
            nickname.setText(item.getNickname());
        }
    }
}