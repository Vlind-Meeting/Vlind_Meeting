package com.example.vlind_meeting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MsgReceivedTabFragment extends Fragment {
    private ViewGroup rootView;
    private RecyclerView mRecyclerView;
    private ArrayList<MsgReceivedRecyclerItem> mList;
    private MsgReceivedRecyclerAdapter mRecyclerViewAdapter;

    MediaPlayer mediaPlayer;
    String fileName;
    int n;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_msg_received, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        /* initiate adapter */
        mRecyclerViewAdapter = new MsgReceivedRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        /* adapt data */
        mList = new ArrayList<>();
        for(int i=1;i<=10;i++){
            if(i%2==0)
                mList.add(new MsgReceivedRecyclerItem(R.color.black,i+"번째 사람", "Press to Play", "", 0));
            else
                mList.add(new MsgReceivedRecyclerItem(R.color.tiffany,i+"번째 사람", "Press to Play", "", 0));
        }
        mRecyclerViewAdapter.setReceivedList(mList);

        mRecyclerViewAdapter.setOnItemClickListener(new MsgReceivedRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onSoundClick(View v, int position) {

                File file = new File(Environment.getExternalStorageDirectory(), "1.mp4");
                fileName = file.getAbsolutePath();  // 파일 위치 가져옴
                Toast.makeText(getActivity().getApplicationContext(), "파일 위치:"+fileName, Toast.LENGTH_SHORT).show();

                mList.get(position).setSoundState("Playing");

                try {
                    if(mediaPlayer != null){    // 사용하기 전에
                        mediaPlayer.release();  // 리소스 해제
                        mediaPlayer = null;
                    }
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(fileName); // 음악 파일 위치 지정
                    mediaPlayer.prepare();  // 미리 준비
                    mediaPlayer.start();    // 재생

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mList.get(position).setSoundState("Stopped");
                            mList.get(position).setSoundInfo("press to replay");
                            mRecyclerViewAdapter.notifyItemChanged(position);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mRecyclerViewAdapter.notifyItemChanged(position);
            }

            @Override
            public void onAcceptClick(View v, int position) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("매칭 신청 수락")
                        .setMessage("매칭 신청을 수락하시겠습니까?")
                        .setNegativeButton("수락", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //여기서 n값 데베로
                                n=(int)((Math.random()*11)+0);
                                mList.remove(position);
                                mRecyclerViewAdapter.notifyItemRemoved(position);
                                Intent intent = new Intent(requireActivity(), FinalMatchActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }

            @Override
            public void onDenyClick(View v, int position) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("매칭 신청 거절")
                        .setMessage("매칭 신청을 거절하시겠습니까?")
                        .setNegativeButton("거절", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mList.remove(position);
                                mRecyclerViewAdapter.notifyItemRemoved(position);
                            }
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
