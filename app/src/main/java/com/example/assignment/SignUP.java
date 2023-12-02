package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUP extends AppCompatActivity {

    private Button btnSign, btnQuayLai;
    private EditText edtUsername, edtPassword, edtPasswordCf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        btnSign = findViewById(R.id.btnLoginDK);
        btnQuayLai = findViewById(R.id.btnQuayLai);
        edtUsername = findViewById(R.id.UsernameDk);
        edtPassword = findViewById(R.id.PasswordDk);
        edtPasswordCf = findViewById(R.id.PasswordNL);

        // sự kiện nút signup
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = edtUsername.getText().toString();
                String p = edtPassword.getText().toString();
                String pCF = edtPasswordCf.getText().toString();

                // validate
                if (u.equals("") || p.equals("") || pCF.equals("")) {
//                    Toast.makeText(Dang_ky.this, "Nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                    if (u.equals("")){
                        edtUsername.setError("Vui lòngnhaapjp username");
                    }
                    if (p.equals("")){
                        edtPassword.setError("Vui lòng nhập password");
                    }
                    if (pCF.equals("")){
                        edtPasswordCf.setError("Vui lòng nhập lại passsword");
                    }

                } else if (!p.equals(pCF)) {
                    Toast.makeText(SignUP.this, "nhập mâ khẩu không trùng khớp", Toast.LENGTH_SHORT).show();

                } else{
                    Intent intent = new Intent();
                    intent.putExtra("user",u);
                    intent.putExtra("pass",p);
                    setResult(1, intent);
                    finish();

                }

            }
        });
        // sự kiện nút quay lại
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUP.this, Login.class);
                startActivity(intent);
            }
        });
    }
}