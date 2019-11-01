package com.example.appbangiay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.appbangiay.Adapter.DonHangVanChuyenAdapter;
import com.example.appbangiay.data_models.DonHangVanChuyenQuanLy;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManHinhDonHangVanChuyenQuanLyActivity extends AppCompatActivity {

    Button btn_Trolai;

    ListView lstDanhSach;

    SearchView srTimKiem;

    ArrayList<DonHangVanChuyenQuanLy> mangDonHangVanChuyen;
    DonHangVanChuyenAdapter adapterDonHangVanChuyen = null;

    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_donhangvanchuyenquanly_layout);

        btn_Trolai = (Button)findViewById(R.id.btn_Trolai);
        srTimKiem = (SearchView)findViewById(R.id.srTimKiem);
        btn_Trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhDonHangVanChuyenQuanLyActivity.this,ManHinhDonHangXacNhanQuanLyActivity.class);
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
        mData.child("DonHangVanChuyenQuanLy").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DonHangVanChuyenQuanLy donHangVanChuyen = dataSnapshot.getValue(DonHangVanChuyenQuanLy.class);
                mangDonHangVanChuyen.add(new DonHangVanChuyenQuanLy(donHangVanChuyen.id,donHangVanChuyen.maDH,donHangVanChuyen.tenSP,donHangVanChuyen.soLuong,donHangVanChuyen.size,donHangVanChuyen.tenKH,donHangVanChuyen.soDTKH,donHangVanChuyen.diaChiKH,donHangVanChuyen.tongTien,donHangVanChuyen.tinhTrang));
                adapterDonHangVanChuyen.notifyDataSetChanged();

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

        srTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query,true);
                Toast.makeText(ManHinhDonHangVanChuyenQuanLyActivity.this,"Enter",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty() || "".trim().equals(newText)){
                    letSearch("",false);
                }
                return false;
            }
        });
    }

    public void tao(){
        String id = mData.push().getKey();
        DonHangVanChuyenQuanLy donHangVanChuyen = new DonHangVanChuyenQuanLy(id,"sp010","FiLa","1","37","Hà","0789635","Khánh Hòa","350.000VND","Đang giao");
        mData.child("DonHangVanChuyenQuanLy").push().setValue(donHangVanChuyen);
    }


    public void taoAdapter(){
        mangDonHangVanChuyen = new ArrayList<>();
        adapterDonHangVanChuyen = new DonHangVanChuyenAdapter(this,R.layout.listview_donhangvanchuyenquanly_layout,mangDonHangVanChuyen);
        lstDanhSach.setAdapter(adapterDonHangVanChuyen);
    }

    private void AnhXa() {
        lstDanhSach = (ListView)findViewById(R.id.lst_DanhSach);

    }

    public void letSearch(String search, boolean isSearch){
        if (isSearch)
        {
            ArrayList<DonHangVanChuyenQuanLy> donHangVanChuyens = new ArrayList<>();
            for (DonHangVanChuyenQuanLy donHangVC:mangDonHangVanChuyen)
            {
                if (donHangVC.getId().equalsIgnoreCase(search)|| donHangVC.getMaDH().equalsIgnoreCase(search)|| donHangVC.getTenSP().equalsIgnoreCase(search) || donHangVC.getSoLuong().equalsIgnoreCase(search)|| donHangVC.getSize().equalsIgnoreCase(search)||
                        donHangVC.getTenKH().equalsIgnoreCase(search)|| donHangVC.getSoDTKH().equalsIgnoreCase(search)||
                        donHangVC.getDiaChiKH().equalsIgnoreCase(search)|| donHangVC.getTongTien().equalsIgnoreCase(search)||
                        donHangVC.getTinhTrang().equalsIgnoreCase(search))
                {
                    donHangVanChuyens.add(donHangVC);
                }
            }

            adapterDonHangVanChuyen = new DonHangVanChuyenAdapter(this,R.layout.listview_donhangvanchuyenquanly_layout,donHangVanChuyens);
            lstDanhSach.setAdapter(adapterDonHangVanChuyen);
        }
        else {
            taoAdapter();
            LoadData();
        }

    }
}
