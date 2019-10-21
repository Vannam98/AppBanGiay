package com.example.appbangiay.Manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appbangiay.Adapter.MyAdapter;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.ShoeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MangerShoe extends AppCompatActivity {
    RecyclerView mrecyclerView;
    List<ShoeData> myShoeList;
    ShoeData mShoeData;
    private DatabaseReference databaseReference ;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_sanpham_layout);

        mrecyclerView = (RecyclerView)findViewById(R.id.recycle);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MangerShoe.this,1);
        mrecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");

        myShoeList = new ArrayList<>();
        myAdapter = new MyAdapter(MangerShoe.this,myShoeList);
        mrecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Shoe");
        progressDialog.show();
        eventListener  = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myShoeList.clear();
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren())
                {

                    ShoeData shoeData = itemSnapshot.getValue(ShoeData.class);
                    myShoeList.add(shoeData);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });
    }
    public void btn_uploadActivity(View view) {
        startActivity(new Intent(this, UploadImage.class));
    }
}
