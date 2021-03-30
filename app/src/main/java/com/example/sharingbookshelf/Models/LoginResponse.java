package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;
//DTO
public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String msg;

    @SerializedName("flag")
    private int flag;

    @SerializedName("token")
    private String token;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getFlag() {
        return flag;
    }

    public String getToken() {
        return token;
    }
}

