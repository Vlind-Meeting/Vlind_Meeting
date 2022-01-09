package com.example.vlind_meeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MsgReceivedTabFragment extends Fragment {
    private ViewGroup rootView;
    private RecyclerView mRecyclerView;
    private ArrayList<MsgReceivedRecyclerItem> mList;
    private MsgReceivedRecyclerAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_msg_received, container, false);
//
//        firstInit();
//
//        mList = new ArrayList<>();
//        for(int i=1;i<=10;i++){
//            if(i%2==0)
//                mList.add(new MsgReceivedRecyclerItem(R.drawable.profile,i+"번째 사람",i+"번째 상태메시지"));
//            else
//                mList.add(new MsgReceivedRecyclerItem(R.drawable.profile,i+"번째 사람",i+"번째 상태메시지"));
//        }
////        for(int i=0;i<5;i++){
////            addItem("iconName", "Taek" + i, "taek2.tistory.com");
////        }
//
//        mRecyclerViewAdapter = new MsgReceivedRecyclerAdapter(mList);
//        mRecyclerView.setAdapter(mRecyclerViewAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void firstInit(){
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
    }

//    public void addItem(String imgName, String mainText, String subText){
//        MsgReceivedRecyclerItem item = new MsgReceivedRecyclerItem();
//
//        item.setImgName(imgName);
//        item.setMainText(mainText);
//        item.setSubText(subText);
//
//        mList.add(item);
//    }
}
