package edu.eci.cosw.APIApp.network.model;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("accessToken")
    private String accessToken;

    Token(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
