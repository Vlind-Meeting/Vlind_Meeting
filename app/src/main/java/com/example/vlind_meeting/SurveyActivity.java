package com.example.vlind_meeting;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyActivity extends AppCompatActivity implements FragmentListener {

    String[] permission_list = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private final String TAG = "SurveyActivityLog";
    private int q1, q2, q3, q4, q5, q6, q7, q8, q9;
    private String q10, filename;
    private String user_name, user_password, user_gender, user_number, user_nickname;
    private String filename1, filename2, filename3, nickname1, nickname2, nickname3, number1, number2, number3;

    private ResponseSurvey responseSurvey;

    RecordFragment frameRecord;
    Q1Fragment frameQ1;
    Q2Fragment frameQ2;
    Q3Fragment frameQ3;
    Q4Fragment frameQ4;
    Q5Fragment frameQ5;
    Q6Fragment frameQ6;
    Q7Fragment frameQ7;
    Q8Fragment frameQ8;
    Q9Fragment frameQ9;
    Q10Fragment frameQ10;
    public FragmentManager fragmentManager;
    public FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_main);
        checkPermission();

        frameRecord = new RecordFragment();
        frameQ1 = new Q1Fragment();
        frameQ2 = new Q2Fragment();
        frameQ3 = new Q3Fragment();
        frameQ4 = new Q4Fragment();
        frameQ5 = new Q5Fragment();
        frameQ6 = new Q6Fragment();
        frameQ7 = new Q7Fragment();
        frameQ8 = new Q8Fragment();
        frameQ9 = new Q9Fragment();
        frameQ10 = new Q10Fragment();

        Intent intent = getIntent();
        user_gender = intent.getExtras().getString("user_gender");
        user_password = intent.getExtras().getString("user_password");
        user_name = intent.getExtras().getString("user_name");
        user_number = intent.getExtras().getString("user_number");
        user_nickname = intent.getExtras().getString("user_nickname");

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.frame_layout, frameQ1).commit();
        transaction.replace(R.id.frame_layout, frameRecord).commit();

        responseSurvey = RetrofitClientInstance.getClient().create(ResponseSurvey.class);
    }

    @Override
    public String getUserNumber() {
        return user_number;
    }

    @Override
    public void setRecord(String s){
        filename = s;
    }

    @Override
    public void nextRecord(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ1).commit();
    }

    @Override
    public void setQ1(int i) {
        q1 = i;
    }

    @Override
    public void nextQ1(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ2).commit();
    }

    @Override
    public void setQ2(int i) {
        q2 = i;
    }

    @Override
    public void nextQ2() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ3).commit();
    }

    @Override
    public void setQ3(int i) {
        q3 = i;
    }

    @Override
    public void nextQ3() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ4).commit();
    }

    @Override
    public void setQ4(int i) {
        q4 = i;
    }

    @Override
    public void nextQ4() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ5).commit();
    }

    @Override
    public void setQ5(int i) {
        q5 = i;
    }

    @Override
    public void nextQ5() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ6).commit();
    }

    @Override
    public void setQ6(int i) {
        q6 = i;
    }

    @Override
    public void nextQ6() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ7).commit();
    }

    @Override
    public void setQ7(int i) {
        q7 = i;
    }

    @Override
    public void nextQ7() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ8).commit();
    }

    @Override
    public void setQ8(int i) {
        q8 = i;
    }

    @Override
    public void nextQ8() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ9).commit();
    }

    @Override
    public void setQ9(int i) {
        q9 = i;
    }

    @Override
    public void nextQ9() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ10).commit();
    }

    @Override
    public void set10(String s) {
        q10 = s;
    }

    @Override
    public void submit() {

        SurveyRequest surveyRequest = new SurveyRequest(user_name, user_number, user_nickname, user_password, user_gender,
                filename, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10);
        responseSurvey.postSurvey(surveyRequest).enqueue(new Callback<SurveyResponse>(){
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

                    ProfileClass profileClass = ProfileClass.getInstance();
                    profileClass.setUser_number(user_number);
                    profileClass.setUser_password(user_password);
                    profileClass.setUser_nickname(user_nickname);
                    profileClass.setUser_name(user_name);
                    profileClass.setUser_filename(filename);

                    Intent intent = new Intent(SurveyActivity.this, MainAppActivity.class);
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
                    Log.v(TAG, "result = " + result);
                } else {
                    Log.v(TAG, "error = " + String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                Log.v(TAG, "Fail");
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkPermission(){
        //현재 안드로이드 버전이 6.0미만이면 메서드를 종료한다.
        //안드로이드6.0 (마시멜로) 이후 버전부터 유저 권한설정 필요
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;

        for(String permission : permission_list){
            //권한 허용 여부를 확인한다.
            int chk = checkCallingOrSelfPermission(permission);

            if(chk == PackageManager.PERMISSION_DENIED){
                //권한 허용을여부를 확인하는 창을 띄운다
                requestPermissions(permission_list,0);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0)
        {
            for(int i=0; i<grantResults.length; i++)
            {
                //허용됬다면
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                }
                else {
                    //권한을 하나라도 허용하지 않는다면 앱 종료
                    Toast.makeText(getApplicationContext(),"앱권한설정하세요",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }


}

