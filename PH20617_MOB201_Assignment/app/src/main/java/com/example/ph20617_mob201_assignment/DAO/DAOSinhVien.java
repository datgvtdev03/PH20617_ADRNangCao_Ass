package com.example.ph20617_mob201_assignment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.ph20617_mob201_assignment.DTO.SinhVien;
import com.example.ph20617_mob201_assignment.Database.CreateDatabase;

import java.util.ArrayList;

public class DAOSinhVien {
    private final SQLiteDatabase db;

    public DAOSinhVien(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        db = createDatabase.getWritableDatabase();
    }

    public long insert(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put("tenSv", sinhVien.getTenSv());
        values.put("maLop", sinhVien.getMaLop());
        values.put("maKH", sinhVien.getMaKH());
        values.put("namSinh", sinhVien.getNamSinh());
        return db.insert("SinhVien", null, values);
    }

    public int update(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put("tenSv", sinhVien.getTenSv());
        values.put("maLop", sinhVien.getMaLop());
        values.put("maKH", sinhVien.getMaKH());
        values.put("namSinh", sinhVien.getNamSinh());
        return db.update("SinhVien", null, "maSv = ?", new String[]{String.valueOf(sinhVien.getMaSv())});
    }

    public int delete(int maSv) {
        return db.delete("SinhVien", "maSv = ?", new String[]{String.valueOf(maSv)});
    }

    public ArrayList<SinhVien> getAll() {
        String sql = "SELECT* FROM SinhVien";
        return getData(sql);
    }

    @SuppressLint("Range")
    public SinhVien getID(int maSv) {
        String sql = "SELECT* FROM SinhVien WHERE maSv = ?";
        SinhVien sinhVien = new SinhVien();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maSv)});
        if (cursor.moveToFirst()) {
            sinhVien.setMaSv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSv"))));
            sinhVien.setTenSv(cursor.getString(cursor.getColumnIndex("tenSv")));
            sinhVien.setMaLop(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLop"))));
            sinhVien.setMaKH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            sinhVien.setNamSinh(cursor.getString(cursor.getColumnIndex("namSinh")));
        }
        return sinhVien;
    }

    @SuppressLint("Range")
    private ArrayList<SinhVien> getData(String sql, String... args) {
        ArrayList<SinhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            SinhVien sinhVien = new SinhVien();
            sinhVien.setMaSv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSv"))));
            sinhVien.setTenSv(cursor.getString(cursor.getColumnIndex("tenSv")));
            sinhVien.setMaLop(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLop"))));
            sinhVien.setMaKH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            sinhVien.setNamSinh(cursor.getString(cursor.getColumnIndex("namSinh")));
            list.add(sinhVien);
        }
        return list;
    }

}
