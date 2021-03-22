package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {

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
