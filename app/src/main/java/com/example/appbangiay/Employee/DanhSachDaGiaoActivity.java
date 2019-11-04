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

public class DanhSachDaGiaoActivity extends AppCompatActivity {

    public static Intent intent;
//    private Button btn_trolai_dsdagiao;
//    private Button btn_Huy_dsdagiao;
    private SearchView sv_Dsdonhangdagiao;
    private ListView lv_Dsdagiaonv;
    private DanhSachDaGiaoAdapter thanhToanAdapter;

    private ArrayList<DanhSachDagiao> danhSachDagiaos;
    private TextView key1, masanpham_dsdagiao_lsv2, size_dsdagiao_lsv2,tensanpham_dsdagiao_lsv2, soluong_dsdagiao_lsv2, tenkhachhang_dsdagiao_lsv2, sodienthoaikhachhang_dsdagiao_lsv2, diachi_dsdagiao_lsv2, tongtien_dsdagiao_lsv2, tinhtrang_dsdagiao_lsv2;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_dagiao_nhanvien_layout);
        masanpham_dsdagiao_lsv2 = findViewById(R.id.masanpham_dsdagiao_lsv2);
        tensanpham_dsdagiao_lsv2 = findViewById(R.id.tensanpham_dsdagiao_lsv2);
        soluong_dsdagiao_lsv2 = findViewById(R.id.soluong_dsdagiao_lsv2);
        tenkhachhang_dsdagiao_lsv2 = findViewById(R.id.tenkhachhang_dsdagiao_lsv2);
        size_dsdagiao_lsv2 = findViewById(R.id.size_dsdagiao_lsv2);
        sodienthoaikhachhang_dsdagiao_lsv2 = findViewById(R.id.sodienthoaikhachhang_dsdagiao_lsv2);
        diachi_dsdagiao_lsv2 = findViewById(R.id.diachi_dsdagiao_lsv2);
        tongtien_dsdagiao_lsv2 = findViewById(R.id.tongtien_dsdagiao_lsv2);
        tinhtrang_dsdagiao_lsv2 = findViewById(R.id.tinhtrang_dsdagiao_lsv2);
        sv_Dsdonhangdagiao = findViewById(R.id.sv_Dsdonhangdagiao);
        //DATABASE
        data = FirebaseDatabase.getInstance().getReference();
        //goi ham
        AnhXa();
        DieuKhien();
        taoAdapters();
        //databaseTT();
        loadData();


    }

    private void taoAdapters() {
        danhSachDagiaos = new ArrayList<>();
        thanhToanAdapter = new DanhSachDaGiaoAdapter(this, R.layout.listview_danhsach_dagiao_nhanvien_layout, danhSachDagiaos);
        lv_Dsdagiaonv.setAdapter(thanhToanAdapter);
    }


    private void AnhXa() {

//        btn_Huy_dsdagiao = (Button) findViewById(R.id.btn_Huy_dsdagiao);
//        btn_trolai_dsdagiao = (Button) findViewById(R.id.btn_trolai_dsdagiao);
        lv_Dsdagiaonv = findViewById(R.id.lv_Dsdagiaonv);

    }

    private void DieuKhien() {
//        btn_Huy_dsdagiao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
//        btn_trolai_dsdagiao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(DanhSachDaGiaoActivity.this, DanhSachDaGiaoQuanLyActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });

        // perform set on query text listener event
        sv_Dsdonhangdagiao.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                Toast.makeText(DanhSachDaGiaoActivity.this, "Enter", Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty() || "".trim().equals(newText)) {
                    letSearch("", false);
                }
                else
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
            ArrayList<DanhSachDagiao> lstDanhSachdagiaoNew = new ArrayList<>();
            for (DanhSachDagiao item : danhSachDagiaos) {
                if (item.getMaDonHang().contains(keyWord) || item.getTenSanPham().contains(keyWord) ||
                        item.getTenKhachHang().contains(keyWord)) {
                    lstDanhSachdagiaoNew.add(item);
                }
            }
            thanhToanAdapter = new DanhSachDaGiaoAdapter(this, R.layout.listview_danhsach_dagiao_nhanvien_layout, lstDanhSachdagiaoNew);
            lv_Dsdagiaonv.setAdapter(thanhToanAdapter);
        } else {
            taoAdapters();
            loadData();
        }
    }

    //them vao firebase
//    private void databaseTT() {
//        String id = data.child("DaGiaoNhanVien").push().getKey();
//        DanhSachDagiao dagiao = new DanhSachDagiao(id,"05", "bistis","5", "40", "giao", "tai", "0961446997","sai gon", 9000000);
//        data.child("DaGiaoNhanVien").child(id).setValue(dagiao);
//    }
    //lay tu farebase
    private void loadData() {
        data.child("DaGiaoNhanVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                danhSachDagiaos.clear();
                DanhSachDagiao dagiao;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    dagiao = ds.getValue(DanhSachDagiao.class);
                    danhSachDagiaos.add(dagiao);
                }
        thanhToanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }


}

