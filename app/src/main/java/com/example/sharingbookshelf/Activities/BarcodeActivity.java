package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.sharingbookshelf.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_photo);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(ZxingActivity.class); //세로모드
        integrator.setOrientationLocked(false);
        integrator.setPrompt("등록할 책의 바코드를 읽어주세요.");
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
        //new IntentIntegrator(this).initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String ISBN = result.getContents();
                Bundle extra = new Bundle();
                Intent intent = getIntent();
                extra.putString("ISBN", ISBN);
                intent.putExtras(extra);
                setResult(RESULT_OK, intent);
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}