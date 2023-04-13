package com.example.cuahangdienthoai.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuahangdienthoai.Activity.ThanhToanActivity;
import com.example.cuahangdienthoai.Adapters.GioHangAdapter;
import com.example.cuahangdienthoai.R;
import com.example.cuahangdienthoai.Utils.Common;


public class GioHangFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_hang, container, false);
    }

    GioHangAdapter adapter;
    ListView lv;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.lv_sanpham);
        adapter = new GioHangAdapter(getContext());
        lv.setAdapter(adapter);
        view.findViewById(R.id.btnDatHang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Common.gioHangList != null) {
                    startActivity(new Intent(getContext(), ThanhToanActivity.class));
                }
            }
        });
    }
}