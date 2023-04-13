package com.example.cuahangdienthoai.Activity;

import static com.example.cuahangdienthoai.Utils.Common.gioHangList;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.cuahangdienthoai.API.ApiService;
import com.example.cuahangdienthoai.Models.GioHang;
import com.example.cuahangdienthoai.Models.HinhAnh;
import com.example.cuahangdienthoai.R;
import com.example.cuahangdienthoai.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    TextView tvTen, tvGiaBan, tvSoLuong, tvHang, tvMota;
    Button btnThemGioHang;
    ImageSlider imgHinhAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        anhXa();
        loadDuLieu();
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GioHang gioHang = new GioHang(Common.sanPham.getMaSP(), Common.sanPham.getTenSP(), Common.sanPham.getHinhAnh(), Common.sanPham.getGiaBan(), 1);
                themGioHang(gioHang);
            }
        });
    }
    List<SlideModel> slideModels = new ArrayList<>();
    //load dữ liệu
    private void loadDuLieu() {
        if (Common.sanPham != null) {
            tvTen.setText(Common.sanPham.getTenSP());
            tvGiaBan.setText(Common.formatMoney(Common.sanPham.getGiaBan()));
            tvSoLuong.setText("Số lượng: " + Common.sanPham.getSoLuong());

            tvHang.setText("Hãng sản xuất: " + Common.sanPham.getTenHang());
            tvMota.setText(Common.sanPham.getMoTa());
            slideModels.add(new SlideModel(Common.sanPham.getHinhAnh(), ScaleTypes.CENTER_INSIDE));
            ApiService.api.getHinhAnhs(Common.sanPham.getMaSP()).enqueue(new Callback<List<HinhAnh>>() {
                @Override
                public void onResponse(Call<List<HinhAnh>> call, Response<List<HinhAnh>> response) {
                    if (response.body() != null) {
                        for (HinhAnh hinhAnh : response.body()
                        ) {
                            slideModels.add(new SlideModel(hinhAnh.getHinhAnh1(), ScaleTypes.CENTER_INSIDE));
                        }
                        imgHinhAnh.setImageList(slideModels);
                    }

                }

                @Override
                public void onFailure(Call<List<HinhAnh>> call, Throwable t) {

                }
            });
        }
    }

    //ánh xạ
    private void anhXa() {
        tvTen = findViewById(R.id.tv_ten);
        tvGiaBan = findViewById(R.id.tv_gia);
        tvSoLuong = findViewById(R.id.tv_soluong);

        tvHang = findViewById(R.id.tv_hang);
        tvMota = findViewById(R.id.tv_mota);
        btnThemGioHang = findViewById(R.id.btnThemGioHang);
        imgHinhAnh = findViewById(R.id.img_hinhanh);
    }

    //giỏ hàng
    private void themGioHang(GioHang gioHang) {
        //nếu giỏ hàng rỗng thì tạo mới, thêm sản phẩm vào giỏ hàng
        if (gioHangList == null) {
            gioHangList = new ArrayList<>();
            gioHangList.add(gioHang);
            dialog("Thêm thành công");
            Toast.makeText(ChiTietSanPhamActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
        } else {
            //kiểm tra tồn tại
            int kt = ktTonTai(gioHang.getMaSP());
            //nếu sản phẩm đã tồn tại thì tăng số lượng trogn giỏ hang lên 1
            if (kt > -1) {
                GioHang gh = gioHangList.get(kt);
                if (gh.getSoLuong() + 1 <= Common.sanPham.getSoLuong()) {
                    gh.setSoLuong(gh.getSoLuong() + 1);
                    gioHangList.set(kt, gh);
                    dialog("Thêm thành công");
                    Toast.makeText(ChiTietSanPhamActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                } else {
                    dialog("Số lượng tối đa");
                    Toast.makeText(ChiTietSanPhamActivity.this, "Số lượng sản phẩm tối đa", Toast.LENGTH_LONG).show();
                }
                //nếu không tồn tại sản phẩm trong giỏ hàng thì thêm sp vào giỏ hàng
            } else {
                gioHangList.add(gioHang);
                dialog("Thêm thành công");
                Toast.makeText(ChiTietSanPhamActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int ktTonTai(int maSP) {
        GioHang model = null;
        for (GioHang gioHang : gioHangList
        ) {
            if (gioHang.getMaSP() == maSP) model = gioHang;
        }
        return gioHangList.indexOf(model);
    }

    private void dialog(String noidung) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietSanPhamActivity.this);
        builder.setTitle(noidung);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }



}