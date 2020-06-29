package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class FotoBuku {
    @SerializedName("error")
    private boolean error;
    @SerializedName("msg_error")
    private String msg_errror;

    public boolean getError() {
        return error;
    }

    public String getMessageError() {
        return msg_errror;
    }
}
