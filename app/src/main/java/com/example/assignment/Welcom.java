package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.assignment.Model.DAO_Staff;

import java.util.ArrayList;

public class Welcom extends AppCompatActivity {
    private static final int Time = 3000;
    public static ArrayList<DAO_Staff> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom);

        // sự kiện 5 giấy chuyển màn hình
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcom.this, Login.class);
                startActivity(intent);
                finish();
            }

        }, Time);

        // list Nhân Viên
        listNV();
    }

    // list Nhân Viên
    public void listNV(){
         String maNV[] = {"NV001", "NV002", "NV003", "NV004", "NV005", "NV006"};
         String Name[] = {"Lê Xuân Thảo", "Cù Duy Đức", "Nguyễn Tiến Phước", "Nguyễn Khắc Trung", "Nguyễn Bá Nghị","Như Ý"};
         String PhongBan[] = {"Hành Chính", "Hành Chính", "Đào Tạo", "Nhân Viên", "Nhân Viên","Hành Chính"};

        for (int i = 0; i < maNV.length; i++) {
            list.add(new DAO_Staff("Mã NV: " + maNV[i], "Họ Tên: " + Name[i], "Phòng Ban: " + PhongBan[i]));

        }
    }



}