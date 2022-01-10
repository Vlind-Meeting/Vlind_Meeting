package com.example.vlind_meeting;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;

public class Match2Fragment extends Fragment {
    private TextView sound_state;
    private TextView sound_info;
    private TextView nickname;
    private Button sound_btn;
    private MatchingListener matchingListener;
    private String match2_nickname, match2_filename;
    private matchClass match2;
    private MediaPlayer mediaPlayer;

    public Match2Fragment(MatchingListener listener) {
        this.matchingListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_match2, container, false);

        match2 = matchingListener.getMatch2();
        match2_nickname = match2.getNickname();
        match2_filename = match2.getFilename();

        sound_state = (TextView) rootView.findViewById(R.id.sound_state);
        sound_info = (TextView) rootView.findViewById(R.id.sound_info);
        nickname = (TextView) rootView.findViewById(R.id.user_nickname);
        sound_btn = (Button) rootView.findViewById(R.id.sound_btn);

        nickname.setText(match2_nickname);

        sound_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound_state.setText("Playing");
                sound_info.setText("press to play");
                try {
                    if(mediaPlayer != null){    // 사용하기 전에
                        mediaPlayer.release();  // 리소스 해제
                        mediaPlayer = null;
                    }
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(match2_filename); // 음악 파일 위치 지정
                    mediaPlayer.prepare();  // 미리 준비
                    mediaPlayer.start();    // 재생

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            sound_state.setText("Stopped");
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }


}
