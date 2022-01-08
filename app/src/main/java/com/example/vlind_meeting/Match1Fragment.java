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

import org.w3c.dom.Text;

import java.io.File;

public class Match1Fragment extends Fragment {
    private TextView sound_state;
    private TextView sound_info;
    private TextView nickname;
    private Button sound_btn;

    int n=0;
    MediaRecorder recorder;
    String fileName;
    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_match1, container, false);


        sound_state = (TextView) rootView.findViewById(R.id.sound_state);
        sound_info = (TextView) rootView.findViewById(R.id.sound_info);
        nickname = (TextView) rootView.findViewById(R.id.user_nickname);
        sound_btn = (Button) rootView.findViewById(R.id.sound_btn);

        File file = new File(Environment.getExternalStorageDirectory(), "recorded.mp4");
        fileName = file.getAbsolutePath();  // 파일 위치 가져옴
        Toast.makeText(getActivity().getApplicationContext(), "파일 위치:"+fileName, Toast.LENGTH_SHORT).show();

        sound_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n%2==0){
                    sound_state.setText("Playing");
                    sound_info.setText("press to stop");

                }

                else{
                    sound_state.setText("Stopped");
                    sound_info.setText("press to listen");

                }
                n++;
            }
        });

        return rootView;
    }


}
