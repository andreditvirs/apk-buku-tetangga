package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class RakBuku {
        /*
        INSTANCE FIELDS
         */
        @SerializedName("id")
        private int id;
        @SerializedName("username")
        private String username;
        @SerializedName("isbn")
        private String isbn;
        @SerializedName("judul_buku")
        private String judul_buku;
        @SerializedName("pengarang")
        private String pengarang;
        @SerializedName("penerbit")
        private String penerbit;
        @SerializedName("bahasa")
        private String bahasa;
        @SerializedName("deskripsi")
        private String deskripsi;
        @SerializedName("harga")
        private int harga;
        @SerializedName("jumlah_stock")
        private int jumlah_stock;
        @SerializedName("berat")
        private String berat;
        @SerializedName("panjang")
        private String panjang;
        @SerializedName("lebar")
        private String lebar;
        @SerializedName("foto")
        private String foto;

        public RakBuku(int id, String judul_buku, String pengarang, String penerbit, int harga, int jumlah_stock, String foto) {
            this.id = id;
            this.judul_buku = judul_buku;
            this.pengarang = pengarang;
            this.penerbit = penerbit;
            this.harga = harga;
            this.jumlah_stock = jumlah_stock;
            this.foto = foto;
        }

        /*
         *GETTERS AND SETTERS
         */
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getJudul_buku() {
            return judul_buku;
        }
        public void setJudul_buku(String judul_buku) {
            this.judul_buku = judul_buku;
        }
        public String getPengarang() {
            return pengarang;
        }
        public String getPenerbit() {
            return penerbit;
        }
        public int getJumlah_stock(){return jumlah_stock;}
        public int getHarga() {
            return harga;
        }
        public String getFoto(){
            return foto;
        }

        public String setStock(){
            if(getHarga() == 0){
                return "Tersedia";
            }
            return "Habis";
        }

        public String getUsername(){
            return username;
        }
        public String getDeskripsi() {
            return deskripsi;
        }
        public String getIsbn(){return isbn;}
        public String getBahasa(){return bahasa;}
        public String getBerat(){return berat;}
        public String getPanjang(){return panjang;}
        public String getLebar(){return lebar;}
    /*
            TOSTRING
             */
        @Override
        public String toString() {
            return judul_buku;
        }

}
