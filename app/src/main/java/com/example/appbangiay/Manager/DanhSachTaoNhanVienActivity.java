package com.example.appbangiay.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.data_models.QuanLyNhanVien;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.QuanLyNhanVien;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class DanhSachTaoNhanVienActivity extends AppCompatActivity {

    Button btn_Tao, btn_Huy;
    EditText edt_tenNV,edt_soDT,edt_email, edt_ngaySinh,edt_password,edt_diaChi;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_taonhanvien_quanly_layout);

        getViews();
        //Processing events
        control();



    }

    private void getViews(){
        btn_Tao = (Button) findViewById(R.id.btn_Tao);
        btn_Huy = (Button) findViewById(R.id.btn_Huy);
        edt_tenNV = (EditText) findViewById(R.id.edt_tenNV);
        edt_soDT = (EditText) findViewById(R.id.edt_soDT);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_ngaySinh = (EditText) findViewById(R.id.edt_ngaySinh);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_diaChi = (EditText) findViewById(R.id.edt_diaChi);
    }

    private void control(){
        btn_Tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgURL = "https://firebasestorage.googleapis.com/v0/b/appbangiay-ee914.appspot.com/o/users-vector-icon-png_260862.jpg?alt=media&token=4073c922-ce30-4c2c-b723-e57b0d257070";
                int idNV = new Random().nextInt();
                QuanLyNhanVien nv1 = new QuanLyNhanVien("0","NV-"+idNV, edt_tenNV.getText().toString()
                        ,edt_soDT.getText().toString(),
                        edt_ngaySinh.getText().toString(), edt_email.getText().toString(), "NhanVien",imgURL,edt_password.getText().toString(),edt_diaChi.getText().toString());
                createUser(nv1);
                Intent intent = new Intent(DanhSachTaoNhanVienActivity.this, DanhSachQuanLyNhanVienActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachTaoNhanVienActivity.this,DanhSachQuanLyNhanVienActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    private void createUser(QuanLyNhanVien user){ //Hàm tạo nhân viên
        String id = mData.child("QuanLyNhanVien").push().getKey();
        user.setId(id);
        mData.child("QuanLyNhanVien").child(id).setValue(user);
    }

}

