package com.example.vlind_meeting;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView location_icon, time_icon;

    List<String> placeName = new ArrayList<String>();
    List<String> placeURL = new ArrayList<String>();
    int n;
    Intent i = new Intent(Intent.ACTION_VIEW);
    private long backpressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_match);

        location_icon = (ImageView) findViewById(R.id.location_icon);
        time_icon = (ImageView) findViewById(R.id.time_icon);
        location_icon.setColorFilter(getResources().getColor(R.color.tiffany));
        time_icon.setColorFilter(getResources().getColor(R.color.tiffany));

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

        //여기서 n값 데이터베이스에서 가져오기
        n=0;
        place_info.setText(placeName.get(n));
        i.setData(Uri.parse(placeURL.get(n)));

        place_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        if (System.currentTimeMillis() > backpressedTime + 2000) {
//            backpressedTime = System.currentTimeMillis();
////            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
//        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
//            moveTaskToBack(true);
//            finishAndRemoveTask();
//            System.exit(0);
//        }
    }
}
