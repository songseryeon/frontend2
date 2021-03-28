package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class VerifyGoogleTokenData {
    @SerializedName("GoogleToken")
    private String GoogleToken;

    public VerifyGoogleTokenData(String GoogleToken) {
        this.GoogleToken = GoogleToken;
    }
}
