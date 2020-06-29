package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class VoicecallOtp2 {
    @SerializedName("digit")
    private int digit;
    @SerializedName("otpStr")
    private String otpStr;

    public VoicecallOtp2(String otpStr, int digit) {
        this.otpStr = otpStr;
        this.digit = digit;
    }

    public int getDigit() {
        return digit;
    }

    public String getOtpStr() {
        return otpStr;
    }
}
