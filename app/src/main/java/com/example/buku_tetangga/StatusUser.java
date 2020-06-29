package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class StatusUser {
    @SerializedName("error")
    private boolean error;
    @SerializedName("error_msg")
    private String error_msg;
    @SerializedName("status")
    private String status;

    public StatusUser(boolean error, String error_msg, String status) {
        this.error = error;
        this.error_msg = error_msg;
        this.status = status;
    }

    public boolean getError() {
        return error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public String getStatus() {
        return status;
    }
}
