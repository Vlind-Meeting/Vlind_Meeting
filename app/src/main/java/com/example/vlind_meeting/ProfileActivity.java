package com.example.vlind_meeting;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private Button msg_btn;
    private Button match_btn;
    private Button sound_btn;
    private Button edit_nickname;
    private Button edit_pwd;
    private EditText nickname, pwd;
    private TextView sound_state, sound_info, text_name, text_phone, text_nickname, text_password;
    private List<SendResponse> result;
    private ArrayList<SendResponse> result_intent = new ArrayList<SendResponse>();;
    private List<ReceiveResponse> result0;
    private ArrayList<ReceiveResponse> result0_intent = new ArrayList<ReceiveResponse>();



    ConstraintLayout layout, layout_2;

    ResponseSurvey responseSurvey;
    ResponseProfile responseProfile;
    ResponseMessage responseMessage;

    private String filename1, filename2, filename3, nickname1, nickname2, nickname3, number1, number2, number3;
    int n=0;
    MediaRecorder recorder;
    String fileName;
    MediaPlayer mediaPlayer;

    private String user_number, user_password, user_nickname, user_name, user_filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent0 = getIntent();
        user_number = intent0.getExtras().getString("user_number");
//        System.out.println(user_number);

        ProfileClass profileClass = ProfileClass.getInstance();
        user_password = profileClass.getUser_password();
        user_nickname = profileClass.getUser_nickname();
        user_name = profileClass.getUser_name();
        user_filename = profileClass.getUser_filename();


        msg_btn = (Button) findViewById(R.id.msg_btn);
        match_btn = (Button) findViewById(R.id.match_btn);

        msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responseMessage = RetrofitClientInstance.getClient().create(ResponseMessage.class);

                responseMessage.sendFunc(user_number).enqueue(new Callback<List<SendResponse>>(){
                    @Override
                    public void onResponse(Call<List<SendResponse>> call, Response<List<SendResponse>> response) {
                        if (response.isSuccessful()) {
                            result_intent.clear();
                            result = response.body();
                            result_intent.addAll(result);
//                            System.out.println('!');
//                            for(int i = 0; i < result.size(); i++)
//                                System.out.println(result.get(i).getNickname());
//                            System.out.println('!');
                            responseMessage.receiveFunc(user_number).enqueue(new Callback<List<ReceiveResponse>>(){
                                @Override
                                public void onResponse(Call<List<ReceiveResponse>> call, Response<List<ReceiveResponse>> response0) {
                                    if (response0.isSuccessful()) {
                                        result0_intent.clear();
                                        result0 = response0.body();
                                        result0_intent.addAll(result0);

                                        Intent intent = new Intent(ProfileActivity.this, MsgActivity.class);
                                        intent.putExtra("result_intent", result_intent);
                                        intent.putExtra("result0_intent", result0_intent);
                                        intent.putExtra("user_number", user_number);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "profile activity error occurred", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<ReceiveResponse>> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "profile activity error occurred", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SendResponse>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        match_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                responseSurvey = RetrofitClientInstance.getClient().create(ResponseSurvey.class);

                responseSurvey.getSurvey(user_number).enqueue(new Callback<SurveyResponse>(){
                    @Override
                    public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {
                        if (response.isSuccessful()) {
                            SurveyResponse result = response.body();
                            nickname1 = result.getNickname1();
                            filename1 = result.getFilename1();
                            number1 = result.getNumber1();
                            nickname2 = result.getNickname2();
                            filename2 = result.getFilename2();
                            number2 = result.getNumber2();
                            nickname3 = result.getNickname3();
                            filename3 = result.getFilename3();
                            number3 = result.getNumber3();
                            int heart_num = result.getHeartNum();
                            Intent intent = new Intent(ProfileActivity.this, MainAppActivity.class);
                            intent.putExtra("nickname1", nickname1);
                            intent.putExtra("filename1", filename1);
                            intent.putExtra("number1", number1);
                            intent.putExtra("nickname2", nickname2);
                            intent.putExtra("filename2", filename2);
                            intent.putExtra("number2", number2);
                            intent.putExtra("nickname3", nickname3);
                            intent.putExtra("filename3", filename3);
                            intent.putExtra("number3", number3);
                            intent.putExtra("user_number", user_number);
                            intent.putExtra("heart_num", heart_num);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<SurveyResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        sound_btn = (Button) findViewById(R.id.sound_btn);
        edit_nickname = (Button) findViewById(R.id.edit_nickname);
        edit_pwd = (Button) findViewById(R.id.edit_pwd);
        nickname = (EditText) findViewById(R.id.user_nickname);
        pwd = (EditText) findViewById(R.id.user_pwd);
        sound_state = (TextView) findViewById(R.id.sound_state);
        sound_info = (TextView) findViewById(R.id.sound_info);
        text_name = (TextView) findViewById(R.id.user_name);
        text_phone = (TextView) findViewById(R.id.user_phone);
        text_nickname = (TextView) findViewById(R.id.user_nickname);
        text_password = (TextView) findViewById(R.id.user_pwd);

        text_name.setText(user_name);
        text_nickname.setText(user_nickname);
        text_phone.setText(user_number);
        text_password.setText(user_password);

        //여기서 filename 수정
//        File file = new File(Environment.getExternalStorageDirectory(), "01040530728.mp4");
//        fileName = file.getAbsolutePath();  // 파일 위치 가져옴
//        Toast.makeText(this.getApplicationContext(), "파일 위치:"+fileName, Toast.LENGTH_SHORT).show();

        sound_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n%3==0){
                    //녹음 재생
                    sound_state.setText("Playing");
                    sound_info.setText("press to record");
                    try {
                        if(mediaPlayer != null){    // 사용하기 전에
                            mediaPlayer.release();  // 리소스 해제
                            mediaPlayer = null;
                        }
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(user_filename); // 음악 파일 위치 지정
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
                    recorder.setOutputFile(user_filename);  // 음성 데이터를 저장할 파일 지정
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
                hideKeyboard();
                text_nickname.clearFocus();
                String tmp = text_nickname.getText().toString();
                ChangeNickname changeNickname = new ChangeNickname(user_number, tmp);
                responseProfile = RetrofitClientInstance.getClient().create(ResponseProfile.class);
                responseProfile.changeNickname(changeNickname).enqueue(new Callback<ResponseBody>(){
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String result = response.body().toString();
                            Toast.makeText(getApplicationContext(), "닉네임이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        // 비밀번호 수정
        edit_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                text_password.clearFocus();
                String tmp = text_password.getText().toString();
                ChangePassword changePassword = new ChangePassword(user_number, tmp);
                responseProfile = RetrofitClientInstance.getClient().create(ResponseProfile.class);
                responseProfile.changePassword(changePassword).enqueue(new Callback<ResponseBody>(){
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String result = response.body().toString();
                            Toast.makeText(getApplicationContext(), "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        layout = (ConstraintLayout) findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(nickname.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(pwd.getWindowToken(), 0);
            }
        });

        layout_2 = (ConstraintLayout) findViewById(R.id.layout_2);
        layout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(nickname.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(pwd.getWindowToken(), 0);
            }
        });

    }
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}