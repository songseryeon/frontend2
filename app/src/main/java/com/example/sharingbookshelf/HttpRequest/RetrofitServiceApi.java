package com.example.sharingbookshelf.HttpRequest;

import com.example.sharingbookshelf.Models.LoginResponse;
import com.example.sharingbookshelf.Models.SetUserInfoResponse;
import com.example.sharingbookshelf.Models.UserInfoData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

//DAO랑 비슷
//Call안에 서버로부터 넘겨받을 데이터 구조 정의
public interface RetrofitServiceApi {

    @POST("api/users")
    Call<LoginResponse> userLogin();

    @PATCH("api/users")
    Call<SetUserInfoResponse> setUserInfo(@Body UserInfoData userInfoData);

   /* @GET("api/members")
    Call<LoginResponse> getUser(
            @Query("id") int id);

    @POST("api/members")
    Call<JoinResponse> userJoin(
            @Body JoinData joinData); //user/join 경로로 body에 JoinData 담아보내고 JoinResponse 구조로 받아올거*/

}

