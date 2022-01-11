package com.example.vlind_meeting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.service.autofill.FieldClassification;
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
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MsgReceivedTabFragment extends Fragment {
    private ViewGroup rootView;
    private RecyclerView mRecyclerView;
    private ArrayList<MsgReceivedRecyclerItem> mList;
    private MsgReceivedRecyclerAdapter mRecyclerViewAdapter;

    MediaPlayer mediaPlayer;
    String fileName;
    private MsgListener msgListener;
    private ArrayList<String> nicknames;
    private ArrayList<String> numbers;
    private ArrayList<String> filenames;
    ResponseMessage responseMessage;
    private String receive_number;

    int red = 0;
    int blue =0;
    int green = 0;

    public MsgReceivedTabFragment(MsgListener listener, String receive_number) {
        this.msgListener = listener;
        this.receive_number = receive_number;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_msg_received, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        nicknames = msgListener.getReceiveNickNames();
        numbers = msgListener.getReceiveNumbers();
        filenames = msgListener.getReceiveFilenames();

        /* initiate adapter */
        mRecyclerViewAdapter = new MsgReceivedRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        //recycler view에서 나타낼 데이터들을 db에서 가져와야함. nickname, filename만 필요함
        //color를 random지정해야함.
        /* adapt data */
        mList = new ArrayList<>();

//        for(int i=1;i<=10;i++){
//            if(i%2==0)
//                mList.add(new MsgReceivedRecyclerItem(R.color.black,i+"번째 사람", "Press to Play", ""));
//            else
//                mList.add(new MsgReceivedRecyclerItem(R.color.tiffany,i+"번째 사람", "Press to Play", ""));
//        }

        for(int i = 0; i < nicknames.size(); i++){
            red = (int)(Math.random() * 255);
            blue = (int)(Math.random() * 255);
            green = (int)(Math.random() * 255);

            mList.add(new MsgReceivedRecyclerItem(Color.rgb(red, green, blue), nicknames.get(i), "Press to Play", ""));
        }

        mRecyclerViewAdapter.setReceivedList(mList);

        mRecyclerViewAdapter.setOnItemClickListener(new MsgReceivedRecyclerAdapter.OnItemClickListener() {

            //파일 위치만 데이터베이스에서 가져오면 됨.
            @Override
            public void onSoundClick(View v, int position) {
                System.out.println('!');
                System.out.println(position);
                System.out.println(nicknames.get(position));
                System.out.println(filenames.get(position));
                System.out.println(numbers.get(position));
//                File file = new File(Environment.getExternalStorageDirectory(), "01.mp4");
//                fileName = file.getAbsolutePath();  // 파일 위치 가져옴
                fileName = filenames.get(position);
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

            //수락해도 list에서 삭제하도록 현재 구현되어 있음. 
            //그리고 마지막 매칭 페이지로 이동
            @Override
            public void onAcceptClick(View v, int position) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("매칭 신청 수락")
                        .setMessage("매칭 신청을 수락하시겠습니까?")
                        .setNegativeButton("수락", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String send_number = numbers.get(position);
                                responseMessage = RetrofitClientInstance.getClient().create(ResponseMessage.class);
                                MatchRequest matchRequest = new MatchRequest(send_number, receive_number);
                                responseMessage.successFunc(matchRequest).enqueue(new Callback<MatchResponse>(){
                                    @Override
                                    public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                                        MatchResponse resp = response.body();
                                        String n = resp.getN();
                                        String send_number = resp.getSend_number();
                                        String receive_number = resp.getReceive_number();
                                        mList.remove(position);
                                        mRecyclerViewAdapter.notifyItemRemoved(position);
                                        Intent intent = new Intent(requireActivity(), FinalMatchActivity.class);
                                        intent.putExtra("n", n);
                                        intent.putExtra("send_number", send_number);
                                        intent.putExtra("receive_number", receive_number);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<MatchResponse> call, Throwable t) {

                                    }
                                });

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

            //현재는 거절을 하면 recycler view item list에서 삭제하도록 만들어놨다.
            //이를 db에서 삭제로 바꾸면 됨
            @Override
            public void onDenyClick(View v, int position) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("매칭 신청 거절")
                        .setMessage("매칭 신청을 거절하시겠습니까?")
                        .setNegativeButton("거절", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String send_number = numbers.get(position);
                                responseMessage = RetrofitClientInstance.getClient().create(ResponseMessage.class);
                                MatchRequest matchRequest = new MatchRequest(send_number, receive_number);
                                responseMessage.failFunc(matchRequest).enqueue(new Callback<ResponseBody>(){
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        String result = response.body().toString();
                                        System.out.println(result);
                                        mList.remove(position);
                                        mRecyclerViewAdapter.notifyItemRemoved(position);
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });

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
