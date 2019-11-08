package com.example.appbangiay.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appbangiay.ManHinhDonHangHuyQuanLyActivity;
import com.example.appbangiay.ManHinhDonHangVanChuyenQuanLyActivity;
import com.example.appbangiay.R;
import com.example.appbangiay.User.LoginActivity;
import com.example.appbangiay.User.MemberActivity;
import com.example.appbangiay.User.NavMainActivity;
import com.example.appbangiay.User.ThanhToanActivity;
import com.google.android.material.navigation.NavigationView;

public class MangHinhChinhQuanLy extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private LinearLayout lnI_dsdhdg_qly , lnI_dsdhhh_qly,lnI_dsttdh_qly,lnI_dshdh_qly,lnI_dsvcdh_qly,lnI_dsxndh_qly;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinhqly_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.manhinhqly_member);
        lnI_dsdhdg_qly = (LinearLayout) findViewById(R.id.lnI_dsdhdg_qly);
        lnI_dsdhhh_qly = (LinearLayout) findViewById(R.id.lnI_dsdhhh_qly);
        lnI_dsttdh_qly = (LinearLayout) findViewById(R.id.lnI_dsttdh_qly);
        lnI_dshdh_qly = (LinearLayout) findViewById(R.id.lnI_dshdh_qly);
        lnI_dsvcdh_qly = (LinearLayout) findViewById(R.id.lnI_dsvcdh_qly);
        lnI_dsxndh_qly = (LinearLayout) findViewById(R.id.lnI_dsxndh_qly);

        actionBarDrawerToggle = new ActionBarDrawerToggle( this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.memberqly);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch(id)
                {
                    case R.id.taonhanvien:
                        Toast.makeText(MangHinhChinhQuanLy.this," Tạo nhân viên" ,Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(getApplicationContext(), DanhSachQuanLyNhanVienActivity.class));
                        return true;
                    case R.id.taosanpham:
                        Toast.makeText(MangHinhChinhQuanLy.this," Tạo sản Phẩm" ,Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(getApplicationContext(), MangerShoe.class));
                        return true;
                    case R.id.logoutqly:
                        Toast.makeText(MangHinhChinhQuanLy.this," Logout" ,Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });

        lnI_dsdhdg_qly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhQuanLy.this, DanhSachDaGiaoQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        lnI_dsdhhh_qly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhQuanLy.this, DanhSachHoanThanhQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        lnI_dsttdh_qly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhQuanLy.this, DanhSachQuanLyNhanVienActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        //huy don hang
        lnI_dshdh_qly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhQuanLy.this, ManHinhDonHangHuyQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        // van chuyen  qly
        lnI_dsvcdh_qly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhQuanLy.this, ManHinhDonHangVanChuyenQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        lnI_dsxndh_qly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhQuanLy.this, DanhSachDaGiaoQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return  true;
        return super.onOptionsItemSelected(item);
    }
}
