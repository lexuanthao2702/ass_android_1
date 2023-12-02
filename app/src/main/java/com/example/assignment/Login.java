package com.example.assignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button btnLogin, btnSignUp;
    private EditText edtUsername, edtPassword;
    private CheckBox checkBox;

    private String userRegiser = "", passRegister = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUsername = findViewById(R.id.Username);
        edtPassword = findViewById(R.id.Password);
        CheckBox cboRemember = findViewById(R.id.cboGhiNho);

        // kiểm tra thông tin đăng nhập, người dùng có lưu lại hay không
        SharedPreferences sharedPreferences = getSharedPreferences("INFO", MODE_PRIVATE);
        boolean isRemenBer = sharedPreferences.getBoolean("isRemenBer", false);
        if (isRemenBer) { // isRemenBer == true
            // lưu thông tin user và pass từ 2 cặp hóa là userLogin va paaaLogin
            String user = sharedPreferences.getString("userLogin", "");
            String pass = sharedPreferences.getString("paaaLogin", "");
            // đua 2 giá trị đã lấy được lên textView trên giao diện
            edtUsername.setText(user);
            edtPassword.setText(pass);
            cboRemember.setChecked(isRemenBer);

            userRegiser = user;
            passRegister = pass;

        }


        // sự kiện chuyển đến nút đăng ký
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUP.class);
                myLauncher.launch(intent);
            }
        });

        // SỰ kiện nút Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLogin = edtUsername.getText().toString();
                String passLogin = edtPassword.getText().toString();
                // validate
                if (userLogin.equals("") || passLogin.equals("")) {

                    if (userLogin.equals("")) {
                        edtUsername.setError("VUi lòng nhập Username");
                    }
                    if (passLogin.equals("")) {
                        edtPassword.setError("VUi lòng nhập Password");
                    }

                } else if (userLogin.equals(userRegiser) && passLogin.equals(passRegister)) {
                    // check Remember me
                    if (cboRemember.isChecked()) { // cboRemember == true
                        SharedPreferences preferences = getSharedPreferences("INFO", MODE_PRIVATE); // gọi file cần lưu trũ
                        SharedPreferences.Editor editor = preferences.edit(); // edit đến file
                        editor.putString("userLogin", userLogin);
                        editor.putString("paaaLogin", passLogin);
                        editor.putBoolean("isRemenBer", true); // kiểm tra có nhấn vào RemenBer?
                        editor.apply();
                    }else { // cboRemember == false
                        SharedPreferences preferences = getSharedPreferences("INFO",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                    }
                    Toast.makeText(Login.this, "Đăng nhập thành Công", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(Đang_Nhap.this, Trang_Chu.class));
                    Intent intent = new Intent(Login.this, HomePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // clear nút quat lại
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Đăng nhâp thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // nơi xử lý dử liệu trả về
                    if (result.getResultCode() == 1) {
                        Intent intent = result.getData();
                        userRegiser = intent.getStringExtra("user");
                        passRegister = intent.getStringExtra("pass");
                    }
                }
            });
}