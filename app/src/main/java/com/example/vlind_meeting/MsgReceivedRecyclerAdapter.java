package com.example.vlind_meeting;


import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MsgReceivedRecyclerAdapter extends RecyclerView.Adapter<MsgReceivedRecyclerAdapter.ViewHolder> {

    private ArrayList<MsgReceivedRecyclerItem> mReceivedList;
    private Resources res;

    @NonNull
    @Override
    public MsgReceivedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_received, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgReceivedRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mReceivedList.get(position));
    }

    public void setReceivedList(ArrayList<MsgReceivedRecyclerItem> list){
        this.mReceivedList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mReceivedList.size();
    }

    interface OnItemClickListener{
        void onSoundClick(View v, int position); //소리
        void onAcceptClick(View v, int position); //수락
        void onDenyClick(View v, int position);//거절
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView sound_btn;
        Button accept_btn, deny_btn;
        TextView nickname;
        TextView soundState, soundInfo;
        int n;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            res = itemView.getResources();

            sound_btn = (ImageView) itemView.findViewById(R.id.sound_btn);
            accept_btn = (Button) itemView.findViewById(R.id.accept_btn);
            deny_btn= (Button) itemView.findViewById(R.id.deny_btn);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
            soundState = (TextView) itemView.findViewById(R.id.sound_state);
            soundInfo = (TextView) itemView.findViewById(R.id.sound_info);

            sound_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        if(mListener!=null)
                            mListener.onSoundClick(view, position);
                    }
                }
            });

            accept_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        if(mListener!=null)
                            mListener.onAcceptClick(view, position);
                    }
                }
            });

            deny_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        if(mListener!=null)
                            mListener.onDenyClick(view, position);
                    }
                }
            });
        }

        void onBind(MsgReceivedRecyclerItem item){
            sound_btn.setColorFilter(res.getColor(item.getResourceId()));
            nickname.setText(item.getNickname());
            soundState.setText(item.getSoundState());
            soundInfo.setText(item.getSoundInfo());
            n = item.getN();
        }
    }
}
