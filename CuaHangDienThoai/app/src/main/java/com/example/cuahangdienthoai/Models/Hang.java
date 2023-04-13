package com.example.cuahangdienthoai.Models;

public class Hang {
    private int MaHang;
    private String TenHang;
    private String HinhAnh;

    public Hang(int maHang, String tenHang, String hinhAnh) {
        MaHang = maHang;
        TenHang = tenHang;
        HinhAnh = hinhAnh;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getMaHang() {
        return MaHang;
    }

    public void setMaHang(int maHang) {
        MaHang = maHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String tenHang) {
        TenHang = tenHang;
    }
}
