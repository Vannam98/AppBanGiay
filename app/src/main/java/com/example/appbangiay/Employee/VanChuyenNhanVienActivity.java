package com.example.appbangiay.Employee;
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


import com.example.appbangiay.Adapter.DanhSachDaGiaoAdapter;
import com.example.appbangiay.Adapter.VanChuyenNhanVienAdapter;
import com.example.appbangiay.Manager.DanhSachDaGiaoQuanLyActivity;
import com.example.appbangiay.R;
import com.example.appbangiay.User.ThanhToanActivity;
import com.example.appbangiay.data_models.DanhSachDagiao;
import com.example.appbangiay.data_models.VanChuyenNhanVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VanChuyenNhanVienActivity extends AppCompatActivity{
    public static Intent intent;
    private SearchView sv_Dsvanchuyen;
    private ListView lv_Dsvanchuyen;
    private VanChuyenNhanVienAdapter vanChuyenNhanVienAdapter;
    private ArrayList<VanChuyenNhanVien> vanChuyenNhanViens;
    TextView txt_madonhang_vanchuyennv2, txt_tensanpham_vanchuyennv2, txt_soluong_vanchuyennv2,txt_size_vanchuyennv2,txt_tinhtrang_vanchuyennv2,txt_tenkhachhang_vanchuyennv2,txt_sodienthoai_vanchuyennv2,txt_diachikhachhang_vanchuyennv2,txt_tongtien_vanchuyen;
    Button btn_xacnhan_dsvanchuyen, btn_Thoat_dsvanchuyen;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_vanchuyen_nhanvien_layout);
        txt_madonhang_vanchuyennv2 = findViewById(R.id.txt_madonhang_vanchuyennv2);
        txt_tensanpham_vanchuyennv2 = findViewById(R.id.txt_tensanpham_vanchuyennv2);
        sv_Dsvanchuyen = findViewById(R.id.sv_Dsvanchuyen);
        txt_soluong_vanchuyennv2 = findViewById(R.id.txt_soluong_vanchuyennv2);
        txt_size_vanchuyennv2 = findViewById(R.id.txt_size_vanchuyennv2);
        txt_tinhtrang_vanchuyennv2 = findViewById(R.id.txt_tinhtrang_vanchuyennv2);
        txt_tenkhachhang_vanchuyennv2 = findViewById(R.id.txt_tenkhachhang_vanchuyennv2);
        txt_sodienthoai_vanchuyennv2 = findViewById(R.id.txt_sodienthoai_vanchuyennv2);
        txt_diachikhachhang_vanchuyennv2 = findViewById(R.id.txt_diachikhachhang_vanchuyennv2);
        txt_tongtien_vanchuyen = findViewById(R.id.txt_tongtien_vanchuyen);
        //DATABASE
        data = FirebaseDatabase.getInstance().getReference();
        //goi ham
        AnhXa();
        DieuKhien();
        taoAdapters();
        databaseTT();
        loadData();



    }
    private void taoAdapters() {
        vanChuyenNhanViens = new ArrayList<>();
        vanChuyenNhanVienAdapter = new VanChuyenNhanVienAdapter(this, R.layout.listview_vanchuyen_nhanvien_layout, vanChuyenNhanViens);
        lv_Dsvanchuyen.setAdapter(vanChuyenNhanVienAdapter);
    }


    private void AnhXa() {

        btn_Thoat_dsvanchuyen = (Button) findViewById(R.id.btn_Thoat_dsvanchuyen);
        btn_xacnhan_dsvanchuyen = (Button) findViewById(R.id.btn_xacnhan_dsvanchuyen);
        lv_Dsvanchuyen = findViewById(R.id.lv_Dsvanchuyen);

    }
    private void DieuKhien() {
        btn_Thoat_dsvanchuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btn_xacnhan_dsvanchuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(VanChuyenNhanVienActivity.this, DanhSachNguoiGiaoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        // perform set on query text listener event
        sv_Dsvanchuyen.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty() || "".trim().equals(newText)) {
                    letSearch("", false);
                }else
                {
                    letSearch(newText, true);
                }
                return false;
            }
        });
    }
    //ham tim san pham
    private void letSearch(String keyWord, boolean isSearch) {
        if (isSearch) {
            ArrayList<VanChuyenNhanVien> lstDanhSachvanchuyenNew = new ArrayList<>();
            for (VanChuyenNhanVien item : vanChuyenNhanViens) {
                if (item.getMaDonHang().contains(keyWord) || item.getTenSanPham().contains(keyWord) ||
                        item.getTenKhachHang().contains(keyWord)) {
                    lstDanhSachvanchuyenNew.add(item);
                }
            }
            vanChuyenNhanVienAdapter = new VanChuyenNhanVienAdapter(this, R.layout.listview_vanchuyen_nhanvien_layout, lstDanhSachvanchuyenNew);
            lv_Dsvanchuyen.setAdapter(vanChuyenNhanVienAdapter);
        } else {
            taoAdapters();
           loadData();
        }
   }

    //them vao firebase
    private void databaseTT() {
        String id = data.child("Vanchuyennhanvien").push().getKey();
        VanChuyenNhanVien vanchuyen = new VanChuyenNhanVien(id,"01","bitis","5","40","chua xac nhan","long","0961446997","saigon",100000);
        data.child("Vanchuyennhanvien").child(id).setValue(vanchuyen);
    }
    //lay tu farebase
    private void loadData() {
        data.child("Vanchuyennhanvien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vanChuyenNhanViens.clear();
                VanChuyenNhanVien vanchuyen;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    vanchuyen = ds.getValue(VanChuyenNhanVien.class);
                    vanChuyenNhanViens.add(vanchuyen);
                }
                vanChuyenNhanVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }





}
