package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;

public class PetunjukPenggunaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk_pengguna);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<PaketBantuan> paket_bantuan = new ArrayList<>();

        String judul1, desc11, desc12,desc13,desc14,
                judul2, desc21,
                judul3, desc31,
                judul4, desc41;

        //Judul 1
        desc11 = "Langkah 1\n" +
                "Daftarkan diri dan lengkapi profil Anda. Setelah itu, Anda dapat menjadi Penyewa maupun Penyedia buku.";
        desc12 ="Langkah 2\n" +
                "Pastikan Anda telah menentukan jaminan untuk dapat menyewa buku yang Anda inginkan.";
        desc13 = "Langkah 3\n" +
                "Lihat buku rekomendasi di beranda atau Anda dapat mencari buku yang Anda inginkan. Setelah itu, klik sewa supaya terhubung dengan Penyedia buku.\n";
        desc14 = "Langkah 4\n" +
                "Setalah Anda terhubung dengan Penyedia buku. Anda akan mendapatkan kode sewa dan memasukkan kode pemilik buku. Kemudian Anda dapat melakukan transaksi dan mengambul buku secara langsung bertemu dengan Penyedia buku.";

        ArrayList<Bantuan> judul_bantuan_1 = new ArrayList<>();
        judul_bantuan_1.add(new Bantuan(desc11));
        judul_bantuan_1.add(new Bantuan(desc12));
        judul_bantuan_1.add(new Bantuan(desc13));
        judul_bantuan_1.add(new Bantuan(desc14));

        judul1 = "Bagaimana cara kerja Buku Tetangga?";
        PaketBantuan bantuan1 = new PaketBantuan(judul1, judul_bantuan_1);
        paket_bantuan.add(bantuan1);

        //Judul 2
        desc21 = "Penyedia buku merupakan orang yang bersedia untuk menyewakan bukunya dengan ketentuan harga sewa yaitu 10% dari harga asli buku. Penyedia buku dapat menambahkan buku yang hendak disewakan dengan mudah yaitu dengan cukup memasukkan judul buku yang hendak disewakan.\n" +
                "\n" +
                "Sedangkan Penyewa buku merupakan orang yang ingin menyewa buku dengan harga terjangkau, waktu tertentu, dan lokasi terdekat layaknya tetangga. Penyewa buku dapat mencari buku yang diingkan berdasarkan lokasi terdekat atau judul buku.\n";

        ArrayList<Bantuan> judul_bantuan_2 = new ArrayList<>();
        judul_bantuan_2.add(new Bantuan(desc21));

        judul2 = "Apa perbedaan Penyewa dan Penyedia buku?";
        PaketBantuan bantuan2 = new PaketBantuan(judul2, judul_bantuan_2);
        paket_bantuan.add(bantuan2);

        //Judul 3
        desc31 = "Jaminan yang ditawarkan ada 2 pilihan dan dapat memilih keduanya. Jaminan terdiri atas KTP atau deposit.\n" +
                "\n" +
                "Jika Anda memilih jaminan KTP, makan Anda harus mengupload foto KTP. Lalu ketika menyewa buku, KTP atau Tanda Pengenal lainnya dapat Anda serahkan kepada penyedia buku sebagai jaminan.\n" +
                "\n" +
                "Jika Anda memilih jaminan Deposit, maka Anda harus mentrasfer sejumlah uang minimal ( Rp. 25.000,- ). Kemudian mengupload bukti transfer, ketentuan Deposit yaitu ketika Anda mendepositkan sebesar uang minimal ( Rp. 25.000,- ), Anda hanya akan dapat menyewa buku dengan harga asli 25.000 ke bawah dan tetap membayar harga sewa kepada penyedia buku, sistem akan memotong uang deposit Anda. Setelah Anda selesai menyewa buku dan mengembalikannya, maka sistem akan mengembalikan nominal Deposit Anda .\n" +
                "\n";
        ArrayList<Bantuan> judul_bantuan_3 = new ArrayList<>();
        judul_bantuan_3.add(new Bantuan(desc31));

        judul3 = "Bagaimana jaminan yang harus dipilih?";
        PaketBantuan bantuan3 = new PaketBantuan(judul3, judul_bantuan_3);
        paket_bantuan.add(bantuan3);

        //Judul 4
        desc41 = "Pembayaran dilakukan dengan cara Call Of Delivery (COD) yaitu bertemu secara langsung dan membayar ketika buku sudah didapat. User Penyewa dapat membayar ketika buku sudah didapat. User Penyewa dapat membayar harga sewa dan menerima buku dari Penyedia buku.";
        ArrayList<Bantuan> judul_bantuan_4 = new ArrayList<>();
        judul_bantuan_4.add(new Bantuan(desc41));

        judul4 = "Bagaimana jaminan yang harus dipilih?";
        PaketBantuan bantuan4 = new PaketBantuan(judul4, judul_bantuan_4);
        paket_bantuan.add(bantuan4);

        BantuanAdapter adapter = new BantuanAdapter(paket_bantuan);
        recyclerView.setAdapter(adapter);
    }

    public void toHome(View view) {
        startActivity(new Intent(this, Navbar.class));
        Animatoo.animateSlideDown(this);
        finish();
    }
}
