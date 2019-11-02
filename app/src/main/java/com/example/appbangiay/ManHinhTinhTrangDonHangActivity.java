package com.example.appbangiay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.appbangiay.Adapter.TinhTrangDonHangAdapter;
import com.example.appbangiay.data_models.TinhTrangDonHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ManHinhTinhTrangDonHangActivity extends AppCompatActivity {

    Button btn_Trove;

    ListView lstDanhSach;

    ArrayList<TinhTrangDonHang> mangSanPham;
    TinhTrangDonHangAdapter adapter = null;

    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_tinhtrangdonhang_layout);
        btn_Trove = (Button)findViewById(R.id.btn_Trove);
        btn_Trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhTinhTrangDonHangActivity.this,ManHinhDonHangHoanThanhNhanVienActivity.class);
                startActivity(intent);
            }
        });

        mData = FirebaseDatabase.getInstance().getReference();

        AnhXa();

        taoAdapter();

        tao();

        LoadData();


    }

    private void LoadData(){
        mData.child("TinhTrangDonHang").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TinhTrangDonHang sanPham = dataSnapshot.getValue(TinhTrangDonHang.class);
                mangSanPham.add(new TinhTrangDonHang(sanPham.id,sanPham.maDH,sanPham.tenDH,sanPham.thongTinDH,sanPham.tinhTrang,sanPham.tongTien,sanPham.ngay));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void tao(){
        String id = mData.push().getKey();
        TinhTrangDonHang tinhTrangDonHang = new TinhTrangDonHang(id,"sp002","Adidas","Đã lưu","Đang giao","200.000VND","9/10/2020");
        mData.child("TinhTrangDonHang").child(id).setValue(tinhTrangDonHang);
    }


    public void taoAdapter(){
        mangSanPham = new ArrayList<>();
        adapter = new TinhTrangDonHangAdapter(this,R.layout.listview_tinhtrangdonhang_layout,mangSanPham);
        lstDanhSach.setAdapter(adapter);
    }

    private void AnhXa() {
        lstDanhSach = (ListView)findViewById(R.id.lst_DanhSach);

    }

}

