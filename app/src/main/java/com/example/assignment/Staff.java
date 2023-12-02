package com.example.assignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SizeF;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.assignment.Model.Adapter_Staff;
import com.example.assignment.Model.DAO_Add;
import com.example.assignment.Model.DAO_Staff;

import java.io.Serializable;
import java.util.ArrayList;

public class Staff extends AppCompatActivity implements Serializable {
    private ListView listView;
    private Button btnApp;
    private Toolbar toolbar;
    private Adapter_Staff adapter;
    private SearchView searchView;
    private Context context;

    private ArrayList<DAO_Add> list1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff);

        btnApp = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.lvStudent);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Nhân Viên");

        loadData();
        // sự kiện nút thêm
        btnApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Staff.this, Add_Staff.class);
                getdate.launch(intent);
            }
        });


    }
    //  cài đặt Activity For Result
    private ActivityResultLauncher<Intent> getdate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // thêm trả về
            if (result.getResultCode() == 1) {
                Intent intent = result.getData(); // lấy về dử liệu
                Bundle bundle = intent.getExtras(); // lấy về gói
                String MaNV = bundle.getString("MaNV");
                String Name = bundle.getString("Name");
                String PhongBan = bundle.getString("PhongBan");
                Welcom.list.add(new DAO_Staff("Mã NV: " + MaNV, "Họ Và Tên: " + Name, "Phong Ban: " + PhongBan));
                adapter.notifyDataSetChanged();
            }

        }
    });

    public void loadData(){
        adapter = new Adapter_Staff(this, Welcom.list,getdate);
        listView.setAdapter(adapter);
    }


    // xử lý sự kiện tìm kiếm
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.dangXuat) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}