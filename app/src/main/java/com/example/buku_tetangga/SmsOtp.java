package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class SmsOtp {
    @SerializedName("maxAttempt")
    private int maxAttempt;
    @SerializedName("otpstr")
    private String otpstr;
    @SerializedName("phoneNum")
    private String phoneNum;
    @SerializedName("expireIn")
    private int expireIn;
    @SerializedName("content")
    private String content;
    @SerializedName("digit")
    private int digit;

    public SmsOtp(int maxAttempt, String phoneNum, int expireIn, String content, int digit) {
        this.maxAttempt = maxAttempt;
        this.phoneNum = phoneNum;
        this.expireIn = expireIn;
        this.content = content;
        this.digit = digit;
    }

    public SmsOtp(int maxAttempt, String otpstr, int expireIn, int digit){
        this.maxAttempt = maxAttempt;
        this.otpstr = otpstr;
        this.expireIn = expireIn;
        this.digit = digit;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    public String getOtpStr() {
        return otpstr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public String getContent() {
        return content;
    }

    public int getDigit() {
        return digit;
    }
}
