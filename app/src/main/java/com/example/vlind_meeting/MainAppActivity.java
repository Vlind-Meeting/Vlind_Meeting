package com.example.vlind_meeting;

import static java.lang.Integer.valueOf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAppActivity extends AppCompatActivity implements MatchingListener{
    private final String TAG = "MainAppActivityLog";
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 3;
    private CircleIndicator3 mIndicator;
    private CheckBox select_btn;
    private Button msg_btn, profile_btn;
    private TextView heart_remain;
    private String select_page;
    public String nickname1, nickname2, nickname3, filename1, filename2, filename3, number1, number2, number3, receive_number;
    public int heart_num;

    private List<SendResponse> result;
    private ArrayList<SendResponse> result_intent = new ArrayList<SendResponse>();;
    private List<ReceiveResponse> result0;
    private ArrayList<ReceiveResponse> result0_intent = new ArrayList<ReceiveResponse>();

    HeartRequest heartRequest;
    ResponseSurvey responseSurvey;
    ResponseMessage responseMessage;
    private String user_voice, user_name, user_password, user_gender, user_number, user_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        select_btn = (CheckBox) findViewById(R.id.select_btn);
        heart_remain = (TextView) findViewById(R.id.heart_remain);

        Intent intent0 = getIntent();
        nickname1 = intent0.getExtras().getString("nickname1");
        filename1 = intent0.getExtras().getString("filename1");
        number1= intent0.getExtras().getString("number1");
        nickname2 = intent0.getExtras().getString("nickname2");
        filename2 = intent0.getExtras().getString("filename2");
        number2= intent0.getExtras().getString("number2");
        nickname3 = intent0.getExtras().getString("nickname3");
        filename3 = intent0.getExtras().getString("filename3");
        number3 = intent0.getExtras().getString("number3");
        user_number = intent0.getExtras().getString("user_number");
        heart_num = intent0.getExtras().getInt("heart_num");
//        System.out.println(user_number);

        System.out.println(heart_num);
        System.out.println(number1);
        if(heart_num == 0){
            heart_remain.setText("0");
            select_btn.setEnabled(false);
        }

        responseSurvey = RetrofitClientInstance.getClient().create(ResponseSurvey.class);

        //그리고 여기서 이성한테 하트를 보내는 창, 메시지 함 등으로 넘어가는 아이콘들을 구현해야함. 그리고 추가로 Activity들을 생성해야 한다.

        msg_btn = (Button) findViewById(R.id.msg_btn);
        profile_btn = (Button) findViewById(R.id.profile_btn);

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

                                        Intent intent = new Intent(MainAppActivity.this, MsgActivity.class);
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

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, ProfileActivity.class);
                intent.putExtra("user_number", user_number);
                startActivity(intent);
            }
        });




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

        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tmp = valueOf(mPager.getCurrentItem()).intValue();
                if(tmp == 0)
                    receive_number = number1;
                else if(tmp == 1)
                    receive_number = number2;
                else
                    receive_number = number3;
                System.out.println(tmp);
                System.out.println(user_number);
                heartRequest = new HeartRequest(user_number, receive_number, 0);
                System.out.println(heartRequest);
                new AlertDialog.Builder(MainAppActivity.this)
                        .setTitle("매칭 신청은 1일 1회로 제한됩니다.")
                        .setMessage("그래도 매칭을 신청하시겠습니까?")
                        .setNegativeButton("동의", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                responseSurvey.insertHeart(heartRequest).enqueue(new Callback<SurveyResponse>() {
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
                                            Intent intent = new Intent(MainAppActivity.this, MainAppActivity.class);
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
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                select_btn.setChecked(false);
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });
//        if(select_btn.isChecked()){
//            select_page = String.valueOf(mPager.getCurrentItem());
//            Toast.makeText(getApplicationContext(), String.valueOf(mPager.getCurrentItem()), Toast.LENGTH_SHORT).show();
//
//        }

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
