package com.example.appbangiay.Employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbangiay.Adapter.AdapterDonHang;

import com.example.appbangiay.R;
import com.example.appbangiay.User.MemberActivity;
import com.example.appbangiay.data_models.DonHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhSachDonHangActivity extends AppCompatActivity {
    public static Intent intent;
    private ListView list_DSDH;
    private AdapterDonHang donHangAdapter;
    private ArrayList<DonHang> donHangs;
    private SearchView svdanhSachDonHang;
    private Button btn_thanhToan;
    private Button btn_Thoat;
    private TextView txt_maSP, txt_tenSP, txt_soLuong, txt_size, txt_tongTien;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_donhang_layout);
        /*Ánh xạ View Button từ file .xml sang file .java bằng thuộc tính id*/

        svdanhSachDonHang = (SearchView) findViewById(R.id.sv_DSDH);
        AnhXa();
        DieuKhien();
        //intent = new Intent(this, DanhSachXacNhanDonHangActivity.class);
        taoAdapters();
        create();
        loadData();
    }


    private void taoAdapters() {
        donHangs = new ArrayList<>();
        donHangAdapter = new AdapterDonHang(this, R.layout.listview_danhsach_donhang_layout, donHangs);
        list_DSDH.setAdapter(donHangAdapter);
    }

    private void AnhXa() {
        btn_thanhToan = (Button) findViewById(R.id.btn_Thanhtoan);
        btn_Thoat = (Button) findViewById(R.id.btn_Thoat);
        list_DSDH = findViewById(R.id.list_DSDH);

    }

    private void DieuKhien() {
        btn_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DanhSachDonHangActivity.this, DanhSachXacNhanDonHangActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void create() {
        String id1 = mData.push().getKey();
        String id2 = mData.push().getKey();
        String id3 = mData.push().getKey();
        DonHang dh1 = new DonHang(id1, "N59", "Nike", "1", "43", 750000);
        DonHang dh2 = new DonHang(id2, "B31", "Bitis", "3", "40", 2300000);
        DonHang dh3 = new DonHang(id3, "A87", "Adidas", "4", "37", 4500000);

        mData.child("DonHang").child(id1).setValue(dh1);
        mData.child("DonHang").child(id2).setValue(dh2);
        mData.child("DonHang").child(id3).setValue(dh3);


    }

    private void loadData() {
        mData.child("DonHang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DonHang donHang = ds.getValue(DonHang.class);
                    donHangs.add(donHang);
                }
                donHangAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO HERE
            }
        });

        svdanhSachDonHang.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                Toast.makeText(DanhSachDonHangActivity.this, "OK",Toast.LENGTH_SHORT).show();
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
            ArrayList<DonHang> arrDonHang = new ArrayList<>();
            for(DonHang item : donHangs)
            {
                if(item.getMaSanPham().contains(keyWord) || item.getTenSanPham().contains(keyWord))
                {
                    arrDonHang.add(item);
                }
            }
            donHangAdapter = new AdapterDonHang(this, R.layout.listview_danhsach_donhang_layout, arrDonHang);
            list_DSDH.setAdapter(donHangAdapter);
        }
        else
        {
            taoAdapters();
            loadData();
        }
    }
}

