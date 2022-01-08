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
        responseSurvey.postSurvey(surveyRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        if(result.equals("succeed")){
                            Intent intent = new Intent(SurveyActivity.this, MainAppActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "error occured", Toast.LENGTH_SHORT).show();
                        }
                        Log.v(TAG, "result = " + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.v(TAG, "error = " + String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v(TAG, "Fail");
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double RMSE_algorithm(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, String x10){
        double result = Math.sqrt((Math.pow(x1-q1,2)*0.99 + Math.pow(x2-q2,2)*0.98 + Math.pow(x3-q3, 2)*0.97
                + Math.pow(x4-q4,2)*1.01 + Math.pow(x5-q5,2)*1.02 + Math.pow(x6-q6, 2)*1.03
                + Math.pow(x7-q7,2)*1.04 + Math.pow(x8-q8,2)*0.96 + Math.pow(x9-q9, 2))*0.95/9);
        return result;
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

