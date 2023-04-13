package com.example.cuahangdienthoai.Models;

public class HoaDon {
    int MaHD;
    String TenTK;
    String NgayBan;
    String DiaChi;
    String SDT;
    int TrangThai;

    public HoaDon(String tenTK, String ngayBan, String diaChi, String SDT, int trangThai) {
        TenTK = tenTK;
        NgayBan = ngayBan;
        DiaChi = diaChi;
        this.SDT = SDT;
        TrangThai = trangThai;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(String ngayBan) {
        NgayBan = ngayBan;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public HoaDon(int maHD, String tenTK, String ngayBan, String diaChi, String SDT, int trangThai) {
        MaHD = maHD;
        TenTK = tenTK;
        NgayBan = ngayBan;
        DiaChi = diaChi;
        this.SDT = SDT;
        TrangThai = trangThai;
    }
}
