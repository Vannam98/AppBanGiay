package com.example.appbangiay.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Employee.DanhSachDonHangActivity;
import com.example.appbangiay.R;


public class List_ThanhToanActivity extends AppCompatActivity {
    public static Intent intent;
    private Button btn_tang;
    private Button btn_xuong;
    private Button btn_xoa_lsv;
    private EditText edt_soluong;
    private  TextView txt_tensanpham_lsv, txt_gia_lsv, txt_masp_lsv,txt_size_lsv2;
    private Spinner spinner_thanhtoan;
    //    DatabaseReference ref;
    private int soLuong = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_thanhtoan_layout);
        /*Ánh xạ View Button từ file .xml sang file .java bằng thuộc tính id*/
        btn_tang = (Button) findViewById(R.id.button_tang_lsv);
        btn_xuong = (Button) findViewById(R.id.button_xuong_lsv);
        edt_soluong = (EditText) findViewById(R.id.edt_soluong_lsv);
        txt_tensanpham_lsv = findViewById(R.id.txt_tensanpham_lsv);
        txt_gia_lsv = findViewById(R.id.txt_gia_lsv);
        txt_masp_lsv = findViewById(R.id.txt_masp_lsv);
        btn_xoa_lsv = (Button) findViewById(R.id.btn_xoa_lsv);
        txt_size_lsv2 = findViewById(R.id.txt_size_lsv2);
//        String key = getIntent().getExtras().get("").toString();
//        ref = FirebaseDatabase.getInstance().getReference().child(key);


        intent = new Intent(this, DanhSachDonHangActivity.class);

        btn_xuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong--;
                if(soLuong < 0){
                    soLuong = 0;
                }else {
                    edt_soluong.setText(""+soLuong);
                }
            }
        });
        btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                edt_soluong.setText(soLuong+"");
            }
        });
        btn_xoa_lsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(List_ThanhToanActivity.this, "xoa", Toast.LENGTH_LONG).show();
//                            List_ThanhToanActivity.this.finish();
//                        }else {
//                            Toast.makeText(List_ThanhToanActivity.this, " khong xoa", Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                });

            }
        });
    }}
