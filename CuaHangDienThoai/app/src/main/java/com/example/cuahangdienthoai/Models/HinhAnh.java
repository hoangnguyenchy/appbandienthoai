package com.example.cuahangdienthoai.Models;

public class HinhAnh {
    int MaHinhAnh;
    String HinhAnh1;
    int MaSP;

    public int getMaHinhAnh() {
        return MaHinhAnh;
    }

    public void setMaHinhAnh(int maHinhAnh) {
        MaHinhAnh = maHinhAnh;
    }

    public String getHinhAnh1() {
        return HinhAnh1;
    }

    public void setHinhAnh1(String hinhAnh1) {
        HinhAnh1 = hinhAnh1;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public HinhAnh(int maHinhAnh, String hinhAnh1, int maSP) {
        MaHinhAnh = maHinhAnh;
        HinhAnh1 = hinhAnh1;
        MaSP = maSP;
    }
}
