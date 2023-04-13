package com.example.cuahangdienthoai.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangdienthoai.API.ApiService;
import com.example.cuahangdienthoai.R;
import com.example.cuahangdienthoai.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhXa();
        findViewById(R.id.btnDangKy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
            }
        });
        findViewById(R.id.btnDangNhap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoan = edtTaiKhoan.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                ProgressDialog dialog = new ProgressDialog(DangNhapActivity.this);
                dialog.setTitle("Đang đăng nhập");
                dialog.show();
                ApiService.api.dangNhap(taiKhoan, matKhau).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            if (response.body()) {
                                Common.tenTK = taiKhoan;
                                startActivity(new Intent(getApplicationContext(), ManHinhChinhActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    //ánh xạ điều khiển
    private void anhXa() {
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
    }
}