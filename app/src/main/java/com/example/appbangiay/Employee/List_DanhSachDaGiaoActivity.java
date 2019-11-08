package com.example.appbangiay.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.appbangiay.R;
import com.example.appbangiay.User.ThanhToanActivity;
import com.example.appbangiay.data_models.DanhSachDagiao;
import com.example.appbangiay.data_models.DonHangHuy;
import com.example.appbangiay.data_models.ThanhToan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class List_DanhSachDaGiaoActivity extends AppCompatActivity {

    public static Intent intent;
    private Button btn_Huy_dsdagiao_lsv;
    private Button btn_xacnhan_dsdagiao_lsv;
    private TextView key1, masanpham_dsdagiao_lsv2, tensanpham_dsdagiao_lsv2, soluong_dsdagiao_lsv2, tenkhachhang_dsdagiao_lsv2, sodienthoaikhachhang_dsdagiao_lsv2, diachi_dsdagiao_lsv2, tongtien_dsdagiao_lsv2, tinhtrang_dsdagiao_lsv2;
    DatabaseReference data;
    private ArrayList<DanhSachDagiao> danhSachDagiao;
    DonHangHuy huyGiaoHang_;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_thanhtoan_layout);
        /*Ánh xạ View Button từ file .xml sang file .java bằng thuộc tính id*/
        btn_Huy_dsdagiao_lsv = (Button) findViewById(R.id.btn_Huy_dsdagiao_lsv);
        btn_xacnhan_dsdagiao_lsv = (Button) findViewById(R.id.btn_xacnhan_dsdagiao_lsv);
        masanpham_dsdagiao_lsv2 = findViewById(R.id.masanpham_dsdagiao_lsv2);
        tensanpham_dsdagiao_lsv2 = findViewById(R.id.tensanpham_dsdagiao_lsv2);
        soluong_dsdagiao_lsv2 = findViewById(R.id.soluong_dsdagiao_lsv2);
        tenkhachhang_dsdagiao_lsv2 = findViewById(R.id.tenkhachhang_dsdagiao_lsv2);
        sodienthoaikhachhang_dsdagiao_lsv2 = findViewById(R.id.sodienthoaikhachhang_dsdagiao_lsv2);
        diachi_dsdagiao_lsv2 = findViewById(R.id.diachi_dsdagiao_lsv2);
        tongtien_dsdagiao_lsv2 = findViewById(R.id.tongtien_dsdagiao_lsv2);
        tinhtrang_dsdagiao_lsv2 = findViewById(R.id.tinhtrang_dsdagiao_lsv2);
        intent = new Intent(this, ThanhToanActivity.class);


//        btn_Huy_dsdagiao_lsv.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View v) {
//                String id = data.child("donhanghuy").push().getKey();
//                DonHangHuy huyhang = new DonHangHuy(id, danhSachDagiao);
//                data.child("donhanghuy").child(id).setValue(huyhang).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        startActivity(intent);
//                    }
//                });
//
//            }
//        });


    }
}
