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

import com.example.appbangiay.Adapter.DonHangHuyAdapter;
import com.example.appbangiay.data_models.DonHangHuyQuanLy;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManHinhDonHangHuyQuanLyActivity extends AppCompatActivity {


    Button btn_Trolai;

    ListView lstDanhSach;

    SearchView srTimKiem;

    ArrayList<DonHangHuyQuanLy> mangDonHangHuy;

    DonHangHuyAdapter adapterDonHangHuy = null;

    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_donhanghuyquanly_layout);

        btn_Trolai = (Button)findViewById(R.id.btn_Trolai);
        srTimKiem = (SearchView)findViewById(R.id.srTimKiem);
        btn_Trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhDonHangHuyQuanLyActivity.this,ManHinhDonHangVanChuyenQuanLyActivity.class);
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
        mData.child("DonHangHuyQuanLy").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DonHangHuyQuanLy donHangHuy = dataSnapshot.getValue(DonHangHuyQuanLy.class);
                mangDonHangHuy.add(new DonHangHuyQuanLy(donHangHuy.id,donHangHuy.maDH,donHangHuy.tenSP,donHangHuy.soLuong,donHangHuy.size,donHangHuy.tenKH,donHangHuy.soDTKH,donHangHuy.diaChiKH,donHangHuy.tongTien,donHangHuy.tinhTrang,donHangHuy.lyDoHuy));
                adapterDonHangHuy.notifyDataSetChanged();

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
                Toast.makeText(ManHinhDonHangHuyQuanLyActivity.this,"Enter",Toast.LENGTH_SHORT).show();
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

        DonHangHuyQuanLy donHangHuy = new DonHangHuyQuanLy(id,"sp006","Casin","1","41","Huệ","04589632","TP.HCM","100.000VND","Hủy","Từ chối nhận");
        mData.child("DonHangHuyQuanLy").push().setValue(donHangHuy);
    }

    public void taoAdapter(){

        mangDonHangHuy = new ArrayList<>();
        adapterDonHangHuy = new DonHangHuyAdapter(this,R.layout.listview_danhsachhuyquanly_layout,mangDonHangHuy);
        lstDanhSach.setAdapter(adapterDonHangHuy);
    }

    private void AnhXa() {
        lstDanhSach = (ListView)findViewById(R.id.lst_DanhSach);

    }

    public void letSearch(String search, boolean isSearch){
        if (isSearch)
        {
            ArrayList<DonHangHuyQuanLy> donHangHuys = new ArrayList<>();
            for (DonHangHuyQuanLy donHangHuy:mangDonHangHuy)
            {
                if (donHangHuy.getId().equalsIgnoreCase(search)|| donHangHuy.getMaDH().equalsIgnoreCase(search)|| donHangHuy.getTenSP().equalsIgnoreCase(search) || donHangHuy.getSoLuong().equalsIgnoreCase(search)|| donHangHuy.getSize().equalsIgnoreCase(search)||
                        donHangHuy.getTenKH().equalsIgnoreCase(search)|| donHangHuy.getSoDTKH().equalsIgnoreCase(search)||
                        donHangHuy.getDiaChiKH().equalsIgnoreCase(search)|| donHangHuy.getTongTien().equalsIgnoreCase(search)||
                        donHangHuy.getTinhTrang().equalsIgnoreCase(search) || donHangHuy.getLyDoHuy().equalsIgnoreCase(search))
                {
                    donHangHuys.add(donHangHuy);
                }
            }

            adapterDonHangHuy = new DonHangHuyAdapter(this,R.layout.listview_danhsachhuyquanly_layout,donHangHuys);
            lstDanhSach.setAdapter(adapterDonHangHuy);
        }
        else {
            taoAdapter();
            LoadData();
        }

    }
}
