package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button btnNhanVien, btnThoat, btnPhongBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnPhongBan = findViewById(R.id.btnPhongBan);
        btnThoat = findViewById(R.id.btnThoat);

        // sự kiện nút thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePage.super.onBackPressed();
            }
        });
        // sự kiện chuyển đến phòng ban
        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Department.class);
                startActivity(intent);
            }
        });
        // sự kiện chuyển đến Nhân viên
        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Staff.class);
                startActivity(intent);
            }
        });

    }
}