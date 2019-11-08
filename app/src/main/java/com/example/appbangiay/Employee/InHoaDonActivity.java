package com.example.appbangiay.Employee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.VanChuyenNhanVien;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class InHoaDonActivity extends AppCompatActivity {

    public static Intent intent;
    private Button btn_giao_hoadon;
    private Button btn_thoat_hoadon;
    private Button btn_huy_hoadon;
    VanChuyenNhanVien vanchuyen;
    TextView madonhang_hoadon, tensanpham_hoadon, size_hoadon, tenkhachhang_hoadon, sdtkhachhang_hoadon, diachi_hoadon, tongtien_hoadon, nguoigiao_hoadon, sdtNguoiGiao_hoadon;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_hoadon_nhanvien_layout);
        madonhang_hoadon = findViewById(R.id.madonhang_hoadon);
        tensanpham_hoadon = findViewById(R.id.tensanpham_hoadon);
        size_hoadon = findViewById(R.id.size_hoadon);
        tenkhachhang_hoadon = findViewById(R.id.tenkhachhang_hoadon);
        sdtkhachhang_hoadon = findViewById(R.id.sdtkhachhang_hoadon);
        diachi_hoadon = findViewById(R.id.diachi_hoadon);
        tongtien_hoadon = findViewById(R.id.tongtien_hoadon);
        btn_giao_hoadon = findViewById(R.id.btn_giao_hoadon);
        btn_thoat_hoadon = findViewById(R.id.btn_thoat_hoadon);
        btn_huy_hoadon = findViewById(R.id.btn_huy_hoadon);

        //DATABASE
        data = FirebaseDatabase.getInstance().getReference();

//        databaseTT();
        DieuKhien();
//        String id = "";
//        loadData(id);
        getDataFromIntent();
    }



    private void getDataFromIntent(){
        intent = getIntent();
         vanchuyen = intent.getParcelableExtra("DonHang");
        madonhang_hoadon.setText(vanchuyen.getMaDonHang());
        tensanpham_hoadon.setText(vanchuyen.getTenSanPham());
        size_hoadon.setText(vanchuyen.getSize());
        tenkhachhang_hoadon.setText(vanchuyen.getTenKhachHang());
        sdtkhachhang_hoadon.setText(vanchuyen.getSoDienThoai());
        diachi_hoadon.setText(vanchuyen.getDiaChi());
        tongtien_hoadon.setText(String.valueOf(vanchuyen.getTongtien()));

    }

    private void AnhXa() {
        btn_huy_hoadon = (Button) findViewById(R.id.btn_huy_hoadon);
        btn_thoat_hoadon = (Button) findViewById(R.id.btn_thoat_hoadon);
        btn_giao_hoadon = findViewById(R.id.btn_giao_hoadon);

    }

    private void DieuKhien() {
        btn_thoat_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btn_huy_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(InHoaDonActivity.this, DanhSachNguoiGiaoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btn_giao_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(InHoaDonActivity.this, DanhSachDaGiaoActivity.class);
                Toast.makeText(InHoaDonActivity.this, "Xac Nhan Giao", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                xacNhanDonHang(vanchuyen);
            }
        });
    }
    public void xacNhanDonHang(VanChuyenNhanVien value) {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        //Them du lieu vao don hang da giao
        String id = data.child("DaGiaoNhanVien").push().getKey();
        data.child("DaGiaoNhanVien").child(value.getId()).setValue(value);
    }
    //laasy du lieu firebase
//    private void loadData(String id)
//    {
//        data.child("XacNhanVanchuyen").child(id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                XacNhanVanChuyen xacnhan = dataSnapshot.getValue(XacNhanVanChuyen.class);
//                madonhang_hoadon.setText(xacnhan.getMaDonHang());
//                tensanpham_hoadon.setText(xacnhan.getTenKhachHang());
//                size_hoadon.setText(xacnhan.getSize());
//                tenkhachhang_hoadon.setText(xacnhan.getTenKhachHang());
//                sdtkhachhang_hoadon.setText(xacnhan.getSoDienThoai());
//                diachi_hoadon.setText(xacnhan.getDiaChi());
//                tongtien_hoadon.setText(String.valueOf(xacnhan.getTongtien()));
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

}
