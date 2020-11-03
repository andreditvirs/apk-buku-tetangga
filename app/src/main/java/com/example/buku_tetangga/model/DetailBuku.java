package com.example.buku_tetangga.model;

import com.example.buku_tetangga.model.keranjang.Penyedia;
public class DetailBuku {

    private String keranjang_id;
    private Penyedia penyedia;
    private RakBuku rakBuku;
    private Buku buku;

    public DetailBuku(String keranjang_id, Penyedia penyedia, RakBuku rakBuku, Buku buku) {
        this.keranjang_id = keranjang_id;
        this.penyedia = penyedia;
        this.rakBuku = rakBuku;
        this.buku = buku;
    }

    public String getKeranjang_id() {
        return keranjang_id;
    }

    public Penyedia getPenyedia() {
        return penyedia;
    }

    public RakBuku getRakBuku() {
        return rakBuku;
    }

    public Buku getBuku() {
        return buku;
    }
}
