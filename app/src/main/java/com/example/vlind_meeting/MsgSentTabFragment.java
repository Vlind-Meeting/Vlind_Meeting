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

public class MsgSentTabFragment extends Fragment {

    private ViewGroup rootView;
    private RecyclerView mRecyclerView;
    private ArrayList<MsgSentRecyclerItem> mList;
    private MsgSentRecyclerAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_msg_sent, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        /* initiate adapter */
        mRecyclerViewAdapter = new MsgSentRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        /* adapt data */
        mList = new ArrayList<>();
        for(int i=1;i<=10;i++){
            if(i%2==0)
                mList.add(new MsgSentRecyclerItem(R.color.purple_200,"sumin"));
            else
                mList.add(new MsgSentRecyclerItem(android.R.color.holo_red_dark,"핸스"));
        }
        mRecyclerViewAdapter.setSentList(mList);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
