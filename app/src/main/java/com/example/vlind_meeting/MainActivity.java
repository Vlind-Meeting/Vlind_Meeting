package com.example.vlind_meeting;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivityLog";

    private String user_number, user_password;
    private EditText login_number, login_password;
    private TextView join_button;
    private Button login_button;
    private Retrofit retrofit;
    private ResponseLogin responseLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        setActionBar();
        login_number = (EditText) findViewById(R.id.login_number);
        login_password = (EditText) findViewById(R.id.mbti);
        join_button = (TextView) findViewById(R.id.join_button);
        login_button = (Button) findViewById(R.id.login_button);

        responseLogin = RetrofitClientInstance.getClient().create(ResponseLogin.class);

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
                user_password = login_password.getText().toString();
                user_number = login_number.getText().toString();

                if(user_number.getBytes().length <= 0 || user_password.getBytes().length <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("알림창").setMessage("값을 입력해주세요");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                else {
                    //데이터베이스에서 정보를 가져와 비교해서 로그인 여부를 결정하는 코드 아직 작성 안됨
                    Call<LoginResponse> call_get = responseLogin.getLogin(user_number);
                    call_get.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                LoginResponse result = response.body();
                                String exist_number = result.getNumber();
                                String exist_password = result.getPassword();
                                String exist_nickname = result.getNickname();
                                String exist_name = result.getName();
                                String exist_filename = result.getFilename();
//                                System.out.println('!');
//                                System.out.println(exist_name);
//                                System.out.println(exist_filename);
//                                System.out.println(exist_nickname);
//                                System.out.println(exist_number);
//                                System.out.println(exist_password);
//                                System.out.println('!');


                                if (exist_number.equals(user_number)) {
                                    if(exist_password.equals(user_password)) {
                                        ProfileClass profileClass = ProfileClass.getInstance();
                                        profileClass.setUser_number(exist_number);
                                        profileClass.setUser_password(exist_password);
                                        profileClass.setUser_nickname(exist_nickname);
                                        profileClass.setUser_name(exist_name);
                                        profileClass.setUser_filename(exist_filename);
//                                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                        intent.putExtra("user_number", user_number);
                                        startActivity(intent);
                                    }
                                    else
                                        Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "전화번호를 확인해주세요", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.v(TAG, "Fail");
                            Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }

        });
    }

//    private void setActionBar(){
//        // 커스텀 액션바 적용
//        CustomActionBar ca = new CustomActionBar(this, getSupportActionBar());
//        ca.setActionBar();
//    }
}