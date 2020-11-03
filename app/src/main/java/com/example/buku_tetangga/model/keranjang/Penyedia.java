package com.example.buku_tetangga.model.keranjang;

import java.util.List;

public class Penyedia {
    private String nama_penyedia;
    private String username;
    private String foto_penyedia;
    private List<BukuInPenyedia> subItemList;

    public Penyedia(String nama_penyedia, String username, String foto_penyedia) {
        this.nama_penyedia = nama_penyedia;
        this.username = username;
        this.foto_penyedia = foto_penyedia;
    }

    public Penyedia(String nama_penyedia, String foto_penyedia, List<BukuInPenyedia> subItemList) {
        this.nama_penyedia = nama_penyedia;
        this.foto_penyedia = foto_penyedia;
        this.subItemList = subItemList;
    }

    public String getNama_penyedia() {
        return nama_penyedia;
    }

    public String getFoto_penyedia() {
        return foto_penyedia;
    }

    public String getUsername() {
        return username;
    }

    public List<BukuInPenyedia> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<BukuInPenyedia> subItemList) {
        this.subItemList = subItemList;
    }
}