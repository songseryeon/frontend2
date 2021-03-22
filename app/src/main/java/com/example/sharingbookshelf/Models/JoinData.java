package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

//회원가입 요청시 보내는 데이터
public class JoinData {
    @SerializedName("email") //json key값이랑 똑같아야함
    private String email;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("password")
    private String password;

    public JoinData(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
