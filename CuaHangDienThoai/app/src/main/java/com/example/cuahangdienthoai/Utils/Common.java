package com.example.cuahangdienthoai.Utils;

import com.example.cuahangdienthoai.Models.GioHang;
import com.example.cuahangdienthoai.Models.SanPham;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Common {
    public static String baseUrl = "https://othersagerock6.conveyor.cloud/";
    public static List<SanPham> sanPhamList;
    public static SanPham sanPham;
    public static List<GioHang> gioHangList;
    public static String tenTK;
    public static String formatMoney(int money) {
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(money);
    }
}
