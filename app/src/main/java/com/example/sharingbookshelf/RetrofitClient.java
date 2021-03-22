package com.example.sharingbookshelf;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) { //싱글톤
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/") //서버랑연결
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
