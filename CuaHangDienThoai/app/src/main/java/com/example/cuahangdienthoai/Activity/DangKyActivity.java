package com.example.cuahangdienthoai.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangdienthoai.API.ApiService;
import com.example.cuahangdienthoai.Models.TaiKhoan;
import com.example.cuahangdienthoai.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau, edtSDT, edtDiaChi, edtHoTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhXa();
        findViewById(R.id.btnDangKy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenTK = edtTaiKhoan.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String sdt = edtSDT.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                if (tenTK.equals("") || matKhau.equals("") || sdt.equals("") || diaChi.equals("") || hoTen.equals("")) {
                    Toast.makeText(getApplicationContext(), "Các thông tin không được để trống", Toast.LENGTH_LONG).show();
                } else {
                    ProgressDialog dialog = new ProgressDialog(DangKyActivity.this);
                    dialog.setTitle("Đang đăng ký");
                    dialog.show();
                    TaiKhoan taiKhoan = new TaiKhoan(tenTK, matKhau, hoTen, diaChi, sdt);
                    ApiService.api.dangKy(taiKhoan).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if (response.body() != null) {
                                dialog.dismiss();
                                if (response.body() > 0) {

                                    finish();
                                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Không thành công", Toast.LENGTH_LONG).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Không thành công", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }

    //ãnh xạ điều khiển
    private void anhXa() {
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtSDT = findViewById(R.id.edtSDT);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtHoTen = findViewById(R.id.edtHoTen);
    }
}