package com.example.appbangiay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.data_models.DonHangHoanThanhNhanVien;
import com.example.appbangiay.data_models.HuyGiaoHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class List_DonHangHoanThanhNhanVienActivity extends AppCompatActivity {

    public static Intent intent;
    private Button btn_Huy;
    private Button btn_Hoanthanh;
    private TextView key1,txt_Madonhang, txt_Tensanpham, txt_Soluong, txt_Size,txt_Tenkhachhang,txt_SodienthoaiKH,
            txt_DiachiKH,txt_Tongtien,txt_Tinhtrang;
    DatabaseReference data;
    private ArrayList<DonHangHoanThanhNhanVien> donHangHoanThanh;
    HuyGiaoHang huyGiaoHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_donhanghoanthanhnhanvien_layout);
        btn_Huy = (Button) findViewById(R.id.btn_Huy);
        btn_Hoanthanh = (Button)findViewById(R.id.btn_Hoanthanh);
        txt_Madonhang = findViewById(R.id.txt_Madonhang);
        txt_Tensanpham = findViewById(R.id.txt_Tensanpham);
        txt_Soluong = findViewById(R.id.txt_Soluong);
        txt_Size = findViewById(R.id.txt_Size);
        txt_Tenkhachhang = findViewById(R.id.txt_Tenkhachhang);
        txt_SodienthoaiKH = findViewById(R.id.txt_SodienthoaiKH);
        txt_DiachiKH = findViewById(R.id.txt_DiachiKH);
        txt_Tongtien = findViewById(R.id.txt_Tongtien);
        txt_Tinhtrang = findViewById(R.id.txt_Tinhtrang);
        intent = new Intent(this,ManHinhTinhTrangDonHangActivity.class);

        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = data.child("DonHangHuyNhanVien").push().getKey();
                HuyGiaoHang huyhang = new HuyGiaoHang(id,donHangHoanThanh);
                data.child("DonHangHuyNhanVien").child(id).setValue(huyhang).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(intent);
                    }
                });
            }
        });
    }
}

