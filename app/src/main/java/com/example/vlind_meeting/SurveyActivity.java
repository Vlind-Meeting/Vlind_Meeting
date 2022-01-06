package com.example.vlind_meeting;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;

public class SurveyActivity extends AppCompatActivity implements FragmentListener {

    private int q1, q2, q3, q4, q5, q6, q7, q8, q9;
    private String q10, voice;
    private String user_voice, user_name, user_password, user_gender, user_number;

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
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_main);
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
        user_voice = intent.getExtras().getString("user_voice");
        user_gender = intent.getExtras().getString("user_gender");
        user_password = intent.getExtras().getString("user_password");
        user_name = intent.getExtras().getString("user_name");
        user_number = intent.getExtras().getString("user_number");

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.frame_layout, frameQ1).commit();
        transaction.replace(R.id.frame_layout, frameRecord).commit();

    }

    public void setRecord(String s){
        voice=s;
    }

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
        //설문을 제출하면 본인 프로필이 있는 MainAppActivity로 이동하여 프로필을 보여줘야함
        Intent intent = new Intent(SurveyActivity.this, MainAppActivity.class);
        startActivity(intent);
    }

    private double RMSE_algorithm(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, String x10){
        double result = Math.sqrt((Math.pow(x1-q1,2)*0.99 + Math.pow(x2-q2,2)*0.98 + Math.pow(x3-q3, 2)*0.97
                + Math.pow(x4-q4,2)*1.01 + Math.pow(x5-q5,2)*1.02 + Math.pow(x6-q6, 2)*1.03
                + Math.pow(x7-q7,2)*1.04 + Math.pow(x8-q8,2)*0.96 + Math.pow(x9-q9, 2))*0.95/9);
        return result;
    }


}

