package com.example.vlind_meeting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    private String user_name, user_number, user_password, user_gender, user_nickname;
    private EditText join_name, join_password, join_number, join_nickname;
    private Button join_button, delete_button;
    private CheckBox check_man, check_woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        join_name = (EditText) findViewById(R.id.join_name);
        join_password = (EditText) findViewById(R.id.join_password);
        join_number = (EditText) findViewById(R.id.join_number);
        join_nickname = (EditText) findViewById(R.id.join_nickname);
        check_man = (CheckBox) findViewById(R.id.check_man);
        check_woman = (CheckBox) findViewById(R.id.check_woman);
        check_man.bringToFront();
        check_woman.bringToFront();
        join_button = (Button) findViewById(R.id.join_button);
        delete_button = (Button) findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        join_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                user_name = join_name.getText().toString();
                user_password = join_password.getText().toString();
                user_number = join_number.getText().toString();
                user_nickname = join_nickname.getText().toString();

                if(user_name.getBytes().length <= 0 || user_number.getBytes().length <= 0 || user_password.getBytes().length <= 0 || user_nickname.getBytes().length <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setTitle("알림창").setMessage("값을 입력해주세요.");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    if((check_man.isChecked() && check_woman.isChecked())
                            || (!check_man.isChecked() && !check_woman.isChecked())){
                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                        builder.setTitle("알림창").setMessage("성별을 올바르게 체크해주세요.");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                    else{
                        if(check_man.isChecked() && !check_woman.isChecked())
                            user_gender = "man";
                        else
                            user_gender = "woman";

                       //회원가입 시 데이터베이스에 새롭게 정보 인서트 하는 거 아직 구현 안 됨

                        new AlertDialog.Builder(JoinActivity.this)
                                .setTitle("동의")
                                .setMessage("hihihi")
                                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(JoinActivity.this, JoinSurveyActivity.class);
                                        intent.putExtra("user_gender", user_gender);
                                        intent.putExtra("user_password", user_password);
                                        intent.putExtra("user_name", user_name);
                                        intent.putExtra("user_number", user_number);
                                        intent.putExtra("user_nickname", user_nickname);
                                        startActivity(intent);
                                    }
                                })
                                .show();

                    }

                }
            }
        });
    };
}
