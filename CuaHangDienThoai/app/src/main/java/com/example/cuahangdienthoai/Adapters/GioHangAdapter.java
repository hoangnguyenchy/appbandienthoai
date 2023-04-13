package com.example.cuahangdienthoai.Adapters;

import static com.example.cuahangdienthoai.Utils.Common.gioHangList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.cuahangdienthoai.Models.GioHang;
import com.example.cuahangdienthoai.R;
import com.example.cuahangdienthoai.Utils.Common;

import java.util.ArrayList;

public class GioHangAdapter extends ArrayAdapter<GioHang> {
    Context context;

    public GioHangAdapter(@NonNull Context context) {
        super(context, 0);
        this.context = context;
        if (gioHangList == null) {
            gioHangList = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.img_hinhanh);
        TextView tvTen = convertView.findViewById(R.id.tv_ten);
        TextView tvGia = convertView.findViewById(R.id.tv_gia);
        TextView tvSoLuong = convertView.findViewById(R.id.tv_soluong);
        ImageButton btnGiam = convertView.findViewById(R.id.btn_giam);
        ImageButton btnTang = convertView.findViewById(R.id.btn_tang);
        ImageButton btnXoa = convertView.findViewById(R.id.btn_xoa);
        GioHang gioHang = gioHangList.get(position);
        Glide.with(context).load(gioHang.getHinhAnh()).into(img);
        tvTen.setText(gioHang.getTenSP());
        tvGia.setText(Common.formatMoney(gioHang.getGiaBan()));
        tvSoLuong.setText(gioHang.getSoLuong()+"");
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioHang.setSoLuong(gioHang.getSoLuong() - 1);
                if (gioHang.getSoLuong() <= 0) gioHangList.remove(position);
                notifyDataSetChanged();
            }
        });
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioHang.setSoLuong(gioHang.getSoLuong() + 1);
                notifyDataSetChanged();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioHangList.remove(gioHang);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


}
