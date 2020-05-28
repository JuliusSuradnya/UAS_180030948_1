package com.aa183.suradnya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgBuku;
    private TextView tvJudul, tvTanggal, tvPenulis, tvPenerbit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgBuku = findViewById(R.id.iv_buku);
        tvJudul = findViewById(R.id.tv_judul);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvPenerbit = findViewById(R.id.tv_penerbit);

        Intent terimaData = getIntent();
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvTanggal.setText(terimaData.getStringExtra("TANGGAL"));
        tvPenulis.setText(terimaData.getStringExtra("PENULIS"));
        tvPenerbit.setText(terimaData.getStringExtra("PENERBIT"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgBuku.setImageBitmap(bitmap);
            imgBuku.setContentDescription(imgLocation);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_bagikan){
            Intent bagikanBuku = new Intent(Intent.ACTION_SEND);
            bagikanBuku.putExtra(Intent.EXTRA_SUBJECT, tvJudul.getText().toString());
            bagikanBuku.setType("text/plain");
            startActivity(Intent.createChooser(bagikanBuku,"Bagikan buku"));
        }
        return super.onOptionsItemSelected(item);
    }
}
