package com.example.vlind_meeting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;

public class JoinActivity extends AppCompatActivity {
    private final String TAG = "JoinActivityLog";

    private String user_name, user_nickname, user_number, user_password, user_gender;
    private EditText join_name, join_nickname, join_number, join_password;
    private CheckBox check_man, check_woman;
    private Button join_button, delete_button;
    private Retrofit retrofit;
    private ResponseLogin responseLogin;

    ConstraintLayout layout;

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
        join_button = (Button) findViewById(R.id.join_button);
        delete_button = (Button) findViewById(R.id.delete_button);

        responseLogin = RetrofitClientInstance.getClient().create(ResponseLogin.class);

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

                        JoinRequest joinRequest = new JoinRequest(user_name, user_number, user_nickname, user_password, user_gender);
                        responseLogin.postJoin(joinRequest).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    try {
                                        String result = response.body().string();
                                        if(result.equals("succeed")){
                                            new AlertDialog.Builder(JoinActivity.this)
                                                    .setTitle("사용 약관 동의")
                                                    .setMessage("녹음된 파일이 다른 사용자에게 공유되는 것에 동의합니까?")
                                                    .setPositiveButton("동의", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(JoinActivity.this, JoinSurveyActivity.class);
                                                            intent.putExtra("user_name", user_name);
                                                            intent.putExtra("user_number", user_number);
                                                            intent.putExtra("user_nickname", user_nickname);
                                                            intent.putExtra("user_password", user_password);
                                                            intent.putExtra("user_gender", user_gender);
                                                            startActivity(intent);
                                                        }
                                                    })
                                                    .show();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "이미 등록된 전화번호입니다.", Toast.LENGTH_SHORT).show();
                                        }
                                        Log.v(TAG, "result = " + result);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.v(TAG, "error = " + String.valueOf(response.code()));
                                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.v(TAG, "Fail");
                                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });

        layout = (ConstraintLayout) findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(join_name.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(join_nickname.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(join_number.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(join_password.getWindowToken(), 0);
            }
        });

    };
}
