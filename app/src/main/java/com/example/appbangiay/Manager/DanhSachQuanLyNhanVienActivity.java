package com.example.appbangiay.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
    private SearchView sv_DSNV;
    private ArrayList<QuanLyNhanVien> quanLyNhanViens;
    private AdapterQuanLyNhanVien adapterQuanLyNhanVien;
    private  Button btn_taoTK;
    private Button btn_Huy;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_quanly_nhanvien_layout);

        //Processing events
        AnhXa();
        run();

        taoAdapter();
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
        sv_DSNV = (SearchView) findViewById(R.id.sv_DSNV);
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



    private void loadData(){
        mData.child("QuanLyNhanVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                quanLyNhanViens.clear();
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

        sv_DSNV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                Toast.makeText(DanhSachQuanLyNhanVienActivity.this, "OK",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty() || "".equals(newText))
                {
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

    public void letSearch(String keyWord, boolean isSearch)
    {
        if(isSearch)
        {
            ArrayList<QuanLyNhanVien> arrNhanVien = new ArrayList<>();
            for(QuanLyNhanVien item : quanLyNhanViens)
            {
                if(item.getMaNhanVien().contains(keyWord) || item.getTenNhanVien().contains(keyWord) || item.getDiaChi().contains(keyWord))
                {
                    arrNhanVien.add(item);
                }
            }
            adapterQuanLyNhanVien = new AdapterQuanLyNhanVien(this, R.layout.listview_danhsach_quanly_nhanvien_layout, arrNhanVien);
            list_QLNV.setAdapter(adapterQuanLyNhanVien);
        }
        else
        {
            taoAdapter();
            loadData();
        }
    }
}

