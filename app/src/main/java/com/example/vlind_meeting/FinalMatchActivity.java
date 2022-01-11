package com.example.vlind_meeting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class FinalMatchActivity extends AppCompatActivity {
    private GifImageView gif_img;
    private GifDrawable gif;
    private ImageView place_map;
    private TextView place_info;
    private String n0, send_number, receive_number;
    private int n;
    List<Integer> placeNum = new ArrayList<Integer>();
    List<String> placeName = new ArrayList<String>();
    List<String> placeURL = new ArrayList<String>();
    TextView text_nickname;
    TextView text_match_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_match);

        Intent intent = getIntent();
        n0 = intent.getExtras().getString("n");
        n = Integer.parseInt(n0);
        send_number = intent.getExtras().getString("send_number");
        receive_number = intent.getExtras().getString("receive_number");

        text_nickname = (TextView) findViewById(R.id.user_nickname);
        text_match_nickname = (TextView) findViewById(R.id.matched_nickname);

        text_nickname.setText(receive_number);
        text_match_nickname.setText(send_number);

        try {
            gif = new GifDrawable(getResources(), R.drawable.match_success);
            gif_img = (GifImageView) findViewById(R.id.final_match_gif);
            gif_img.setImageDrawable(gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gif.start();
        gif.setSpeed(0.65f);
        gif.setLoopCount(1);

        place_info = (TextView) findViewById(R.id.place_info);
        place_map = (ImageView) findViewById(R.id.place_map);

        placeName.add("누오보 나폴리");
        placeURL.add("http://naver.me/5aVt87pM");

        placeName.add("Eek");
        placeURL.add("http://naver.me/IFjFGIGa");

        placeName.add("피에노");
        placeURL.add("http://naver.me/F0Kwcz1n");

        placeName.add("더 쉘터");
        placeURL.add("http://naver.me/GUvDmXNm");

        placeName.add("카페크러쉬온드 둔산점");
        placeURL.add("http://naver.me/GUvDsXRW");

        placeName.add("청담이상 탄방점");
        placeURL.add("http://naver.me/FeOCp9mp");

        placeName.add("코니스");
        placeURL.add("http://naver.me/Fy1YZkTM");

        placeName.add("MOOD");
        placeURL.add("http://naver.me/GOuQPzcB");

        placeName.add("성심당 케익부띠끄");
        placeURL.add("http://naver.me/5JJQDpTg");

        placeName.add("비바릴리 도안점");
        placeURL.add("http://naver.me/xZDOT1lg");

        place_info.setText(placeName.get(n));

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(placeURL.get(n)));

        place_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }
}
