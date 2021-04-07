/*
package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sharingbookshelf.R;
import com.google.firebase.auth.FirebaseAuth;

public class TakingPhotoActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRevoke, btnLogout;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_photo);

        btnLogout = findViewById(R.id.btn_logout);
        btnRevoke = findViewById(R.id.btn_revoke);

        mAuth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(this);
        btnRevoke.setOnClickListener(this);

    }

    //로그아웃
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    //탈퇴
    private void revokeAccess() {
        mAuth.getCurrentUser().delete();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                signOut();
                finishAffinity();
                break;
            case R.id.btn_revoke:
                revokeAccess();
                finishAffinity();
                break;
        }
    }
}
*/