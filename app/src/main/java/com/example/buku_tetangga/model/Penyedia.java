package com.example.buku_tetangga.model;

public class Penyedia {
    private String username;
    private String notelp;
    private String alamat;
    private String nama_lengkap;
    private String foto;

    public Penyedia(String username, String notelp, String alamat) {
        this.username = username;
        this.notelp = notelp;
        this.alamat = alamat;
    }

    public Penyedia(String nama_lengkap, String foto) {
        this.nama_lengkap = nama_lengkap;
        this.foto = foto;
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

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public String getFoto() {
        return foto;
    }
}
