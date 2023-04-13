package com.example.cuahangdienthoai.Models;

public class TaiKhoan {
    private String TenTK;
    private String MatKhau;
    private String HoTen;
    private String DiaChi;
    private String SDT;

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
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

    public TaiKhoan(String tenTK, String matKhau, String hoTen, String diaChi, String SDT) {
        TenTK = tenTK;
        MatKhau = matKhau;
        HoTen = hoTen;
        DiaChi = diaChi;
        this.SDT = SDT;
    }
}
