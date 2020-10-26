package com.example.buku_tetangga.model;

public class Buku {
    private String isbn;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String kategori;
    private String deskripsi;

    public Buku(String judul) {
        this.judul = judul;
    }

    public Buku(String judul, String pengarang, String penerbit) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
    }

    public Buku(String isbn, String judul, String pengarang, String penerbit, String kategori, String deskripsi) {
        this.isbn = isbn;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
