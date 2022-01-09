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

public class Match3Fragment extends Fragment {
    private TextView sound_state;
    private TextView sound_info;
    private TextView nickname;
    private Button sound_btn;
    private MatchingListener matchingListener;
    private String match3_nickname, match3_filename;
    private matchClass match3;
    private MediaPlayer mediaPlayer;

    public Match3Fragment(MatchingListener listener) {
        this.matchingListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_match3, container, false);

        match3 = matchingListener.getMatch3();
        match3_nickname = match3.getNickname();
        match3_filename = match3.getFilename();

        sound_state = (TextView) rootView.findViewById(R.id.sound_state);
        sound_info = (TextView) rootView.findViewById(R.id.sound_info);
        nickname = (TextView) rootView.findViewById(R.id.user_nickname);
        sound_btn = (Button) rootView.findViewById(R.id.sound_btn);

        nickname.setText(match3_nickname);

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
                    mediaPlayer.setDataSource(match3_filename); // 음악 파일 위치 지정
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
