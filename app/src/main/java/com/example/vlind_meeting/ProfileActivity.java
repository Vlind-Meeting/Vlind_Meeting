package com.example.vlind_meeting;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private Button msg_btn;
    private Button match_btn;

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

    }

}