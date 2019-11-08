package com.example.appbangiay.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appbangiay.Employee.MangHinhChinhNhanvienActivity;
import com.example.appbangiay.Manager.MangHinhChinhQuanLy;
import com.example.appbangiay.R;
import com.example.appbangiay.data_models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText txt_email, txt_password;
    Button btn_dangki, btn_Login;
    RadioButton radio_user, radio_nhanvien, radio_quanly;
    private FirebaseAuth firebaseAuth;
    String job = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_dangnhap_layout);

        //
        txt_email = (EditText) findViewById(R.id.email);
        txt_password = (EditText) findViewById(R.id.password);
        btn_dangki = (Button) findViewById(R.id.btnTao);
        btn_Login = (Button) findViewById(R.id.btnLogin);
        radio_user = (RadioButton) findViewById(R.id.radio_user);
        radio_nhanvien = (RadioButton) findViewById(R.id.radio_nhanvien);
        radio_quanly = (RadioButton) findViewById(R.id.radio_quanly);


        //Login
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJob();
//                startActivity(new Intent(getApplicationContext(), NavMainActivity.class));
                String email = txt_email.getText().toString();
                final String password = txt_password.getText().toString();

                firebaseAuth = FirebaseAuth.getInstance();
                if (checkData(email, password)) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

                    Query query = reference.child("Users").orderByChild("email").equalTo(email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // dataSnapshot is the "issue" node with all children with id 0

                                Users users = null;
                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                    users = issue.getValue(Users.class);
                                }
                                if (users.getPassword().equals(password)) {
                                    if (users.getJob().equals(job)) {
                                        switch (job) {
                                            case "Custommer":
                                                startActivity(new Intent(getApplicationContext(), MemberActivity.class));
                                                break;
                                            case "Admin":
                                                startActivity(new Intent(getApplicationContext(), MangHinhChinhQuanLy.class));

                                                break;
                                            case "Employee":
                                                startActivity(new Intent(getApplicationContext(), MangHinhChinhNhanvienActivity.class));

                                                break;
                                            default:
                                                Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu không chính xác", Toast.LENGTH_SHORT).show();

                                                break;
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });


        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


        radio_user.setChecked(true);


        radio_quanly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btn_dangki.setVisibility(View.GONE);
                }
            }
        });

        radio_nhanvien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btn_dangki.setVisibility(View.GONE);
                }
            }
        });
        radio_user.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btn_dangki.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    private boolean checkData(String email, String password) {
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(LoginActivity.this, "Nhập email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this, "Nhập password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(LoginActivity.this, "Password ngắn", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getJob() {
        if (radio_nhanvien.isChecked()) {
            job = "Employee";
        }
        if (radio_user.isChecked()) {
            job = "Custommer";
        }
        if (radio_quanly.isChecked()) {
            job = "Admin";
        }
    }
}
