package com.example.vlind_meeting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import me.relex.circleindicator.CircleIndicator3;

public class MainAppActivity extends AppCompatActivity implements MatchingListener{

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 3;
    private CircleIndicator3 mIndicator;
    private CheckBox select_btn;
    private Button msg_btn, profile_btn;
    private String nickname1, nickname2, nickname3, filename1, filename2, filename3;

    private String user_voice, user_name, user_password, user_gender, user_number, user_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent0 = getIntent();
        nickname1 = intent0.getExtras().getString("nickname1");
        filename1 = intent0.getExtras().getString("filename1");
        nickname2 = intent0.getExtras().getString("nickname2");
        filename2 = intent0.getExtras().getString("filename2");
        nickname3 = intent0.getExtras().getString("nickname3");
        filename3 = intent0.getExtras().getString("filename3");

        //그리고 여기서 이성한테 하트를 보내는 창, 메시지 함 등으로 넘어가는 아이콘들을 구현해야함. 그리고 추가로 Activity들을 생성해야 한다.

        msg_btn = (Button) findViewById(R.id.msg_btn);
        profile_btn = (Button) findViewById(R.id.profile_btn);

        msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, MsgActivity.class);
                startActivity(intent);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        //여기서 데이터 베이스 연결 -> 1,2,3등 이성 매치(닉네임, 녹음파일)

        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new MainAppActivityAdapter(MainAppActivity.this, num_page, this);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page,0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        mPager.setCurrentItem(0); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }
        });

        select_btn = (CheckBox) findViewById(R.id.select_btn);
        if(select_btn.isChecked()){
            Toast.makeText(getApplicationContext(), String.valueOf(mPager.getCurrentItem()), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public matchClass getMatch1() {
        matchClass m1 = new matchClass(nickname1, filename1);
        return m1;
    }

    @Override
    public matchClass getMatch2() {
        matchClass m2 = new matchClass(nickname2, filename2);
        return m2;
    }

    @Override
    public matchClass getMatch3() {
        matchClass m3 = new matchClass(nickname3, filename3);
        return m3;
    }
}
