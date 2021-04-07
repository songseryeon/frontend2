package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.LoginResponse;
import com.example.sharingbookshelf.Models.SetUserInfoResponse;
import com.example.sharingbookshelf.Models.UserInfoData;
import com.example.sharingbookshelf.R;
import com.example.sharingbookshelf.RetrofitClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAgeAreaActivity extends Activity {

    private final String TAG = "아이북쉐어";
    private Spinner sp_age;
    private TextView et_address;
    private final String[] ages = {"1세","2세","3세","4세","5세","6세","7세","8세","9세","10세",
            "11세","12세","13세","14세","15세","16세","17세","18세"};

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_age_area);
        
        // 자녀 나이 선택
        sp_age = (Spinner) findViewById(R.id.reg_childAge);
        //age = findViewById(R.id.showage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_age.setAdapter(adapter);

        // 주소지 설정 팝업
        Button btn_address = (Button) findViewById(R.id.reg_address);
        Button btn_camera = (Button) findViewById(R.id.camera_button);
        et_address = findViewById(R.id.et_address);

        if (btn_address != null) {
            btn_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SelectAgeAreaActivity.this, WebViewActivity.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });
        }

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptNext();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == SEARCH_ADDRESS_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString("data");
                if (data != null) {
                    et_address.setText(data);
                }
            }
        }
    }

    private void attemptNext() {

        String age = sp_age.getSelectedItem().toString();
        String address = et_address.getText().toString();
        String nickname = "안녕";
        int sex = 1; //1은 여자 0은 남자

        boolean cancel = false;
        View focusView = null;

        //age 유효성 검사
        /*if (age.isEmpty()) {
            age.setError("비밀번호를 입력해주세요.");
            focusView = PasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            PasswordView.setError("8자 이상의 비밀번호를 입력해주세요.");
            focusView = PasswordView;
            cancel = true;
        }*/

        //address 유효성 검사
        if (address.isEmpty()) {
            //et_address.setError("지역을 설정해주세요.");
            focusView = et_address;
            cancel = true;
        }

        if(cancel) {
            focusView.requestFocus();
        } else {
            startSetInfo(new UserInfoData(nickname, age, address));
        }
    }

    private void startSetInfo(UserInfoData userInfo) {
        RetrofitServiceApi retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        System.out.println(MainActivity.getJWT());
        Call<SetUserInfoResponse> call = retrofitServiceApi.setUserInfo(userInfo);
        call.enqueue(new Callback<SetUserInfoResponse>() {
            @Override
            public void onResponse(Call<SetUserInfoResponse> call, Response<SetUserInfoResponse> response) {
                Log.d(TAG, response.body().getMsg());
                updateUI();
            }

            @Override
            public void onFailure(Call<SetUserInfoResponse> call, Throwable t) {
                Log.e(TAG, "회원정보 등록 응답 못받음", t);
            }
        });
    }

    private void updateUI() {
        Intent intent = new Intent(this, TakingPhotoActivity.class);
        startActivity(intent);
        finish();
    }
}