package com.example.cuahangdienthoai.Models;

public class SanPham {
    private int MaSP;
    private String TenSP;
    private int SoLuong;
    private int GiaBan;
    private String HinhAnh;
    private String MoTa;
    private String TenHang;

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

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int giaBan) {
        GiaBan = giaBan;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }


    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String tenHang) {
        TenHang = tenHang;
    }

    public SanPham(int maSP, String tenSP, int soLuong, int giaBan, String hinhAnh, String moTa, String tenHang) {
        MaSP = maSP;
        TenSP = tenSP;
        SoLuong = soLuong;
        GiaBan = giaBan;
        HinhAnh = hinhAnh;
        MoTa = moTa;

        TenHang = tenHang;
    }
}
