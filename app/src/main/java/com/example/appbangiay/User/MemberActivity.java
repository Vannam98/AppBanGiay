package com.example.appbangiay.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appbangiay.R;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MemberActivity extends AppCompatActivity {
    private DrawerLayout d;
    private ActionBarDrawerToggle t;
    private NavigationView n;
    private NavigationView tk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_nguoidung_layout);
        d = (DrawerLayout) findViewById(R.id.manhinh_member);
        t = new ActionBarDrawerToggle( this, d, R.string.open, R.string.close);
        d.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        n = findViewById(R.id.member);
        n.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch(id)
                {
                    case R.id.thongtinkhach:
                        Toast.makeText(MemberActivity.this," Thông Tin Khách Hàng" ,Toast.LENGTH_SHORT ).show();
                    case R.id.giaynam:
                        Toast.makeText(MemberActivity.this," Giầy Nam" ,Toast.LENGTH_SHORT ).show();
                    case R.id.giaynu:
                        Toast.makeText(MemberActivity.this," Giầy Nữ" ,Toast.LENGTH_SHORT ).show();
                    case R.id.giaykid:
                        Toast.makeText(MemberActivity.this," Đăng Trẻ em " ,Toast.LENGTH_SHORT ).show();
                    case R.id.logout:
                        Toast.makeText(MemberActivity.this," Logout" ,Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(getApplicationContext(), NavMainActivity.class));
                    default:
                        return true;
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (t.onOptionsItemSelected(item))
            return  true;
        return super.onOptionsItemSelected(item);
    }
}
