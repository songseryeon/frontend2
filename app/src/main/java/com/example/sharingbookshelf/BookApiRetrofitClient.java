package com.example.sharingbookshelf;

import android.text.TextUtils;

import com.example.sharingbookshelf.HttpRequest.AuthenticationInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookApiRetrofitClient {
    public static final String BASE_URL = "https://dapi.kakao.com/";
    private static final String authCode = "14a0c004c5eaeb4ddb9a71b0cb6862ce";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor("KakaoAK " + authCode);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        return retrofit.create(serviceClass);
    }
}
