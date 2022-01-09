package com.example.vlind_meeting;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MsgReceivedRecyclerAdapter extends RecyclerView.Adapter<MsgReceivedRecyclerAdapter.ViewHolder> {
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_item;
        TextView txt_main;
        TextView txt_sub;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView_item = (ImageView) itemView.findViewById(R.id.imgView_item);
            txt_main = (TextView) itemView.findViewById(R.id.txt_main);
            txt_sub = (TextView) itemView.findViewById(R.id.txt_sub);
        }
    }

    private ArrayList<MsgReceivedRecyclerItem> mList = null;

    public MsgReceivedRecyclerAdapter(ArrayList<MsgReceivedRecyclerItem> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.fragment_msg_received, parent, false);
        MsgReceivedRecyclerAdapter.ViewHolder vh = new MsgReceivedRecyclerAdapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull MsgReceivedRecyclerAdapter.ViewHolder holder, int position) {
        MsgReceivedRecyclerItem item = mList.get(position);

        holder.imgView_item.setImageResource(item.getImgName());   // 사진 없어서 기본 파일로 이미지 띄움
        holder.txt_main.setText(item.getMainText());
        holder.txt_sub.setText(item.getSubText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}