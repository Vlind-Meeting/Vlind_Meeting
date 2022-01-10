package com.example.vlind_meeting;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

public class Match1Fragment extends Fragment {
    private TextView sound_state;
    private TextView sound_info;
    private TextView nickname;
    private Button sound_btn;
    private MatchingListener matchingListener;
    private String match1_nickname, match1_filename;
    private matchClass match1;
    private MediaPlayer mediaPlayer;

    public Match1Fragment(MatchingListener listener) {
        this.matchingListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_match1, container, false);

        match1 = matchingListener.getMatch1();
        match1_nickname = match1.getNickname();
        match1_filename = match1.getFilename();

        sound_state = (TextView) rootView.findViewById(R.id.sound_state);
        sound_info = (TextView) rootView.findViewById(R.id.sound_info);
        nickname = (TextView) rootView.findViewById(R.id.user_nickname);
        sound_btn = (Button) rootView.findViewById(R.id.sound_btn);

        nickname.setText(match1_nickname);

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
                    mediaPlayer.setDataSource(match1_filename); // 음악 파일 위치 지정
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
