package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharingbookshelf.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText login_email, login_password;
    Button login_general;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        signUp = findViewById(R.id.login_signUp);

        //Sign Up Button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class); //1번 파라미터 : 메인 액티비티 자신, 2번 : 호출할 클래스
                startActivity(intent); //intent => Activity끼리 서로 호출하기 위해서 필요한 통신장치.
            }
        });
    }

    //login Button
    public void button_login(View view) {
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();

        if (view.getId() == R.id.login_button) {
            //Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
            Intent intent = new Intent(MainActivity.this, SelectAgeAreaActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            Toast.makeText(MainActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else if (view.getId() == R.id.login_button_Google) {
            //Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
            Intent intent = new Intent(MainActivity.this, SelectAgeAreaActivity.class);
            startActivity(intent);
        }
    }
}