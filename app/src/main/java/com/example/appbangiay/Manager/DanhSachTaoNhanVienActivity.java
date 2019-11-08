package com.example.appbangiay.Manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.data_models.QuanLyNhanVien;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.QuanLyNhanVien;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class DanhSachTaoNhanVienActivity extends AppCompatActivity {

    Button btn_Tao, btn_Huy, btn_chinhSua;
    EditText edt_hoTen,edt_tenTK,edt_email,edt_password;
    TextView txt_chucVu;
    Spinner spn_gioiTinh;
    ImageView img_nhanVien;
    Uri uri;
    TextView result;
    String imageUrl;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_taonhanvien_quanly_layout);

        getViews();
        //Processing events




    }

    private void getViews(){
        btn_Tao = (Button) findViewById(R.id.btn_Tao);
        btn_Huy = (Button) findViewById(R.id.btn_Huy);
        edt_hoTen = (EditText) findViewById(R.id.edt_fullName);
        edt_tenTK = (EditText) findViewById(R.id.edt_userName);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_email = (EditText) findViewById(R.id.edt_email);
        txt_chucVu = (TextView) findViewById(R.id.txt_CV);
        spn_gioiTinh = (Spinner) findViewById(R.id.spn_gioiTinh);
        //edt_chucVu = (EditText) findViewById(R.id.edt_chucVu);
        result = (TextView) findViewById(R.id.hashpass);
        //img_nhanVien = (ImageView) findViewById(R.id.img_nhanVien);
    }

    private void control(){
                String gioiTinh = spn_gioiTinh.getSelectedItem().toString();
                //int idNV = new Random().nextInt();
                QuanLyNhanVien nv1 = new QuanLyNhanVien(edt_hoTen.getText().toString(), edt_tenTK.getText().toString(), edt_email.getText().toString(), edt_password.getText().toString(), gioiTinh, "Employee");
                createUser(nv1);

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
        String id = mData.child("Users").push().getKey();
        mData.child("Users").child(id).setValue(user);
    }



    public void btn_TAO(View view) {
        hashPass(edt_password.toString());
        control();
        edt_hoTen.setText("");
        edt_tenTK.setText("");
        edt_email.setText("");
        edt_password.setText("");
        //uploadnv();
        Intent intent = new Intent(DanhSachTaoNhanVienActivity.this,DanhSachQuanLyNhanVienActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void hashPass(String pass)
    {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(pass.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MDSHash = new StringBuffer();
            for(int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while ((h.length() < 0))
                {
                    h = "0" + h;
                    MDSHash.append(h);
                }
                result.setText(MDSHash);
            }
        }catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }
}

