package com.example.buku_tetangga.model.book_butang_activity;

public class RakBuku {
    private String id;
    private String harga;
    private String jumlah_stock;
    private String keterangan;
    private String foto;

    public RakBuku(String id, String harga, String jumlah_stock, String foto) {
        this.id = id;
        this.harga = harga;
        this.jumlah_stock = jumlah_stock;
        this.foto = foto;
    }

    public RakBuku(String id, String harga, String jumlah_stock, String keterangan, String foto) {
        this.id = id;
        this.harga = harga;
        this.jumlah_stock = jumlah_stock;
        this.keterangan = keterangan;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public String getHarga() {
        return harga;
    }

    public String getJumlah_stock() {
        return jumlah_stock;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getFoto() {
        return foto;
    }
}
