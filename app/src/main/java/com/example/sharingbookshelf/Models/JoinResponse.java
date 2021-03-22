package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {
    // 서버에서 받아오기

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
