package com.example.appbangiay.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Adapter.AdapterHuy;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.DonHangHuy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhSachHuyDonHangActivity extends AppCompatActivity {
    public static Intent intent;
    private ListView list_DSHuy;
    private AdapterHuy donHangHuyAdapter;
    private ArrayList<DonHangHuy> donHangHuys;
    private Button btn_OK;
    private Button btn_Thoat;
    private SearchView sv_DSH;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_huy_nhanvien_layout);

//        ListView listView = (ListView) findViewById(R.id.list);
        //Get view from layout

        //Processing events
        AnhXa();
        run();

        //intent = new Intent(this, DanhSachTaoNhanVienActivity.class);
        taoAdapter();
        //create();
        loadData();

    }


    private void taoAdapter()
    {
        donHangHuys = new ArrayList<>();
        donHangHuyAdapter = new AdapterHuy(this, R.layout.listview_danhsach_huy_donhang_layout, donHangHuys);
        list_DSHuy.setAdapter(donHangHuyAdapter);
    }

    private void AnhXa()
    {
        btn_OK = (Button) findViewById(R.id.btn_OK);
        btn_Thoat = (Button) findViewById(R.id.btn_Thoat);
        list_DSHuy = findViewById(R.id.list_DSHuy);
        sv_DSH = (SearchView) findViewById(R.id.sv_DSH);
    }

    private void run()
    {
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachHuyDonHangActivity.this, DanhSachDonHangActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachHuyDonHangActivity.this, DanhSachDonHangActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }



    private void create()
    {
        String id1 = mData.push().getKey();
        String id2 = mData.push().getKey();
        String id3 = mData.push().getKey();
        String id4 = mData.push().getKey();
        DonHangHuy dh1 = new DonHangHuy(id1,"N21","Nike","1","35",false, "");
        DonHangHuy dh2 = new DonHangHuy(id2,"J52","Jordan","2","40",false, "");
        DonHangHuy dh3 = new DonHangHuy(id3,"B18","Bitis","1","47",false, "");
        DonHangHuy dh4 = new DonHangHuy(id4,"A84","Adidas","3","39",false, "");

        mData.child("DonHangHuy").child(id1).setValue(dh1);
        mData.child("DonHangHuy").child(id2).setValue(dh2);
        mData.child("DonHangHuy").child(id3).setValue(dh3);
        mData.child("DonHangHuy").child(id4).setValue(dh4);
    }

    private void loadData(){
        mData.child("DonHangHuy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                donHangHuys.clear();
                DonHangHuy donHangHuy;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    donHangHuy = ds.getValue(DonHangHuy.class);
                    donHangHuys.add(donHangHuy);
                }
                donHangHuyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                //TODO HERE
            }
        });

        sv_DSH.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                letSearch(query, true);
                Toast.makeText(DanhSachHuyDonHangActivity.this, "OK",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty() || "".trim().equals(newText))
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
            ArrayList<DonHangHuy> arrDonHangHuy = new ArrayList<>();
            for(DonHangHuy item : donHangHuys)
            {
                if(item.getMaSanPham().contains(keyWord) ||
                        item.getTenSanPham().contains(keyWord) ||
                        item.getSoLuong().contains(keyWord) ||
                        item.getSize().contains(keyWord))
                {
                    arrDonHangHuy.add(item);
                }
            }
            donHangHuyAdapter = new AdapterHuy(this, R.layout.listview_danhsach_huy_donhang_layout, arrDonHangHuy);
            list_DSHuy.setAdapter(donHangHuyAdapter);
        }
        else
        {
            taoAdapter();
            loadData();
        }
    }


}

