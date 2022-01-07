package com.example.vlind_meeting;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        setActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        EditText login_name = (EditText) findViewById(R.id.login_name);
        EditText login_number = (EditText) findViewById(R.id.login_number);
        EditText login_password = (EditText) findViewById(R.id.mbti);
        TextView join_button = (TextView) findViewById(R.id.join_button);
        Button login_button = (Button) findViewById(R.id.login_button);

        join_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String user_name = login_name.getText().toString();
                String user_password = login_password.getText().toString();
                String user_number = login_number.getText().toString();

                if(user_number.getBytes().length <= 0 || user_password.getBytes().length <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("알림창").setMessage("값을 입력해주세요");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                else {
                    //데이터베이스에서 정보를 가져와 비교해서 로그인 여부를 결정하는 코드 아직 작성 안됨
                    Intent intent = new Intent(MainActivity.this, MainAppActivity.class);
                    startActivity(intent);
                }


            }

        });
    }

    private void setActionBar(){
        // 커스텀 액션바 적용
        CustomActionBar ca = new CustomActionBar(this, getSupportActionBar());
        ca.setActionBar();
    }
}