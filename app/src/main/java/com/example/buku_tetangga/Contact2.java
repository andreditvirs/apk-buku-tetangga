package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haerul on 17/03/18.
 */

public class Contact2 {

    @SerializedName("id") private int Id;
    @SerializedName("name") private String Name;
    @SerializedName("email") private String Email;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }
}
