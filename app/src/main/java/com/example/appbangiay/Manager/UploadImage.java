package com.example.appbangiay.Manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.ShoeData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;


public class UploadImage extends AppCompatActivity {

    ImageView shoesImage;
    Uri uri;
    EditText txt_name,txt_description,txt_price,txt_size,txt_soluong;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        shoesImage = (ImageView)findViewById(R.id.iv_shoeImage);
        txt_name = (EditText)findViewById(R.id.txt_shoe_name);
        txt_description = (EditText)findViewById(R.id.text_description);
        txt_price = (EditText)findViewById(R.id.text_price);
        txt_size =(EditText)findViewById(R.id.txt_shoe_size);
        txt_soluong = (EditText)findViewById(R.id.txt_shoe_int);

    }
    public void btnSelectImage(View view) {
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
            shoesImage.setImageURI(uri);

        }
        else Toast.makeText(this, "Ảnh chưa chọn !!!", Toast.LENGTH_SHORT).show();
    }
    public void uploadImage()
    {
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("ShoeImage").child(uri.getLastPathSegment());
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
                uploadShoe();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });

    }

    public void btn_uploadShoe(View view) {
        uploadImage();
    }
    public void uploadShoe()
    {

        ShoeData shoeData = new ShoeData(
                txt_name.getText().toString(),
                txt_description.getText().toString(),
                txt_size.getText().toString(),
                txt_soluong.getText().toString(),
                txt_price.getText().toString(),
                imageUrl
        );
        String myCurrentDataTime = DateFormat.getTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Shoe")
                .child(myCurrentDataTime).setValue(shoeData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(UploadImage.this,"Uploaded",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadImage.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
