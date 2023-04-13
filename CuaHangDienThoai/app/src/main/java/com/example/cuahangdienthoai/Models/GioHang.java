package com.example.cuahangdienthoai.Models;

public class GioHang {
    private int MaSP;
    private String TenSP;
    private String HinhAnh;
    private int GiaBan;
    private int SoLuong;

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int giaBan) {
        GiaBan = giaBan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public GioHang(int maSP, String tenSP, String hinhAnh, int giaBan, int soLuong) {
        MaSP = maSP;
        TenSP = tenSP;
        HinhAnh = hinhAnh;
        GiaBan = giaBan;
        SoLuong = soLuong;
    }
}
