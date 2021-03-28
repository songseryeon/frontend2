package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

//회원가입 요청시 보내는 데이터
public class JoinData {
    @SerializedName("email") //json key값이랑 똑같아야함
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;

    public JoinData(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
