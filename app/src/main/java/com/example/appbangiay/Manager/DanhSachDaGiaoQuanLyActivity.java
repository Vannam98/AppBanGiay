package com.example.appbangiay.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.appbangiay.Adapter.DanhSachDaGiaoQuanLyAdapter;
import com.example.appbangiay.R;
import com.example.appbangiay.User.ThanhToanActivity;
import com.example.appbangiay.data_models.DanhSachDaGiaoQuanLy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhSachDaGiaoQuanLyActivity extends AppCompatActivity {
    public static Intent intent;
    private ListView lv_Dsdagiaoqly;
    private SearchView sv_Dsdonhangdagiaoqly;
    private Button btn_trolai_dsvanchuyenqly,btn_Huy_dsvanchuyenqly;
    private DanhSachDaGiaoQuanLyAdapter danhSachDaGiaoQuanLyAdapter;
    private ArrayList<DanhSachDaGiaoQuanLy> danhsachdagiaoquanlys;
    private TextView txt_madonhang_dsdagiao2,txt_size_dsdagiao2, txt_tensanpham_dsdagiao2, txt_soluong_dsdagiao2, txt_tenkhachhang_dsdagiao2, txt_sodienthoaikhachhang_dsdagiao2, txt_diachi_dsdagiao2, txt_tongtien_dsdagiao2, tinhtrang_dsdagiaoqly_lsv2;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_dagiao_quanly_layout);
        txt_madonhang_dsdagiao2 = findViewById(R.id.txt_madonhang_dsdagiao2);
        txt_tensanpham_dsdagiao2 = findViewById(R.id.txt_tensanpham_dsdagiao2);
        txt_soluong_dsdagiao2 = findViewById(R.id.txt_soluong_dsdagiao2);
        txt_size_dsdagiao2 = findViewById(R.id.txt_size_dsdagiao2);
        txt_tenkhachhang_dsdagiao2 = findViewById(R.id.txt_tenkhachhang_dsdagiao2);
        txt_sodienthoaikhachhang_dsdagiao2 = findViewById(R.id.txt_sodienthoaikhachhang_dsdagiao2);
        txt_diachi_dsdagiao2 = findViewById(R.id.txt_diachi_dsdagiao2);
        txt_tongtien_dsdagiao2 = findViewById(R.id.txt_tongtien_dsdagiao2);
        tinhtrang_dsdagiaoqly_lsv2 = findViewById(R.id.tinhtrang_dsdagiaoqly_lsv2);
        sv_Dsdonhangdagiaoqly = findViewById(R.id.sv_Dsdonhangdagiaoqly);
        btn_trolai_dsvanchuyenqly = findViewById(R.id.btn_trolai_dsvanchuyenqly);
        btn_Huy_dsvanchuyenqly = findViewById(R.id.btn_Huy_dsvanchuyenqly);

        //database
        data = FirebaseDatabase.getInstance().getReference();
        //goi ham
        AnhXa();
        DieuKhien();
        taoAdapters();
        loadData();


    }

    private void taoAdapters() {
        danhsachdagiaoquanlys = new ArrayList<>();
        danhSachDaGiaoQuanLyAdapter = new DanhSachDaGiaoQuanLyAdapter(this, R.layout.listview_danhsach_dagiao_quanly_layout, danhsachdagiaoquanlys);
        lv_Dsdagiaoqly.setAdapter(danhSachDaGiaoQuanLyAdapter);
    }


    private void AnhXa() {

        lv_Dsdagiaoqly = findViewById(R.id.lv_Dsdagiaoqly);

    }

    private void DieuKhien() {
        btn_Huy_dsvanchuyenqly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DanhSachDaGiaoQuanLyActivity.this, DanhSachHoanThanhQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);


            }
        });
        btn_trolai_dsvanchuyenqly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DanhSachDaGiaoQuanLyActivity.this, ThanhToanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        // perform set on query text listener event
        sv_Dsdonhangdagiaoqly.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                Toast.makeText(DanhSachDaGiaoQuanLyActivity.this, "Enter", Toast.LENGTH_SHORT).show();
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
    //       ham tim san pham
    private void letSearch(String keyWord, boolean isSearch) {
        if (isSearch) {
            ArrayList<DanhSachDaGiaoQuanLy> lstDanhSachDaGiaoNew = new ArrayList<>();
            for (DanhSachDaGiaoQuanLy item : danhsachdagiaoquanlys) {
                if (item.getMaDonHang().equalsIgnoreCase(keyWord) || item.getTenSanPham().equalsIgnoreCase(keyWord) ||
                        item.getTenKhachHang().equalsIgnoreCase(keyWord)) {
                    lstDanhSachDaGiaoNew.add(item);
                }
            }
            danhSachDaGiaoQuanLyAdapter = new DanhSachDaGiaoQuanLyAdapter(this, R.layout.listview_danhsach_dagiao_quanly_layout, lstDanhSachDaGiaoNew);
            lv_Dsdagiaoqly.setAdapter(danhSachDaGiaoQuanLyAdapter);
        } else {
            taoAdapters();
            loadData();
        }
    }
    //    lay tu farebase
    private void loadData() {
        data.child("donhangdagiaoquanly").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DanhSachDaGiaoQuanLy dagiaoqly;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    dagiaoqly = ds.getValue(DanhSachDaGiaoQuanLy.class);
                    danhsachdagiaoquanlys.add(dagiaoqly);
                }
                danhSachDaGiaoQuanLyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }
}

