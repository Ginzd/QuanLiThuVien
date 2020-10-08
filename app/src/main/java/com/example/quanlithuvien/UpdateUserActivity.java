package com.example.quanlithuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlithuvien.dao.NguoiDungDao;

public class UpdateUserActivity extends AppCompatActivity {
    EditText edtUser, edtPass, edtPhone, edtFullName;
    Button btnUp2,btnHuy2;
    NguoiDungDao nguoiDungDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        setTitle("Người Dùng");
        edtUser = findViewById(R.id.edtUserNameNguoiDung2);
        edtPass = findViewById(R.id.edtPassNguoiDung2);
        edtPhone = findViewById(R.id.edtPhoneNguoiDung2);
        edtFullName = findViewById(R.id.edtFullNameNguoiDung2);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            edtUser.setText(bundle.getString("userName_key"));
            edtPass.setText(bundle.getString("password_key"));
            edtPhone.setText(bundle.getString("phone_key"));
            edtFullName.setText(bundle.getString("hoTen_key"));
        }
        btnUp2 = findViewById(R.id.btnUpdate2);
        btnUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nguoiDungDao = new NguoiDungDao(UpdateUserActivity.this);
                if (nguoiDungDao.updateUser(edtUser.getText().toString(),edtPhone.getText().toString(),
                        edtFullName.getText().toString())==1){
                    Toast.makeText(UpdateUserActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateUserActivity.this,ListNguoiDungActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(UpdateUserActivity.this, "Update thất bại - Update theo ten", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy2 = findViewById(R.id.btnHuy2);
        btnHuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}