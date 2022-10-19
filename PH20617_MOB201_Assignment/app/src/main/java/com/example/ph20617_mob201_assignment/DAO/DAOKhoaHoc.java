package com.example.ph20617_mob201_assignment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.ph20617_mob201_assignment.DTO.KhoaHoc;
import com.example.ph20617_mob201_assignment.Database.CreateDatabase;

import java.util.ArrayList;

public class DAOKhoaHoc {

    private final SQLiteDatabase db;

    public DAOKhoaHoc(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        db = createDatabase.getWritableDatabase();
    }
//    private int maKH;
//    private String tenKH;
//    private String soGioHoc;

    public long insert(KhoaHoc khoaHoc) {
        ContentValues values = new ContentValues();
        values.put("tenKH", khoaHoc.getTenKH());
        values.put("soGioHoc", khoaHoc.getSoGioHoc());
        return db.insert("KhoaHoc", null, values);
    }

    public int update(KhoaHoc khoaHoc) {
        ContentValues values = new ContentValues();
        values.put("tenKH", khoaHoc.getTenKH());
        values.put("soGioHoc", khoaHoc.getSoGioHoc());
        return db.update("KhoaHoc", null, "maKH = ?", new String[]{String.valueOf(khoaHoc.getMaKH())});
    }

    public int delete(int maKH) {
        return db.delete("KhoaHoc", "maKH = ?", new String[]{String.valueOf(maKH)});
    }

    public ArrayList<KhoaHoc> getAll() {
        String sql = "SELECT* FROM KhoaHoc";
        return getData(sql);
    }

    @SuppressLint("Range")
    public KhoaHoc getID(int maKH) {
        String sql = "SELECT* FROM KhoaHoc WHERE maKH = ?";
        KhoaHoc khoaHoc = new KhoaHoc();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maKH)});
        if (cursor.moveToFirst()) {
            khoaHoc.setMaKH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            khoaHoc.setTenKH(cursor.getString(cursor.getColumnIndex("tenKH")));
            khoaHoc.setSoGioHoc(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soGioHoc"))));

        }
        return khoaHoc;
    }

    @SuppressLint("Range")
    public ArrayList<KhoaHoc> getData(String sql, String... args) {
        ArrayList<KhoaHoc> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            KhoaHoc khoaHoc = new KhoaHoc();
            khoaHoc.setMaKH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            khoaHoc.setTenKH(cursor.getString(cursor.getColumnIndex("tenKH")));
            khoaHoc.setSoGioHoc(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soGioHoc"))));
            list.add(khoaHoc);
        }
        return list;
    }
}
