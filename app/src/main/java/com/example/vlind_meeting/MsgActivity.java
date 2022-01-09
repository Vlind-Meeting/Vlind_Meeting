package com.example.vlind_meeting;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MsgActivity extends AppCompatActivity {

    private Button profile_btn, match_btn;

    ViewPager viewPager;

    private String user_voice, user_name, user_password, user_gender, user_number, user_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        viewPager = findViewById(R.id.viewpager);
        MsgTabAdapter adapter = new MsgTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        profile_btn = (Button) findViewById(R.id.profile_btn);
        match_btn = (Button) findViewById(R.id.match_btn);

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MsgActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        match_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MsgActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });



    }

}
