/*
package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.JoinData;
import com.example.sharingbookshelf.Models.JoinResponse;
import com.example.sharingbookshelf.R;
import com.example.sharingbookshelf.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText EmailView, NicknameView, PasswordView;
    private Button JoinButton;
    private ProgressBar progressBar;
    RetrofitServiceApi retrofitServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EmailView = findViewById(R.id.join_email);
        NicknameView = findViewById(R.id.join_nickname);
        PasswordView = findViewById(R.id.join_password);
        JoinButton = findViewById(R.id.join_button);

        JoinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                attemptJoin();
            }
        });
    }

    public void button_goMain(View view) { //로그인 화면으로 돌아가기
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void attemptJoin() {
        EmailView.setError(null);
        NicknameView.setError(null);
        PasswordView.setError(null);

        String email = EmailView.getText().toString();
        String nickname = NicknameView.getText().toString();
        String password = PasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //패스워드 유효성 검사
        if (password.isEmpty()) {
            PasswordView.setError("비밀번호를 입력해주세요.");
            focusView = PasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            PasswordView.setError("8자 이상의 비밀번호를 입력해주세요.");
            focusView = PasswordView;
            cancel = true;
        }

        //이메일 유효성 검사
        if (email.isEmpty()) {
            EmailView.setError("이메일을 입력해주세요.");
            focusView = EmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            EmailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = EmailView;
            cancel = true;
        }

        //닉네임 유효성 검사
        if (nickname.isEmpty()) {
            NicknameView.setError("이름을 입력해주세요.");
            focusView = NicknameView;
            cancel = true;
        }

        if(cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(email, nickname, password));
        }

    }

    //정상적인 회원가입 진행 메소드
    private void startJoin(JoinData joinData) {
        retrofitServiceApi = RetrofitClient.getRetrofit().create(RetrofitServiceApi.class);
        Call<JoinResponse> call = retrofitServiceApi.userJoin(joinData);
        call.enqueue(new Callback<JoinResponse>() {

            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(RegisterActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    Intent intent = new Intent(RegisterActivity.this, TakingPhotoActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

}*/
