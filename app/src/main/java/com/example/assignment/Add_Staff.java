package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.assignment.Model.Adapter_Add;
import com.example.assignment.Model.DAO_Add;
import com.example.assignment.Model.DAO_Staff;

import java.util.ArrayList;

public class Add_Staff extends AppCompatActivity {
    private Spinner spinner;
    private EditText edtUsername, edtAddress;
    private Button btnSubmit;
    private String selectedItemSpinner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_staff);

        // ánh xạ các đối tượng
        spinner = findViewById(R.id.sp);
        edtUsername = findViewById(R.id.tenSV);
        edtAddress = findViewById(R.id.dcSV);
        btnSubmit = findViewById(R.id.btnSubmit);

        ArrayList<DAO_Add> list = new ArrayList<>();
        list.add(new DAO_Add(R.mipmap.hanh_chinh, "Hành Chính"));
        list.add(new DAO_Add(R.mipmap.dt, "Đào Tạo"));
        list.add(new DAO_Add(R.mipmap.nv, "Nhân Viên"));
        list.add(new DAO_Add(R.mipmap.kt, "Kế Toán"));

        Adapter_Add adapter = new Adapter_Add(this, list);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemSpinner = ((DAO_Add)spinner.getItemAtPosition(position)).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("MaNV", edtUsername.getText().toString());
                bundle.putString("Name", edtAddress.getText().toString());
                bundle.putString("PhongBan", selectedItemSpinner);
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
            }
        });
    }
}