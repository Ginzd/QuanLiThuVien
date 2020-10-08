package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.database.DataHelper;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText userName,edMKC,edMKM,edReMKM;
    Button btnDoiMK,btnHuyMK;
    DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("Đổi Mật Khẩu");
        userName = findViewById(R.id.userNameChangePass);
        edMKC = findViewById(R.id.edChangeMkCu);
        edMKM = findViewById(R.id.ed_changeMk);
        edReMKM = findViewById(R.id.edChangeMk2);
        btnDoiMK = findViewById(R.id.btnDoiMk);
        btnHuyMK = findViewById(R.id.btnHuyChangeMK);
        db = new DataHelper(this);

        Intent intent = getIntent();
        userName.setText(intent.getStringExtra("username"));

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String passCu = edMKC.getText().toString();
                String passMoi = edMKM.getText().toString();
                String rePassMoi = edReMKM.getText().toString();

                Boolean checkuser = db.checkuserName(user);
                Boolean checkuserPassCu = db.checkUserPassWord(user,passCu);
                if ((checkuser==true) && (checkuserPassCu==true)){
                    Boolean checkPass = db.updatePassword(user,passMoi);
                    if (checkPass==true){
                        Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else if (!passMoi.equals(rePassMoi)){
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu mới chưa trùng khớp", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuyMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}