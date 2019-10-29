package com.example.appbangiay.Manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class DanhSachTaoNhanVienActivity extends AppCompatActivity {

    Button btn_Tao, btn_Huy, btn_chinhSua;
    EditText edt_tenNV,edt_soDT,edt_email, edt_ngaySinh,edt_password,edt_diaChi, edt_chucVu;
    ImageView img_nhanVien;
    Uri uri;
    String imageUrl;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    StorageReference storageReference;
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
        btn_chinhSua = (Button) findViewById(R.id.btn_chinhSua);
        edt_tenNV = (EditText) findViewById(R.id.edt_tenNV);
        edt_soDT = (EditText) findViewById(R.id.edt_soDT);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_ngaySinh = (EditText) findViewById(R.id.edt_ngaySinh);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_diaChi = (EditText) findViewById(R.id.edt_diaChi);
        edt_chucVu = (EditText) findViewById(R.id.edt_chucVu);
        img_nhanVien = (ImageView) findViewById(R.id.img_nhanVien);
    }

    private void control(){

//                int idNV = new Random().nextInt();
//                QuanLyNhanVien nv1 = new QuanLyNhanVien("0","NV-"+idNV, edt_tenNV.getText().toString()
//                        ,edt_soDT.getText().toString(),
//                        edt_ngaySinh.getText().toString(), edt_email.getText().toString(), "NhanVien",imgURL,edt_password.getText().toString(),edt_diaChi.getText().toString());
//                createUser(nv1);

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

    public void btn_chinhsua(View view) {
        Intent photoPicker  = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            uri = data.getData();
            img_nhanVien.setImageURI(uri);

        }
        else Toast.makeText(this, "Ảnh chưa chọn !!!", Toast.LENGTH_SHORT).show();
    }

    public void upImage()
    {
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("NVImage").child(uri.getLastPathSegment());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Uploading....");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage  = uriTask.getResult();
                imageUrl = urlImage.toString() ;
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });

    }


    public void btn_TAO(View view) {
        upImage();
        uploadnv();
        Intent intent = new Intent(DanhSachTaoNhanVienActivity.this,DanhSachQuanLyNhanVienActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public  void uploadnv()
    {
        int idNV = new Random().nextInt();
        QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien("0",
                "NV"+idNV,
                edt_tenNV.getText().toString(),
                edt_ngaySinh.getText().toString(),
                edt_soDT.getText().toString(),
                edt_diaChi.getText().toString(),
                edt_email.getText().toString(),
                edt_chucVu.getText().toString(),
                imageUrl,
                edt_password.getText().toString()
        );
        createUser(quanLyNhanVien);
        String myCurrentDataTime = DateFormat.getTimeInstance()
                .format(Calendar.getInstance().getTime());

        mData.child(myCurrentDataTime).setValue(quanLyNhanVien).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(DanhSachTaoNhanVienActivity.this,"Uploaded",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DanhSachTaoNhanVienActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}

