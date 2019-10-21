package com.example.appbangiay.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbangiay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText txt_email, txt_password;
    Button btn_dangki ,btn_Login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_dangnhap_layout);

        //
        txt_email = (EditText)findViewById(R.id.email);
        txt_password = (EditText)findViewById(R.id.password);
        btn_dangki = (Button)findViewById(R.id.btnTao);
        btn_Login = (Button)findViewById(R.id.btnLogin);

        //Login
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NavMainActivity.class));
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();

                firebaseAuth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(email)){

                    Toast.makeText(LoginActivity.this,"Nhập email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){

                    Toast.makeText(LoginActivity.this,"Nhập password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(LoginActivity.this,"Password ngắn",Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công ",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MemberActivity.class));

                                } else {

                                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại ",Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });


            }
        });

        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}
