package com.example.cuahangdienthoai.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.cuahangdienthoai.Activity.ChiTietSanPhamActivity;
import com.example.cuahangdienthoai.Models.SanPham;
import com.example.cuahangdienthoai.R;
import com.example.cuahangdienthoai.Utils.Common;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    List<SanPham> sanPhamList;
    Context context;

    public SanPhamAdapter(@NonNull Context context, List<SanPham> sanPhamList) {
        super(context, 0, sanPhamList);
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sanpham, parent, false);
        }
        ImageView img_hinhanh = convertView.findViewById(R.id.img_hinhanh);
        TextView tv_ten = convertView.findViewById(R.id.tv_ten);
        TextView tv_gia = convertView.findViewById(R.id.tv_gia);
        SanPham sanPham = sanPhamList.get(position);
        if (!sanPham.getHinhAnh().equals("")) {
            Glide.with(context).load(sanPham.getHinhAnh()).into(img_hinhanh);
        }
        tv_ten.setText(sanPham.getTenSP());
        tv_gia.setText(Common.formatMoney(sanPham.getGiaBan()));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.sanPham = sanPham;
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        return convertView;
    }
}
