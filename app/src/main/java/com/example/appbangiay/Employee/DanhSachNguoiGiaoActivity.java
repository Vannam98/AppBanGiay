package com.example.appbangiay.Employee;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.appbangiay.Adapter.DanhSachNguoiGiaoAdapter;
import com.example.appbangiay.Manager.DanhSachDaGiaoQuanLyActivity;
import com.example.appbangiay.R;
import com.example.appbangiay.User.ThanhToanActivity;
import com.example.appbangiay.data_models.DanhSachDagiao;
import com.example.appbangiay.data_models.DanhSachNguoiGiao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhSachNguoiGiaoActivity extends AppCompatActivity {


    public static Intent intent;
    private Button btn_xacnhan_dsnguoigiao;
    private Button btn_thoat_dsnguoigiao;
    private ListView lv_Dsnguoigiao;
    private DanhSachNguoiGiaoAdapter danhSachNguoiGiaoAdapter;

    private ArrayList<DanhSachNguoiGiao> danhSachNguoiGiaos;
    TextView txt_hoten_dsnguoigiao_lsv2, txt_sodienthoai_dsnguoigiao_lsv2, txt_tinhtrang_dsnguoigiao_lsv2;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_nguoigiao_nhanvien_layout);
        txt_hoten_dsnguoigiao_lsv2 = findViewById(R.id.txt_hoten_dsnguoigiao_lsv2);
        txt_sodienthoai_dsnguoigiao_lsv2 = findViewById(R.id.txt_sodienthoai_dsnguoigiao_lsv2);
        txt_tinhtrang_dsnguoigiao_lsv2 = findViewById(R.id.txt_tinhtrang_dsnguoigiao_lsv2);
        //DATABASE
        data = FirebaseDatabase.getInstance().getReference();


        AnhXa();
        DieuKhien();
        taoAdapters();
        databaseTT();
        loadData();

    }

    private void taoAdapters() {
        danhSachNguoiGiaos = new ArrayList<>();
        danhSachNguoiGiaoAdapter = new DanhSachNguoiGiaoAdapter(this, R.layout.listview_danhsach_nguoigiao_layout, danhSachNguoiGiaos);
        lv_Dsnguoigiao.setAdapter(danhSachNguoiGiaoAdapter);
    }


    private void AnhXa() {

        btn_xacnhan_dsnguoigiao = (Button) findViewById(R.id.btn_xacnhan_dsnguoigiao);
        btn_thoat_dsnguoigiao = (Button) findViewById(R.id.btn_thoat_dsnguoigiao);
        lv_Dsnguoigiao = findViewById(R.id.lv_Dsnguoigiao);

    }

    private void DieuKhien() {
        btn_thoat_dsnguoigiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btn_xacnhan_dsnguoigiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DanhSachNguoiGiaoActivity.this, DanhSachDaGiaoQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }


    //them vao firebase
    private void databaseTT() {
        String id = data.child("DanhSachNguoiGiao").push().getKey();
        DanhSachNguoiGiao nguoiGiao = new DanhSachNguoiGiao(id,"long", "123445678", "rãnh");
        data.child("DanhSachNguoiGiao").child(id).setValue(nguoiGiao);
    }
    //lay tu farebase
    private void loadData() {
        data.child("DanhSachNguoiGiao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                danhSachNguoiGiaos.clear();
                DanhSachNguoiGiao nguoigiao;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    nguoigiao = ds.getValue(DanhSachNguoiGiao.class);
                    danhSachNguoiGiaos.add(nguoigiao);
                }
                danhSachNguoiGiaoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }
}
