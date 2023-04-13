package com.example.cuahangdienthoai.Activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.cuahangdienthoai.Fragments.GioHangFragment;
import com.example.cuahangdienthoai.Fragments.TienichFragment;
import com.example.cuahangdienthoai.Fragments.TrangChuFragment;
import com.example.cuahangdienthoai.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class ManHinhChinhActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    ViewFlipper viewFlipper;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        anhXa();

        if (savedInstanceState == null)

        {

            chipNavigationBar.setItemSelected(R.id.home, true);

            fragmentManager = getSupportFragmentManager();

            TrangChuFragment homeFragment = new TrangChuFragment();

            fragmentManager.beginTransaction()

                    .replace(R.id.fragment_container, homeFragment)

                    .commit();

        }

        setFrameLayout();
    }

    //ánh xạ
    private void anhXa() {

        chipNavigationBar = findViewById(R.id.bottom_nav);
    }

    //set view fragment
    private void setFrameLayout() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @Override

            public void onItemSelected(int i) {

                Fragment fragment = null;

                switch (i)

                {

                    case R.id.home:

                        fragment = new TrangChuFragment();

                        break;

                    case R.id.giohang:

                        fragment = new GioHangFragment();

                        break;
                    case  R.id.tienich:
                        fragment = new TienichFragment ();

                        break;

                }

                if (fragment != null)

                {

                    fragmentManager = getSupportFragmentManager();

                    fragmentManager.beginTransaction()

                            .replace(R.id.fragment_container, fragment)

                            .commit();

                }

            }

        });
    }


}