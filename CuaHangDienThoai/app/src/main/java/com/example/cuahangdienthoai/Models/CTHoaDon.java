package com.example.cuahangdienthoai.Models;

public class CTHoaDon {
     int MaCTHD;
     int MaHD;
     int MaSP;
     int SoLuong;

    public CTHoaDon(int maSP, int soLuong) {
        MaSP = maSP;
        SoLuong = soLuong;
    }

    public int getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        MaCTHD = maCTHD;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public CTHoaDon(int maCTHD, int maHD, int maSP, int soLuong) {
        MaCTHD = maCTHD;
        MaHD = maHD;
        MaSP = maSP;
        SoLuong = soLuong;
    }
}
