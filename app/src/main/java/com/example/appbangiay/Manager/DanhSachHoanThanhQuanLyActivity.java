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
import com.example.appbangiay.Adapter.DanhSachHoanThanhQuanLyAdapter;
import com.example.appbangiay.R;
import com.example.appbangiay.User.ThanhToanActivity;
import com.example.appbangiay.data_models.DanhSachDaGiaoQuanLy;
import com.example.appbangiay.data_models.DanhSachHoanThanhQuanLy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DanhSachHoanThanhQuanLyActivity extends AppCompatActivity {

    public static Intent intent;
    private ListView lsv_dshoanthanhqly;
    private SearchView sv_dshoanthanhqly;
    private Button btn_thoat_dshoanthanhqly,btn_trolai_dshoanthanhqly;
    private DanhSachHoanThanhQuanLyAdapter danhSachHoanThanhQuanLyAdapter;
    private ArrayList<DanhSachHoanThanhQuanLy> danhSachHoanThanhQuanLys;
    TextView txtdate_dshoanthanhqly;
    TextView txt_madonhang_dsdonhanghoanthanh1,txt_tensanpham_dsdonhanghoanthanh1,txt_tenkhachhang_dsdonhanghoanthanh1,txt_sodienthoaikhachhang_dsdonhanghoanthanh1,txt_diachikhachhang_dsdonhanghoanthanh1,txt_nguoigiao_dsdonhanghoanthanh1,txt_sodienthoainguoigiao_dsdonhanghoanthanh1,txt_tongtien_dsdonhanghoanthanh1,txt_tinhtrang_dsdonhanghoanthanh1  ;

    DatabaseReference data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_hoanthanh_quanly_layout);
        txt_madonhang_dsdonhanghoanthanh1 = findViewById(R.id.txt_madonhang_dsdonhanghoanthanh1);
        txt_tensanpham_dsdonhanghoanthanh1 = findViewById(R.id.txt_tensanpham_dsdonhanghoanthanh1);
        txt_tenkhachhang_dsdonhanghoanthanh1 = findViewById(R.id.txt_tenkhachhang_dsdonhanghoanthanh1);
        txt_sodienthoaikhachhang_dsdonhanghoanthanh1 = findViewById(R.id.txt_sodienthoaikhachhang_dsdonhanghoanthanh1);
        txt_diachikhachhang_dsdonhanghoanthanh1 = findViewById(R.id.txt_diachikhachhang_dsdonhanghoanthanh1);
        txt_nguoigiao_dsdonhanghoanthanh1 = findViewById(R.id.txt_nguoigiao_dsdonhanghoanthanh1);
        txt_sodienthoainguoigiao_dsdonhanghoanthanh1 = findViewById(R.id.txt_sodienthoainguoigiao_dsdonhanghoanthanh1);
        txt_tongtien_dsdonhanghoanthanh1 = findViewById(R.id.txt_tongtien_dsdonhanghoanthanh1);
        txt_tinhtrang_dsdonhanghoanthanh1 = findViewById(R.id.txt_tinhtrang_dsdonhanghoanthanh1);
        txtdate_dshoanthanhqly = findViewById(R.id.txtdate_dshoanthanhqly);
        btn_thoat_dshoanthanhqly = findViewById(R.id.btn_thoat_dshoanthanhqly);
        btn_trolai_dshoanthanhqly = findViewById(R.id.btn_trolai_dshoanthanhqly);
        sv_dshoanthanhqly = findViewById(R.id.sv_dshoanthanhqly);

        //database
        data = FirebaseDatabase.getInstance().getReference();

        AnhXa();
        DieuKhien();
        taoAdapters();
//        databaseTT();
        loadData();
        loaddate();
    }

    private void taoAdapters() {
        danhSachHoanThanhQuanLys = new ArrayList<>();
        danhSachHoanThanhQuanLyAdapter = new DanhSachHoanThanhQuanLyAdapter(this, R.layout.listview_danhsach_hoanthanh_quanly_layout, danhSachHoanThanhQuanLys);
        lsv_dshoanthanhqly.setAdapter(danhSachHoanThanhQuanLyAdapter);
    }

    private void AnhXa() {

        lsv_dshoanthanhqly = findViewById(R.id.lsv_dshoanthanhqly);

    }

    private void DieuKhien() {
        btn_thoat_dshoanthanhqly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }
        });
        btn_trolai_dshoanthanhqly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DanhSachHoanThanhQuanLyActivity.this, ThanhToanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        // perform set on query text listener event
        sv_dshoanthanhqly.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                Toast.makeText(DanhSachHoanThanhQuanLyActivity.this, "Enter", Toast.LENGTH_SHORT).show();
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
            ArrayList<DanhSachHoanThanhQuanLy> lstDanhSachhoanthanhNew = new ArrayList<>();
            for (DanhSachHoanThanhQuanLy item : danhSachHoanThanhQuanLys) {
                if (item.getMaDonHang().equalsIgnoreCase(keyWord) || item.getTenSanPham().equalsIgnoreCase(keyWord) ||
                        item.getTenKhachHang().equalsIgnoreCase(keyWord)) {
                    lstDanhSachhoanthanhNew.add(item);
                }
            }
            danhSachHoanThanhQuanLyAdapter = new DanhSachHoanThanhQuanLyAdapter(this, R.layout.listview_danhsach_hoanthanh_quanly_layout, lstDanhSachhoanthanhNew);
            lsv_dshoanthanhqly.setAdapter(danhSachHoanThanhQuanLyAdapter);
        } else {
            taoAdapters();
            loadData();
        }

    }

    // add ngay hien tai
    private void loaddate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        txtdate_dshoanthanhqly.setText(currentDateandTime);
    }



    //them vao firebase
    private void databaseTT() {
        String id = data.child("DanhSachHoanThanh").push().getKey();
        DanhSachHoanThanhQuanLy hoanthanh = new DanhSachHoanThanhQuanLy(id,"01", "Nike","Tai","0961446997","Saigon","Namshipper","0788551997","dagiao",10000);
        data.child("DanhSachHoanThanh").child(id).setValue(hoanthanh);
    }

    //lay tu farebase
    private void loadData() {
        data.child("DonHangHoanThanhQuanLy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                danhSachHoanThanhQuanLys.clear();
                DanhSachHoanThanhQuanLy hoanthanh;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    hoanthanh = ds.getValue(DanhSachHoanThanhQuanLy.class);
                    danhSachHoanThanhQuanLys.add(hoanthanh);
                }
                danhSachHoanThanhQuanLyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
