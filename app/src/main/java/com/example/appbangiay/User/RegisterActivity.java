package com.example.appbangiay.User;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appbangiay.R;
import com.example.appbangiay.data_models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText txt_fullName, txt_username, txt_email, txt_password;
    Button btn_register;
    RadioButton radioNam,radioNu;
    String gender = "";
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.manhinh_dangky_layout);
        super.onCreate(savedInstanceState);
        //
        txt_fullName = (EditText)findViewById(R.id.txt_full_name);
        txt_username = (EditText)findViewById(R.id.txt_user_name);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_password = (EditText)findViewById(R.id.txt_password);
        btn_register = (Button)findViewById(R.id.sign_up);
        radioNam =(RadioButton)findViewById(R.id.radio_nam);
        radioNu = (RadioButton) findViewById(R.id.radio_nu);

        databaseReference = FirebaseDatabase.getInstance().getReference(" Users");
        firebaseAuth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             final String fullName = txt_fullName.getText().toString();
             final String userName = txt_username.getText().toString();
             final String email    = txt_email.getText().toString();
             String password = txt_password.getText().toString();

                if(radioNam.isChecked()){

                    gender ="Nam";
                    }
                if(radioNu.isChecked()){

                    gender ="Nữ";
                }
                if (TextUtils.isEmpty(email)){

                    Toast.makeText(RegisterActivity.this,"Nhập email",Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)){

                    Toast.makeText(RegisterActivity.this,"Nhập password",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(fullName)){

                    Toast.makeText(RegisterActivity.this,"Nhập họ tên ",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(userName)){

                    Toast.makeText(RegisterActivity.this,"Nhập tên ",Toast.LENGTH_SHORT).show();
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Users information = new Users(
                                            fullName,
                                            userName,
                                            email,
                                            gender
                                    );

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(RegisterActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        }
                                    });
                                }
                                else {


                                }
                            }
                        });
            }
        });

    }
}
