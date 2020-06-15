package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class SmsNotif {
    @SerializedName("msisdn")
    private String msisdn;
    @SerializedName("content")
    private String content;

    public SmsNotif(String msisdn, String content) {
        this.msisdn = msisdn;
        this.content = content;
    }

    public String getMsisdn(){
        return msisdn;
    }

    public String getContent(){
        return content;
    }
}
