package com.example.cuahangdienthoai.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.cuahangdienthoai.R;

import java.util.List;

public class HangLoaiAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> list;
    String[] color;

    public HangLoaiAdapter(@NonNull Context context, List<String> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        color = new String[]{"#FFBB86FC", "#FF6200EE", "#FF3700B3", "#FF03DAC5", "#FF018786", "#FF000000", "#F57C00", "#689F38", "#0097A7", "#D32F2F"};
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hang_loai, parent, false);
        }
        ImageView tv_ten = convertView.findViewById(R.id.tv_ten);
        Glide.with(context).load(list.get(position)).error(R.drawable.ic_remove).into(tv_ten);
        return convertView;
    }
}
