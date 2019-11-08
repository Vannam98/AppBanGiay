package com.example.appbangiay.Employee;

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

import com.example.appbangiay.Manager.DanhSachDaGiaoQuanLyActivity;
import com.example.appbangiay.Manager.DanhSachHoanThanhQuanLyActivity;
import com.example.appbangiay.Manager.DanhSachTaoNhanVienActivity;
import com.example.appbangiay.Manager.MangerShoe;
import com.example.appbangiay.R;
import com.example.appbangiay.User.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class MangHinhChinhNhanvienActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private LinearLayout lnI_dsdhdg , lnI_dsdhhh,lnI_dshdh,lnI_dsvcdh,lnI_dsxndh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinhnhanvien_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.manhinhnv_member);
        lnI_dsdhdg = (LinearLayout) findViewById(R.id.lnI_dsdhdg);
        lnI_dsdhhh = (LinearLayout) findViewById(R.id.lnI_dsdhhh);
        lnI_dshdh = (LinearLayout) findViewById(R.id.lnI_dshdh);
        lnI_dsvcdh = (LinearLayout) findViewById(R.id.lnI_dsvcdh);
        lnI_dsxndh = (LinearLayout) findViewById(R.id.lnI_dsxndh);

        actionBarDrawerToggle = new ActionBarDrawerToggle( this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.membernv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch(id)
                {
                    case R.id.logout_nv:
                        Toast.makeText(MangHinhChinhNhanvienActivity.this," Logout" ,Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });

        lnI_dsdhdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhNhanvienActivity.this, DanhSachDaGiaoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        //hoan thanh nv
        lnI_dsdhhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhNhanvienActivity.this, DanhSachHoanThanhQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        lnI_dshdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhNhanvienActivity.this, DanhSachHuyDonHangActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        lnI_dsvcdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhNhanvienActivity.this, VanChuyenNhanVienActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        lnI_dsxndh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangHinhChinhNhanvienActivity.this, DanhSachXacNhanDonHangActivity.class);
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
