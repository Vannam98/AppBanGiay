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

public class NavMainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private NavigationView tk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_layout);
        dl = (DrawerLayout) findViewById(R.id.manhinhchinh);
        t = new ActionBarDrawerToggle( this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.sign_in:
                        Toast.makeText(NavMainActivity.this, " Đăng Nhập", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case R.id.registration:
                        Toast.makeText(NavMainActivity.this, " Đăng ký", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                        break;
                  default:
                       return true;
                }
                return true;
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
