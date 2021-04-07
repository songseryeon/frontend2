package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class SetUserInfoResponse {
    @SerializedName("msg")
    private String msg;

    public String getMsg() {
        return msg;
    }
}
