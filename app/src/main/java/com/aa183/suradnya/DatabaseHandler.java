package com.aa183.suradnya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_buku";
    private final static String TABLE_BUKU = "t_buku";
    private final static String KEY_ID_BUKA = "ID_Buku";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_TGL = "Tanggal";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_PENERBIT = "Penerbit";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUKU = "CREATE TABLE " + TABLE_BUKU
        + "(" + KEY_ID_BUKA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + KEY_JUDUL + " TEXT, " + KEY_TGL + " DATE, "
        + KEY_GAMBAR + " TEXT, " + KEY_PENULIS + " TEXT, "
        + KEY_PENERBIT + " TEXT);";

        db.execSQL(CREATE_TABLE_BUKU);
        inisialisasiBukuAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BUKU;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahBuku(Buku dataBuku){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.getTanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        db.insert(TABLE_BUKU, null, cv);
        db.close();
    }

    public void tambahBuku(Buku dataBuku, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.getTanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        db.insert(TABLE_BUKU, null, cv);
    }

    public void editBuku(Buku dataBuku){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.getTanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());

        db.update(TABLE_BUKU, cv, KEY_ID_BUKA + "=?", new String[]{String.valueOf(dataBuku.getIdBuku())});

        db.close();
    }

    public void hapusBuku(int idBuku){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BUKU, KEY_ID_BUKA + "=?", new String[]{String.valueOf(idBuku)});
        db.close();
    }

    public ArrayList<Buku> getAllBuku(){
        ArrayList<Buku> dataBuku = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BUKU;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()){
            do {
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(2));
                }catch (ParseException er){
                    er.printStackTrace();
                }

                Buku tempBuku = new Buku(
                     csr.getInt(0),
                     csr.getString(1),
                     tempDate,
                     csr.getString(3),
                     csr.getString(4),
                     csr.getString(5)
                );
                dataBuku.add(tempBuku);
            }while (csr.moveToNext());
        }

        return dataBuku;
    }

    private String storeImageFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInteralStorage(image, context);
        return location;
    }

    private void inisialisasiBukuAwal(SQLiteDatabase db){
        int idBuku = 0;
        Date tempDate = new Date();

        //Menambah data Buku ke-1
        try {
            tempDate = sdFormat.parse("24/03/2020");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Buku buku1 = new Buku(
                idBuku,
                "Wanita Bermata Gurita",
                tempDate,
                storeImageFile(R.drawable.buku5),
                "Jemmy Piran",
                "Suka Buku. Pt"
        );
        tambahBuku(buku1, db);
        idBuku++;

        //Menambah data Buku ke-2
        try {
            tempDate = sdFormat.parse("11/06/2019");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Buku buku2 = new Buku(
                idBuku,
                "Fantasteen Deadly Call",
                tempDate,
                storeImageFile(R.drawable.buku6),
                "Sarah Ann",
                "Mizan Media Utama"
        );
        tambahBuku(buku2, db);
        idBuku++;

        //Menambah data Buku ke-3
        try {
            tempDate = sdFormat.parse("03/06/2019");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Buku buku3 = new Buku(
                idBuku,
                "Fantasteen Pure Blood Deluxe",
                tempDate,
                storeImageFile(R.drawable.buku7),
                "Akbar Suganda Jp",
                "Mizan Media Utama"
        );
        tambahBuku(buku3, db);
        idBuku++;

        //Menambah data Buku ke-4
        try {
            tempDate = sdFormat.parse("11/07/2019");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Buku buku4 = new Buku(
                idBuku,
                "Fantasteen The Black Circle",
                tempDate,
                storeImageFile(R.drawable.buku8),
                "Tasyarani Aca",
                "Mizan Media Utama"
        );
        tambahBuku(buku4, db);
        idBuku++;
    }
}
