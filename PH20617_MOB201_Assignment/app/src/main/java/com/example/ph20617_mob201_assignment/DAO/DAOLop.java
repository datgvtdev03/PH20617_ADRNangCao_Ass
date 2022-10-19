package com.example.ph20617_mob201_assignment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.ph20617_mob201_assignment.DTO.Lop;
import com.example.ph20617_mob201_assignment.Database.CreateDatabase;

import java.util.ArrayList;

public class DAOLop {
    private final SQLiteDatabase db;

    public DAOLop(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        db = createDatabase.getWritableDatabase();
    }
//    private int maLop;
//    private String tenLop;

    public long insert(Lop lop) {
        ContentValues values = new ContentValues();
        values.put("tenLop", lop.getTenLop());
        return db.insert("Lop", null, values);
    }

    public int update(Lop lop) {
        ContentValues values = new ContentValues();
        values.put("maLop", lop.getTenLop());
        return db.update("Lop", null, "maLop = ?", new String[]{String.valueOf(lop.getMaLop())});
    }

    public int delete(int maLop) {
        return db.delete("Lop", "maLop = ?", new String[]{String.valueOf(maLop)});
    }

    public ArrayList<Lop> getAll() {
        String sql = "SELECT* FROM Lop";
        return getData(sql);
    }

    @SuppressLint("Range")
    public Lop getID(int maLop) {
        String sql = "SELECT* FROM Lop WHERE maLop = ?";
        Lop lop = new Lop();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maLop)});
        if (cursor.moveToFirst()) {
            lop.setMaLop(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLop"))));
            lop.setTenLop(cursor.getString(cursor.getColumnIndex("tenLop")));

        }
        return lop;
    }

    @SuppressLint("Range")
    private ArrayList<Lop> getData(String sql, String... args) {
        ArrayList<Lop> lst = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            Lop lop = new Lop();
            lop.setMaLop(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLop"))));
            lop.setTenLop(cursor.getString(cursor.getColumnIndex("tenLop")));
            lst.add(lop);
        }
        return lst;
    }


}
