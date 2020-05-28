package com.aa183.suradnya;


import java.util.Date;

public class Buku {

    private int idBuku;
    private String judul;
    private Date tanggal;
    private String gambar;
    private String penulis;
    private String penerbit;

    public Buku(int idBuku, String judul, Date tanggal, String gambar, String penulis, String penerbit) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.penulis = penulis;
        this.penerbit = penerbit;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
}
