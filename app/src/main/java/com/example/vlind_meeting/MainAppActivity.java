package com.example.vlind_meeting;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //본인의 프로필을 보여줘야 함
        //그리고 여기서 이성한테 하트를 보내는 창, 메시지 함 등으로 넘어가는 아이콘들을 구현해야함. 그리고 추가로 Activity들을 생성해야 한다.
    }


}
