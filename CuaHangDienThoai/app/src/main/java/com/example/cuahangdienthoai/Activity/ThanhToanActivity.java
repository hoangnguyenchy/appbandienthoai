package com.example.cuahangdienthoai.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangdienthoai.API.ApiService;
import com.example.cuahangdienthoai.Adapters.GioHangAdapter;
import com.example.cuahangdienthoai.Models.CTHoaDon;
import com.example.cuahangdienthoai.Models.GioHang;
import com.example.cuahangdienthoai.Models.HoaDon;
import com.example.cuahangdienthoai.R;
import com.example.cuahangdienthoai.Utils.Common;

import org.lucasr.twowayview.TwoWayView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanActivity extends AppCompatActivity {

    EditText edtDiaChi, edtSDT;
    TwoWayView lvGioHang;
    Button btnDatHang;
    TextView tvTongTien;
    GioHangAdapter adapter;
    int tongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        anhXa();
        adapter = new GioHangAdapter(getApplicationContext());
        lvGioHang.setAdapter(adapter);
        for (GioHang gioHang : Common.gioHangList
        ) {
            tongTien += (gioHang.getGiaBan() * gioHang.getSoLuong());
        }
        tvTongTien.setText("Tổng tiền: " + Common.formatMoney(tongTien));
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtDiaChi.getText().toString().equals("") || edtSDT.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Địa chỉ và số điện thoại không được để trống", Toast.LENGTH_LONG).show();
                } else {
                    themHoaDon();
                }
            }
        });
    }

    //ánh xạ
    private void anhXa() {
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSDT);
        lvGioHang = findViewById(R.id.lv_giohang);
        btnDatHang = findViewById(R.id.btnDatHang);
        tvTongTien = findViewById(R.id.tvTongTien);
    }

    //thêm hóa đơn
    private void themHoaDon() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        HoaDon hoaDon = new HoaDon(Common.tenTK, dateFormat.format(date), edtDiaChi.getText().toString(), edtSDT.getText().toString(), 0);
        ApiService.api.addHoaDon(hoaDon).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    if (response.body() > 0) {
                        for (GioHang gioHang : Common.gioHangList
                        ) {
                            CTHoaDon ctHoaDon = new CTHoaDon(gioHang.getMaSP(), gioHang.getSoLuong());
                            ApiService.api.addCTHoaDons(ctHoaDon).enqueue(new Callback<Integer>() {
                                @Override
                                public void onResponse(Call<Integer> call, Response<Integer> response) {
                                    if (response.isSuccessful()) {
                                        Log.e("TAG", "thành công");
                                        return;
                                    }
                                }

                                @Override
                                public void onFailure(Call<Integer> call, Throwable t) {

                                }
                            });
                        }
                        Common.gioHangList.clear();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}