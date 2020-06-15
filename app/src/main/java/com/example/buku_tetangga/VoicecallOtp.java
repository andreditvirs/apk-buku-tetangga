package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class VoicecallOtp {
    @SerializedName("phoneNum")
    private String phoneNum;
    @SerializedName("digit")
    private int digit;
    @SerializedName("otpStr")
    private String otpStr;

    public VoicecallOtp(String phoneNum, int digit) {
        this.phoneNum = phoneNum;
        this.digit = digit;
    }

    public VoicecallOtp(String otpStr, int digit, String verifications) {
        this.otpStr = otpStr;
        this.digit = digit;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getDigit() {
        return digit;
    }

    public String getOtpStr() {
        return otpStr;
    }
}
