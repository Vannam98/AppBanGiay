package com.example.appbangiay.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Adapter.ThanhToanAdapter;
import com.example.appbangiay.Adapter.ThanhToanKhachHangAdapter;
import com.example.appbangiay.Employee.DanhSachDaGiaoActivity;
import com.example.appbangiay.Employee.DanhSachDonHangActivity;
import com.example.appbangiay.Employee.VanChuyenNhanVienActivity;
import com.example.appbangiay.Manager.DanhSachDaGiaoQuanLyActivity;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DatHang;
import com.example.appbangiay.data_models.ThanhToan;
import com.example.appbangiay.data_models.ThanhToanKhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {

    public static Intent intent;
    private Button btn_huy_thanhtoan;
    private Button btn_dathang_thanhtoan, btnchinhsua;
    private ListView listview_manghinh_thanhtoan;
    private ThanhToanAdapter thanhToanAdapter;
    private ThanhToanKhachHangAdapter thanhToanKhachHangAdapter;
    private ArrayList<ThanhToan> thanhToan_s;
    private ArrayList<ThanhToanKhachHang> thanhToan2_models;
    private TextView txt_tensanpham_lsv, txt_gia_lsv, txt_masp_lsv,txt_size_lsv2,txt_tongtien_thanhtoan2;
    private EditText edt_soluong_lsv, edt_tenkhachhang_thanhtoan, edt_sodienthoai_thanhtoan, edt_diachigiao_thanhtoan, edt_phivanchuyen_thanhtoan;
    DatabaseReference data;
    ThanhToanKhachHang thongTinThanhToan;
    DatHang datHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_thanhtoan_layout);
        txt_tensanpham_lsv = findViewById(R.id.txt_tensanpham_lsv);
        txt_gia_lsv = findViewById(R.id.txt_gia_lsv);
        txt_masp_lsv = findViewById(R.id.txt_masp_lsv);
        txt_size_lsv2 = findViewById(R.id.txt_size_lsv2);
        edt_soluong_lsv = findViewById(R.id.edt_soluong_lsv);
        edt_tenkhachhang_thanhtoan = findViewById(R.id.edt_tenkhachhang_thanhtoan);
        edt_sodienthoai_thanhtoan = findViewById(R.id.edt_sodienthoai_thanhtoan);
        edt_diachigiao_thanhtoan = findViewById(R.id.edt_diachigiao_thanhtoan);
        txt_tongtien_thanhtoan2 = findViewById(R.id.txt_tongtien_thanhtoan2);
        btnchinhsua = findViewById(R.id.btnchinhsua);
        btn_dathang_thanhtoan = findViewById(R.id.btn_dathang_thanhtoan);
        btn_huy_thanhtoan = findViewById(R.id.btn_huy_thanhtoan);

        //database
        data = FirebaseDatabase.getInstance().getReference();

        /*Ánh xạ View Button từ file .xml sang file .java bằng thuộc tính id*/
        AnhXa();
        DieuKhien();
        intent = new Intent(this, DanhSachDonHangActivity.class);
        taoAdapters();
//        databaseTT();
        //databaseTT2();

        loadData();
        loadData2();


    }

    @Override
    protected void onResume() {
        intent = getIntent();
        double tongTien = intent.getDoubleExtra("tongTien",0);
        txt_tongtien_thanhtoan2.setText(tongTien+"");
        super.onResume();
    }

    //    private void write(Object data, String thanhToanNo){
//        DatabaseReference get = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference my = get.child("thanhtoan/" + thanhToanNo);
//        my.setValue(data);
//    }
    private void taoAdapters(){
        thanhToan_s = new ArrayList<>();
        thanhToanAdapter = new ThanhToanAdapter(this,R.layout.listview_thanhtoan_layout, thanhToan_s);
        listview_manghinh_thanhtoan.setAdapter(thanhToanAdapter);
    }
    private void AnhXa(){
        btn_huy_thanhtoan = (Button) findViewById(R.id.btn_huy_thanhtoan);
        btn_dathang_thanhtoan = (Button) findViewById(R.id.btn_dathang_thanhtoan);
        listview_manghinh_thanhtoan = findViewById(R.id.listview_manghinh_thanhtoan);

    }

    private void DieuKhien(){
        btn_huy_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ThanhToanActivity.this, DanhSachDaGiaoQuanLyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        //setdathang
        btn_dathang_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = data.child("DatHang").push().getKey();
                DatHang dathang = new DatHang(id,thongTinThanhToan, thanhToan_s);
                data.child("DatHang").child(id).setValue(dathang).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        intent.setClass(ThanhToanActivity.this, VanChuyenNhanVienActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ThanhToanActivity.this, "Đã xảy ra sự cố khi đặt hàng", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
// sua thong tin
        btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongTinThanhToan.setDiachi(edt_diachigiao_thanhtoan.getText().toString());
                thongTinThanhToan.setSodienthoai(edt_sodienthoai_thanhtoan.getText().toString());
                thongTinThanhToan.setTenkhachhang(edt_tenkhachhang_thanhtoan.getText().toString());
                editCustommer(thongTinThanhToan.getId(), thongTinThanhToan);
            }
        });

    }
    //ham sua thoong tin
    private void editCustommer(String idCus, ThanhToanKhachHang thanhToan2_model){
        data.child("ThongTinThanhToan").child(idCus).setValue(thanhToan2_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ThanhToanActivity.this, "Đã thay đổi thông tin", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ThanhToanActivity.this, "Đã xảy ra sự cố trong qua trình thay đổi thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //them firebase
//    private void databaseTT(){
//        String id = data.child("thanhtoan").push().getKey();
//        ThanhToan thanhtoan = new ThanhToan(id,"02","nike","5","40",10000);
//        data.child("thanhtoan").child(id).setValue(thanhtoan);
//    }
    //lay du lieu firebase
    private void loadData(){
        data.child("DonHang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                thanhToan_s.clear();
                ThanhToan thanhToan;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    thanhToan = ds.getValue(ThanhToan.class);
                    thanhToan_s.add(thanhToan);
                }
                thanhToanAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }
    //add leen firebase
    private void databaseTT2(){
        String id = data.child("thongtinthanhtoan").push().getKey();
        ThanhToanKhachHang thongtinthanhtoan = new ThanhToanKhachHang(id,"long","0961446997","saigon","50000",100000);
        data.child("thongtinthanhtoan").child(id).setValue(thongtinthanhtoan);
    }
    //laasy du lieu firebase
    private void loadData2(){
        data.child("thongtinthanhtoan").child("-LrlPsyZdclqaNCctHqR").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ThanhToanKhachHang thanhToan2;
                thanhToan2 = dataSnapshot.getValue(ThanhToanKhachHang.class);
                thongTinThanhToan = thanhToan2;
                edt_tenkhachhang_thanhtoan.setText(thanhToan2.getTenkhachhang());
                edt_sodienthoai_thanhtoan.setText(thanhToan2.getSodienthoai());
                edt_diachigiao_thanhtoan.setText(thanhToan2.getDiachi());
                txt_tongtien_thanhtoan2.setText(String.valueOf(thanhToan2.getTongtien()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }



}

