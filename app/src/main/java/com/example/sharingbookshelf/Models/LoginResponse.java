package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

//로그인 요청에 대한 응답으로 돌아올 데이터. DTO랑 비슷
public class LoginResponse {

    @SerializedName("mem_id")
    private int mem_id;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public int getMem_id() {
        return mem_id;
    }

    public void setMem_id(int mem_id) {
        this.mem_id = mem_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String str = "[mem] " + mem_id + " => email = " + email + " , password = " + password;
        return str;
    }

}


