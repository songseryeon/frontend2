package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;
//DTO
public class LoginResponse {

    @SerializedName("msg")
    private String msg;

    @SerializedName("flag")
    private int flag;

    @SerializedName("accessToken")
    private String accessToken;

    public String getMsg() {
        return msg;
    }

    public int getFlag() {
        return flag;
    }

    public String getAccessToken() {
        return accessToken;
    }



}

