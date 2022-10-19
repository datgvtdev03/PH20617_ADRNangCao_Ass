package com.example.ph20617_mob201_assignment.DTO;

public class KhoaHoc {
    private int maKH;
    private String tenKH;
    private int soGioHoc;

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSoGioHoc() {
        return soGioHoc;
    }

    public void setSoGioHoc(int soGioHoc) {
        this.soGioHoc = soGioHoc;
    }

    public KhoaHoc(int maKH, String tenKH, int soGioHoc) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soGioHoc = soGioHoc;
    }

    public KhoaHoc() {
    }
}
