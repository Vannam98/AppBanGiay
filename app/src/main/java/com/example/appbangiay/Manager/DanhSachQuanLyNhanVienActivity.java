package com.example.appbangiay.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Adapter.AdapterQuanLyNhanVien;
import com.example.appbangiay.Employee.DanhSachDonHangActivity;
import com.example.appbangiay.data_models.QuanLyNhanVien;
import com.example.appbangiay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhSachQuanLyNhanVienActivity extends AppCompatActivity {

    public  static Intent intent;
    private ListView list_QLNV;
    private ArrayList<QuanLyNhanVien> quanLyNhanViens;
    private AdapterQuanLyNhanVien adapterQuanLyNhanVien;
    private  Button btn_taoTK;
    private Button btn_Huy;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_quanly_nhanvien_layout);

//        ListView listView = (ListView) findViewById(R.id.list);
        //Get view from layout

        //Processing events
        AnhXa();
        run();

        //intent = new Intent(this, DanhSachTaoNhanVienActivity.class);
        taoAdapter();
//        create();
        loadData();
    }


    private void taoAdapter()
    {
        quanLyNhanViens = new ArrayList<>();
        adapterQuanLyNhanVien = new AdapterQuanLyNhanVien(this, R.layout.listview_danhsach_quanly_nhanvien_layout, quanLyNhanViens);
        list_QLNV.setAdapter(adapterQuanLyNhanVien);
    }

    private void AnhXa()
    {
        btn_taoTK = (Button) findViewById(R.id.btn_taoTK);
        btn_Huy = (Button) findViewById(R.id.btn_Huy);
        list_QLNV = findViewById(R.id.list_QLNV);
    }

    private void run()
    {
        btn_taoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachQuanLyNhanVienActivity.this, DanhSachTaoNhanVienActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachQuanLyNhanVienActivity.this, DanhSachDonHangActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }



    private void create()
    {
//        String id1 = mData.push().getKey();
//        String id2 = mData.push().getKey();
//        String id3 = mData.push().getKey();
//        String id4 = mData.push().getKey();
//        String imgURL1 = "https://firebasestorage.googleapis.com/v0/b/project-t2512.appspot.com/o/animal-animal-photography-cat-1576193.jpg?alt=media&token=9140f33c-be63-40dd-b2b9-780039b738c6";
//        String imgURL2 = "https://firebasestorage.googleapis.com/v0/b/project-t2512.appspot.com/o/adorable-animal-cat-730896.jpg?alt=media&token=00847f18-d34b-43e8-9fbf-4fe199ee0403";
//        String imgURL3 = "https://firebasestorage.googleapis.com/v0/b/project-t2512.appspot.com/o/adorable-animal-anxious-669015.jpg?alt=media&token=0acc0066-b5da-48b5-9dc6-8b6d574e66e9";
//        String imgURL4 = "https://firebasestorage.googleapis.com/v0/b/project-t2512.appspot.com/o/animal-cat-cute-126407.jpg?alt=media&token=23426310-0459-4d64-944c-646bb159693c";
//
//        QuanLiNhanVien nv1 = new QuanLiNhanVien(id1,"N51","Nam","032695142", "Quan9", "Nam@gmail.com", "NhanVien",imgURL1);
//        QuanLiNhanVien nv2 = new QuanLiNhanVien(id2,"L12","Long","0963127921", "Quan4", "Long@gmail.com", "QuanLyKho",imgURL2);
//        QuanLiNhanVien nv3 = new QuanLiNhanVien(id3,"T63","Thư","0914213671", "Quan10", "Thu@gmail.com", "ThuQuy",imgURL3);
//        QuanLiNhanVien nv4 = new QuanLiNhanVien(id4,"X27","Xuân","0932147632", "Quan2", "Xuan@gmail.com", "NhanVien",imgURL4);
//
//        mData.child("QuanLyNhanVien").child(id1).setValue(nv1);
//        mData.child("QuanLyNhanVien").child(id2).setValue(nv2);
//        mData.child("QuanLyNhanVien").child(id3).setValue(nv3);
//        mData.child("QuanLyNhanVien").child(id4).setValue(nv4);
    }

    private void loadData(){
        mData.child("QuanLyNhanVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuanLyNhanVien QLNV;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    QLNV = ds.getValue(QuanLyNhanVien.class);
                    quanLyNhanViens.add(QLNV);
                }
                adapterQuanLyNhanVien.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }
}

