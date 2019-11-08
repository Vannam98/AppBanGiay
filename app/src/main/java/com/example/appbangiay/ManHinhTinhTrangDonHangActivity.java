package com.example.appbangiay;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appbangiay.Adapter.TinhTrangDonHangAdapter;
import com.example.appbangiay.User.NavMainActivity;
import com.example.appbangiay.User.ThanhToanActivity;
import com.example.appbangiay.data_models.TinhTrangDonHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManHinhTinhTrangDonHangActivity extends AppCompatActivity {

    public static Intent intent;
    private ListView lst_DanhSach;
    private Button btn_Thoat;
    private Button btn_Trove;
    private TinhTrangDonHangAdapter tinhTrangDonHangAdapter;
    private ArrayList<TinhTrangDonHang> tinhTrangDonHangs;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_tinhtrangdonhang_layout);
        //DATABASE
        data = FirebaseDatabase.getInstance().getReference();
        //goi ham
        AnhXa();
        DieuKhien();
        taoAdapters();
        loadData();
    }

    private void taoAdapters() {
        tinhTrangDonHangs = new ArrayList<>();
        tinhTrangDonHangAdapter = new TinhTrangDonHangAdapter(this, R.layout.listview_tinhtrangdonhang_layout, tinhTrangDonHangs);
        lst_DanhSach.setAdapter(tinhTrangDonHangAdapter);
    }

    private void DieuKhien() {
        btn_Trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ManHinhTinhTrangDonHangActivity.this, ThanhToanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ManHinhTinhTrangDonHangActivity.this, NavMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }


    private void AnhXa() {
        btn_Thoat = (Button) findViewById(R.id.btn_Thoat);
        btn_Trove = (Button) findViewById(R.id.btn_Trove);
        lst_DanhSach = findViewById(R.id.lst_DanhSach);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

    }

    //lay tu farebase
    private void loadData() {
        data.child("DatHang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tinhTrangDonHangs.clear();
                TinhTrangDonHang tinhtrang;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    tinhtrang = ds.getValue(TinhTrangDonHang.class);
                    tinhTrangDonHangs.add(tinhtrang);
                }
                tinhTrangDonHangAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
