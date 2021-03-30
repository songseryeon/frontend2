/*
package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sharingbookshelf.R;
import com.example.sharingbookshelf.Models.LoginResponse;
import com.example.sharingbookshelf.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginResultActivity extends AppCompatActivity {

    RetrofitServiceApi retrofitServiceApi;

    TextView textView_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_get = findViewById(R.id.사진api);
        textView_get.setText("");

        //retrofit
        retrofitServiceApi = RetrofitClient.getRetrofit().create(RetrofitServiceApi.class);
        Call<LoginResponse> call = retrofitServiceApi.getUser(1);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.out.println("body => " + response);
                textView_get.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                textView_get.setText( "onFailure" );
            }
        });

    }
}*/
