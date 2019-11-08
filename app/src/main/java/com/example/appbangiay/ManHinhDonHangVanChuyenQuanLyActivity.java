package com.example.appbangiay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbangiay.Adapter.DonHangHoanThanhNhanVienAdapter;
import com.example.appbangiay.Adapter.DonHangVanChuyenAdapter;
import com.example.appbangiay.User.NavMainActivity;
import com.example.appbangiay.data_models.DonHangHoanThanhNhanVien;
import com.example.appbangiay.data_models.DonHangVanChuyenQuanLy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManHinhDonHangVanChuyenQuanLyActivity extends AppCompatActivity {

    public static Intent intent;
    private Button btn_Trolai;
    private SearchView srTimKiem;
    private ListView lstDanhsach;
    private DonHangVanChuyenAdapter donHangVanChuyenAdapter;
    private ArrayList<DonHangVanChuyenQuanLy> donHangVanChuyenQuanLys;
    private TextView txt_Madonhang, txt_Tensanpham, txt_Soluong, txt_Size,txt_Tenkhachhang,txt_SodienthoaiKH,
            txt_DiachiKH,txt_Tongtien,txt_Tinhtrang;

    DatabaseReference data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_donhangvanchuyenquanly_layout);
        txt_Madonhang = findViewById(R.id.txt_Madonhang);
        txt_Tensanpham = findViewById(R.id.txt_Tensanpham);
        txt_Soluong = findViewById(R.id.txt_Soluong);
        txt_Size = findViewById(R.id.txt_Size);
        txt_Tenkhachhang = findViewById(R.id.txt_Tenkhachhang);
        txt_SodienthoaiKH = findViewById(R.id.txt_SodienthoaiKH);
        txt_DiachiKH = findViewById(R.id.txt_Diachi);
        txt_Tongtien = findViewById(R.id.txt_Tongtien);
        txt_Tinhtrang = findViewById(R.id.txt_Tinhtrang);
        srTimKiem = findViewById(R.id.srTimKiem);
        data = FirebaseDatabase.getInstance().getReference();

        AnhXa();
        DieuKhien();
        taoAdapters();
        loadData();
    }

    private void taoAdapters() {
        donHangVanChuyenQuanLys = new ArrayList<>();
        donHangVanChuyenAdapter = new DonHangVanChuyenAdapter(this, R.layout.listview_donhangvanchuyenquanly_layout, donHangVanChuyenQuanLys);
        lstDanhsach.setAdapter(donHangVanChuyenAdapter);
    }

    private void AnhXa() {
        btn_Trolai = (Button) findViewById(R.id.btn_Trolai);
        lstDanhsach = findViewById(R.id.lst_DanhSach);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

    }

    private void DieuKhien() {
        btn_Trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ManHinhDonHangVanChuyenQuanLyActivity.this, NavMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        // perform set on query text listener event
        srTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                Toast.makeText(ManHinhDonHangVanChuyenQuanLyActivity.this, "Enter", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty() || "".trim().equals(newText)) {
                    letSearch("", false);
                }
                return false;
            }
        });
    }
    //ham tim san pham
    private void letSearch(String keyWord, boolean isSearch) {
        if (isSearch) {
            ArrayList<DonHangVanChuyenQuanLy> lstDanhSachVanChuyenNew = new ArrayList<>();
            for (DonHangVanChuyenQuanLy item : donHangVanChuyenQuanLys ) {
                if (item.getMaDonHang().equalsIgnoreCase(keyWord) || item.getTenSanPham().equalsIgnoreCase(keyWord)
                        ||item.getTenKhachHang().equalsIgnoreCase(keyWord) || item.getTinhTrang().equalsIgnoreCase(keyWord)) {
                    lstDanhSachVanChuyenNew.add(item);
                }
            }
            donHangVanChuyenAdapter = new DonHangVanChuyenAdapter(this, R.layout.listview_donhangvanchuyenquanly_layout, lstDanhSachVanChuyenNew);
            lstDanhsach.setAdapter(donHangVanChuyenAdapter);
        } else {
            taoAdapters();
            loadData();
        }
    }

    //lay tu farebase
    private void loadData() {
        data.child("VanChuyenQuanLy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donHangVanChuyenQuanLys.clear();
                DonHangVanChuyenQuanLy vanchuyen;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    vanchuyen = ds.getValue(DonHangVanChuyenQuanLy.class);
                    donHangVanChuyenQuanLys.add(vanchuyen);
                }
                donHangVanChuyenAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
