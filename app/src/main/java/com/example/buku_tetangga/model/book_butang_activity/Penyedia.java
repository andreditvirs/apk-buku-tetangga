package com.example.buku_tetangga.model.book_butang_activity;

public class Penyedia {
    private String username;
    private String notelp;
    private String alamat;

    public Penyedia(String username, String notelp, String alamat) {
        this.username = username;
        this.notelp = notelp;
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getAlamat() {
        return alamat;
    }
}
