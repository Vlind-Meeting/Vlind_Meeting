package com.example.vlind_meeting;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MsgActivity extends AppCompatActivity {

    private Button profile_btn, match_btn;

    ResponseSurvey responseSurvey;
    private String filename1, filename2, filename3, nickname1, nickname2, nickname3, number1, number2, number3;


    ViewPager viewPager;


    private String user_voice, user_name, user_password, user_gender, user_number, user_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        Intent intent0 = getIntent();
        user_number = intent0.getExtras().getString("user_number");
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
                intent.putExtra("user_number", user_number);
                startActivity(intent);
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
                            Intent intent = new Intent(MsgActivity.this, MainAppActivity.class);
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



    }

}
