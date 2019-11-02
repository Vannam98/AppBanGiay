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

import com.example.appbangiay.Adapter.DonHangXacNhanAdapter;
import com.example.appbangiay.data_models.DonHangXacNhanQuanLy;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManHinhDonHangXacNhanQuanLyActivity extends AppCompatActivity {

    Button btn_Trolai;

    ListView lstDanhSach;

    SearchView srTimKiem;

    ArrayList<DonHangXacNhanQuanLy> mangDonHangXacNhan;
    DonHangXacNhanAdapter adapterDonHangXacNhan= null;

    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_donhangxacnhanquanly_layout);

        btn_Trolai = (Button)findViewById(R.id.btn_Trolai);
        srTimKiem = (SearchView)findViewById(R.id.srTimKiem);
        btn_Trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhDonHangXacNhanQuanLyActivity.this,ManHinhTinhTrangDonHangActivity.class);
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
        mData.child("DonHangXacNhanQuanLy").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DonHangXacNhanQuanLy donHangXacNhan = dataSnapshot.getValue(DonHangXacNhanQuanLy.class);
                mangDonHangXacNhan.add(new DonHangXacNhanQuanLy(donHangXacNhan.id,donHangXacNhan.maDH,donHangXacNhan.tenSP,donHangXacNhan.soLuong,donHangXacNhan.size,donHangXacNhan.tenKH,donHangXacNhan.soDTKH,donHangXacNhan.diaChiKH,donHangXacNhan.tongTien,donHangXacNhan.tinhTrang));
                adapterDonHangXacNhan.notifyDataSetChanged();

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
                Toast.makeText(ManHinhDonHangXacNhanQuanLyActivity.this,"OK",Toast.LENGTH_SHORT).show();
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
//        DonHangXacNhanQuanLy donHangXacNhan = new DonHangXacNhanQuanLy(id,"sp006","Box","1","41","Lan","0398678","Bến Tre","700.000VND","Đã giao");
//        mData.child("DonHangXacNhanQuanLy").child(id).setValue(donHangXacNhan);
    }

    public void taoAdapter(){
        mangDonHangXacNhan = new ArrayList<>();
        adapterDonHangXacNhan = new DonHangXacNhanAdapter(this,R.layout.listview_danhsachxacnhanquanly_layout,mangDonHangXacNhan);
        lstDanhSach.setAdapter(adapterDonHangXacNhan);
    }

    private void AnhXa() {
        lstDanhSach = (ListView)findViewById(R.id.lst_DanhSach);

    }

    public void letSearch(String search, boolean isSearch){
        if (isSearch)
        {
            ArrayList<DonHangXacNhanQuanLy> donHangXacNhans = new ArrayList<>();
            for (DonHangXacNhanQuanLy donHangXN:mangDonHangXacNhan)
            {
                if (donHangXN.getId().equalsIgnoreCase(search)|| donHangXN.getMaDH().equalsIgnoreCase(search)|| donHangXN.getTenSP().equalsIgnoreCase(search) || donHangXN.getSoLuong().equalsIgnoreCase(search)|| donHangXN.getSize().equalsIgnoreCase(search)||
                        donHangXN.getTenKH().equalsIgnoreCase(search)|| donHangXN.getSoDTKH().equalsIgnoreCase(search)||
                        donHangXN.getDiaChiKH().equalsIgnoreCase(search)|| donHangXN.getTongTien().equalsIgnoreCase(search)||
                        donHangXN.getTinhTrang().equalsIgnoreCase(search))
                {
                    donHangXacNhans.add(donHangXN);
                }
            }

            adapterDonHangXacNhan = new DonHangXacNhanAdapter(this,R.layout.listview_danhsachxacnhanquanly_layout,donHangXacNhans);
            lstDanhSach.setAdapter(adapterDonHangXacNhan);
        }
        else {
            taoAdapter();
            LoadData();
        }

    }
}
