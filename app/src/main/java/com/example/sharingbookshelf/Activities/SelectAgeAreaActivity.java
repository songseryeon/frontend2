package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sharingbookshelf.R;

public class SelectAgeAreaActivity extends Activity {

    Button btn_camera, btn_address;
    Spinner sp_age;
    TextView homeview, et_address;
    String[] ages = {"1세","2세","3세","4세","5세","6세","7세","8세","9세","10세",
            "11세","12세","13세","14세","15세","16세","17세","18세"};

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_age_area);

        // 카메라로 전환
        btn_camera = (Button) findViewById(R.id.camera_button);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TakingPhotoActivity.class); //1번 파라미터 : 메인 액티비티 자신, 2번 : 호출할 클래스
                startActivity(intent); //intent => Activity끼리 서로 호출하기 위해서 필요한 통신장치.
            }
        });
        
        // 자녀 나이 선택
        sp_age = (Spinner) findViewById(R.id.reg_childAge);
        //age = findViewById(R.id.showage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_age.setAdapter(adapter);

//        sp_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            // 선택된 상태
//           @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                text.setText(ages[position]);
//            }
//            // 선택되지 않은 상태
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                text.setText("자녀 나이를 입력하세요");
//            }
//        });

        // 주소지 설정 팝업
        btn_address = findViewById(R.id.reg_address);

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

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        et_address.setText(data);
                    }
                }
                break;
        }
    }
}