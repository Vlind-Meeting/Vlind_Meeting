package com.example.vlind_meeting;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    private Button msg_btn;
    private Button match_btn;
    private Button sound_btn;
    private Button edit_nickname;
    private Button edit_pwd;
    private EditText nickname, pwd;
    private TextView sound_state, sound_info;

    int n=0;
    MediaRecorder recorder;
    String fileName;
    MediaPlayer mediaPlayer;

    private String user_voice, user_name, user_password, user_gender, user_number, user_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        msg_btn = (Button) findViewById(R.id.msg_btn);
        match_btn = (Button) findViewById(R.id.match_btn);

        msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MsgActivity.class);
                startActivity(intent);
            }
        });

        match_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });

        sound_btn = (Button) findViewById(R.id.sound_btn);
        edit_nickname = (Button) findViewById(R.id.edit_nickname);
        edit_pwd = (Button) findViewById(R.id.edit_pwd);
        nickname = (EditText) findViewById(R.id.user_nickname);
        pwd = (EditText) findViewById(R.id.user_pwd);
        sound_state = (TextView) findViewById(R.id.sound_state);
        sound_info = (TextView) findViewById(R.id.sound_info);

        //여기서 filename 수정
        File file = new File(Environment.getExternalStorageDirectory(), "01040530728.mp4");
        fileName = file.getAbsolutePath();  // 파일 위치 가져옴
        Toast.makeText(this.getApplicationContext(), "파일 위치:"+fileName, Toast.LENGTH_SHORT).show();

        sound_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n%2==0){
                    //녹음 재생
                    sound_state.setText("Playing");
                    sound_info.setText("press to rerecord");
                    try {
                        if(mediaPlayer != null){    // 사용하기 전에
                            mediaPlayer.release();  // 리소스 해제
                            mediaPlayer = null;
                        }
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(fileName); // 음악 파일 위치 지정
                        mediaPlayer.prepare();  // 미리 준비
                        mediaPlayer.start();    // 재생
//                        Toast.makeText(getActivity().getApplicationContext(), "재생시작", Toast.LENGTH_SHORT).show();

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
                else if(n%3==1){
                    sound_state.setText("Recording");
                    sound_info.setText("press to save");
                    if (recorder == null) {
                        recorder = new MediaRecorder(); // 미디어리코더 객체 생성
                    }
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 오디오 입력 지정(마이크)
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);    // 출력 형식 지정
                    //마이크로 들어오는 음성데이터는 용량이 크기 때문에 압축이 필요
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);   // 인코딩
                    recorder.setOutputFile(fileName);  // 음성 데이터를 저장할 파일 지정
                    try {
                        recorder.prepare();
                        recorder.start();
                        Toast.makeText(getApplicationContext(), "녹음시작", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {e.printStackTrace();}
                }
                else{
                    sound_state.setText("Saved");
                    sound_info.setText("press to play");
                    if (recorder != null) {
                        recorder.stop();
                        recorder.release();
                        recorder = null;
                    }
                    Toast.makeText(getApplicationContext(), "녹음중지", Toast.LENGTH_SHORT).show();
                }
                n++;
            }
        });

        // 닉네임 수정
        edit_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 비밀번호 수정
        edit_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}