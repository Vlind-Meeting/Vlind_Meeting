package com.example.vlind_meeting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class JoinSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Button survey_button = (Button) findViewById(R.id.survey_button);
        survey_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String user_gender = intent.getExtras().getString("user_gender");
                String user_password = intent.getExtras().getString("user_password");
                String user_name = intent.getExtras().getString("user_name");
                String user_number = intent.getExtras().getString("user_number");
                String user_nickname = intent.getExtras().getString("user_nickname");

                Intent intent0 = new Intent(JoinSurveyActivity.this, SurveyActivity.class);

                intent0.putExtra("user_gender", user_gender);
                intent0.putExtra("user_password", user_password);
                intent0.putExtra("user_name", user_name);
                intent0.putExtra("user_number", user_number);
                intent0.putExtra("user_nickname", user_nickname);

                startActivity(intent0);
            }
        });
    }
}
