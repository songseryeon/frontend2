package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.SetUserInfoResponse;
import com.example.sharingbookshelf.Models.UserInfoData;
import com.example.sharingbookshelf.R;
import com.example.sharingbookshelf.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAgeAreaActivity extends Activity {

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private static final int IMAGE_VIEW_REQUEST = 10001;
    private final String[] ages = {"1세", "2세", "3세", "4세", "5세", "6세", "7세", "8세", "9세", "10세",
            "11세", "12세", "13세", "14세", "15세", "16세", "17세", "18세"};

    private Spinner sp_age;
    private TextView tv_address;
    private ImageView profileView;
    private Button btn_address;
    private RadioGroup radioGroup;
    private TextInputEditText et_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_age_area);

        initializeView();
        setProfile(); //프로필 사진 등록
        selectAge(); // 자녀 나이 선택
        searchAddress(); // 주소지 설정 팝업

        Button btn_register = findViewById(R.id.btn_register); //등록 버튼
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptNext();
            }
        });
    }

    private void initializeView() {
        profileView = findViewById(R.id.profileView);
        et_nickname = findViewById(R.id.input_nickname);
        btn_address = findViewById(R.id.reg_address);
        tv_address = findViewById(R.id.tv_address);
        sp_age = findViewById(R.id.reg_childAge);
        radioGroup = findViewById(R.id.radioGroup_sex);
    }

    private void setProfile() {
        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMAGE_VIEW_REQUEST);
            }
        });
    }

    private void selectAge() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_age.setAdapter(adapter);
    }

    private void searchAddress() {
        if (btn_address != null) {
            btn_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SelectAgeAreaActivity.this, WebViewActivity.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == SEARCH_ADDRESS_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString("data");
                if (data != null) {
                    tv_address.setText(data);
                }
            }
        }
        if (requestCode == IMAGE_VIEW_REQUEST) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(intent.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    profileView.setImageBitmap(img);
                } catch (Exception e) {
                    Log.e(MainActivity.MAIN_TAG, "이미지 불러오기 실패", e);
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void attemptNext() {

        et_nickname.setError(null);
        tv_address.setError(null);

        String age = sp_age.getSelectedItem().toString();
        String address = tv_address.getText().toString();
        String nickname = et_nickname.getText().toString();

        //성별값 변환
        int selectedId = radioGroup.getCheckedRadioButtonId(); //체크된 라디오버튼 id 가져옴
        RadioButton radioButton = findViewById(selectedId); //그 id가 어떤 라디오버튼인지 찾음
        String selectedSex = radioButton.getText().toString();

        int sex = 0; //1이면 남자, 2면 여자
        if (selectedSex.equals("남자")) sex = 1;
        else if (selectedSex.equals("여자")) sex = 2;

        boolean cancel = false;
        View focusView = null;

        //유효성 체크
        if (nickname.isEmpty()) {
            et_nickname.setError("닉네임을 입력해주세요.");
            focusView = et_nickname;
            cancel = true;
        }

        if (address.isEmpty()) {
            tv_address.setError("지역을 설정해주세요.");
            focusView = tv_address;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startSetInfo(new UserInfoData(nickname, address, age, sex));
        }
    }

    private void startSetInfo(UserInfoData userInfo) {
        RetrofitServiceApi retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<SetUserInfoResponse> call = retrofitServiceApi.setUserInfo(userInfo);
        call.enqueue(new Callback<SetUserInfoResponse>() {
            @Override
            public void onResponse(Call<SetUserInfoResponse> call, Response<SetUserInfoResponse> response) {
                Log.d(MainActivity.MAIN_TAG, response.body().getMsg());
                updateUI();
            }

            @Override
            public void onFailure(Call<SetUserInfoResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "회원정보 등록 응답 못받음", t);
            }
        });
    }

    private void updateUI() {
        Intent intent = new Intent(this, TakingPhotoActivity.class);
        startActivity(intent);
        finish();
    }
}