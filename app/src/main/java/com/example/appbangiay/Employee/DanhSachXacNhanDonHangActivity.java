package com.example.appbangiay.Employee;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Adapter.AdapterXacNhan;

import com.example.appbangiay.data_models.DonHangXacNhan;
import com.example.appbangiay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhSachXacNhanDonHangActivity extends AppCompatActivity {
    public static Intent intent;
    private ListView list_DSXN;
    private AdapterXacNhan donHangXacNhanAdapter;
    private ArrayList<DonHangXacNhan> donHangXacNhans;
    private Button btn_OK;
    private Button btn_TroVe;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_danhsach_xacnhan_nhanvien_layout);

        AnhXa();
        run();
        intent = new Intent(this, DanhSachHuyDonHangActivity.class);
        taoAdapter();
        create();
        loadData();

    }

    private void taoAdapter()
    {
        donHangXacNhans = new ArrayList<>();
        donHangXacNhanAdapter = new AdapterXacNhan(this, R.layout.listview_danhsach_xacnhan_donhang_layout, donHangXacNhans);
        list_DSXN.setAdapter(donHangXacNhanAdapter);
    }

    private void AnhXa()
    {
        btn_TroVe = (Button) findViewById(R.id.btn_TroVe);
        btn_OK = (Button) findViewById(R.id.btn_OK);
        list_DSXN = findViewById(R.id.list_DSXN);
    }

    private void run()
    {
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachXacNhanDonHangActivity.this, DanhSachHuyDonHangActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btn_TroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachXacNhanDonHangActivity.this, DanhSachDonHangActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }



    private void create()
    {
        String id1 = mData.push().getKey();
        String id2 = mData.push().getKey();
        DonHangXacNhan dh1 = new DonHangXacNhan(id1,"N52","Nike", "2","42","Nam","036961472","Quan9",500000);
        DonHangXacNhan dh2 = new DonHangXacNhan(id2,"J19","JorDan", "1","35","Long","036961472","Quan12",920000);
//        DonHangXacNhan dh3 = new DonHangXacNhan("A638","Adidas","3", "35","Hoa","032651782","Quan1",1200000);
//        DonHangXacNhan dh4 = new DonHangXacNhan("J716","Jordan","1", "42","Thủy","0164178329","Quan7",2000000);
        mData.child("DonHangXacNhan").child(id1).setValue(dh1);
        mData.child("DonHangXacNhan").child(id2).setValue(dh2);

    }

    private void loadData(){
        mData.child("DonHangXacNhan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                donHangXacNhans.clear();
                DonHangXacNhan donHangXacNhan;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    donHangXacNhan = ds.getValue(DonHangXacNhan.class);
                    donHangXacNhans.add(donHangXacNhan);
                }
                donHangXacNhanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO HERE
            }
        });
    }

}

