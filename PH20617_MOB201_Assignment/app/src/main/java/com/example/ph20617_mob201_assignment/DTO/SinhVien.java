package com.example.ph20617_mob201_assignment.DTO;

public class SinhVien {
    private int maSv;
    private String tenSv;
    private int maLop;
    private int maKH;
    private String namSinh;

    public int getMaSv() {
        return maSv;
    }

    public void setMaSv(int maSv) {
        this.maSv = maSv;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public SinhVien(int maSv, String tenSv, int maLop, int maKH, String namSinh) {
        this.maSv = maSv;
        this.tenSv = tenSv;
        this.maLop = maLop;
        this.maKH = maKH;
        this.namSinh = namSinh;
    }

    public SinhVien() {
    }
}
