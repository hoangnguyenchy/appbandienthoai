package com.example.cuahangdienthoai.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cuahangdienthoai.API.ApiService;
import com.example.cuahangdienthoai.Activity.DanhSachSanPhamActivity;
import com.example.cuahangdienthoai.Adapters.HangLoaiAdapter;
import com.example.cuahangdienthoai.Adapters.SanPhamAdapter;
import com.example.cuahangdienthoai.Models.Hang;
import com.example.cuahangdienthoai.Models.SanPham;
import com.example.cuahangdienthoai.R;
import com.example.cuahangdienthoai.Utils.Common;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    TwoWayView lv_hang;
    GridView lv_sanpham;
    HangLoaiAdapter hangAdapter;
    HangLoaiAdapter loaiAdapter;
    List<String> hangList = new ArrayList<>();
    List<Hang> hangs = new ArrayList<>();
    SanPhamAdapter sanPhamAdapter;
    List<SanPham> sanPhamList = new ArrayList<>();
    EditText edtTimKiem;
    ViewFlipper viewFlipper;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        ActionViewFlipper ();
        loadDS();
        edtTimKiem = view.findViewById(R.id.edtTimKiem);
        edtTimKiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String timKiem = edtTimKiem.getText().toString();
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    Common.sanPhamList = new ArrayList<>();
                    for (SanPham sanPham : sanPhamList
                    ) {
                        if (sanPham.getTenSP().toLowerCase().contains(timKiem.toLowerCase()) ||
                                sanPham.getTenHang().toLowerCase().contains(timKiem.toLowerCase())) {
                            Common.sanPhamList.add(sanPham);
                        }
                    }
                    startActivity(new Intent(getContext(), DanhSachSanPhamActivity.class));
                }
                return false;
            }
        });
    }

    //ánh xạ
    private void anhXa(View view) {
        lv_hang = view.findViewById(R.id.lv_hang);
        lv_sanpham = view.findViewById(R.id.lv_sanpham);
        hangAdapter = new HangLoaiAdapter(getContext(), hangList);
        lv_hang.setAdapter(hangAdapter);
        sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhamList);
        lv_sanpham.setAdapter(sanPhamAdapter);
        viewFlipper=view.findViewById (R.id.viewlipper);
    }

    //load DS
    private void loadDS() {
        lv_hang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hang hang = hangs.get(i);
                ApiService.api.getSanPhamTheoHang(hang.getMaHang()).enqueue(new Callback<List<SanPham>>() {
                    @Override
                    public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                        if (response.body() != null) {
                            Common.sanPhamList = response.body();
                            startActivity(new Intent(getContext(), DanhSachSanPhamActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SanPham>> call, Throwable t) {

                    }
                });
            }
        });
        ApiService.api.getHangs().enqueue(new Callback<List<Hang>>() {
            @Override
            public void onResponse(Call<List<Hang>> call, Response<List<Hang>> response) {
                if (response.body() != null) {

                    for (Hang hang : response.body()
                    ) {
                        hangs.add(hang);
                        hangList.add(hang.getHinhAnh());
                        hangAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Hang>> call, Throwable t) {

            }
        });

        ApiService.api.getSanPhams().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.body() != null) {
                    for (SanPham sanPham : response.body()
                    ) {
                        sanPhamList.add(sanPham);
                        sanPhamAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }
    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<> ();
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getActivity ());
            Glide.with(getActivity ()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in= AnimationUtils.loadAnimation(getActivity (),R.anim.slide_in_right);
        Animation slide_out= AnimationUtils.loadAnimation(getActivity (),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
}