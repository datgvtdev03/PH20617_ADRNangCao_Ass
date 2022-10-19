package com.example.ph20617_mob201_assignment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    public CreateDatabase(@Nullable Context context) {
        super(context, "AsmMOB201.db", null,2 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String bangSinhVien = "CREATE TABLE SinhVien(" +
                "maSv INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSv TEXT NOT NULL," +
                "maKH INTEGER NOT NULL REFERENCES KhoaHoc(maKH)," +
                "maLop INTEGER NOT NULL REFERENCES Lop(maLop)," +
                "namSinh TEXT NOT NULL)";
        db.execSQL(bangSinhVien);
        String bangLop = "CREATE TABLE Lop(" +
                "maLop INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLop TEXT NOT NULL)";
        db.execSQL(bangLop);
        String bangKhoaHoc = "CREATE TABLE KhoaHoc(" +
                "maKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenKH TEXT NOT NULL," +
                "soGioHoc TEXT NOT NULL)";
        db.execSQL(bangKhoaHoc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SinhVien");
        db.execSQL("DROP TABLE IF EXISTS Lop");
        db.execSQL("DROP TABLE IF EXISTS KhoaHoc");
        onCreate(db);

    }
}
