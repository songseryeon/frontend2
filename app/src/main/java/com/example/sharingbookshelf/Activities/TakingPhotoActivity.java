package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.sharingbookshelf.R;

public class TakingPhotoActivity extends AppCompatActivity {
    private static final String TAG = "iBookShare";
    private static final int BARCODE_ACTIVITY = 10000;
    private static final int SELFADD_ACTIVITY =  10001;

    private ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_photo);

        Button btn_photo = findViewById(R.id.btn_barcode);
        Button btn_selfAddBook = findViewById(R.id.btn_selfAddBook);

        iv_photo = findViewById(R.id.iv_photo);

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_barcode) {
                    Intent intent = new Intent(TakingPhotoActivity.this, BarcodeActivity.class);
                    startActivityForResult(intent, BARCODE_ACTIVITY);
                }
                if (v.getId() == R.id.btn_selfAddBook) {
                    Intent intent = new Intent(TakingPhotoActivity.this, SelfAddBookPopupActivity.class);
                    intent.putExtra("data", "Test Popup");
                    startActivityForResult(intent, SELFADD_ACTIVITY);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == BARCODE_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString("ISBN");
                if (data != null) {
                    //et_address.setText(data);
                    Log.d(TAG, data);
                }
            }
        }
        if (requestCode == SELFADD_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString("data");
                if (data != null) {
                    //et_address.setText(data);
                }
            }
        }
    }


}






