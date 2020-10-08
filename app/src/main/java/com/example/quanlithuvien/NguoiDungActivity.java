package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.adapter.AdapterNguoiDung;
import com.example.quanlithuvien.dao.NguoiDungDao;
import com.example.quanlithuvien.model.NguoiDung;

import java.util.ArrayList;

public class NguoiDungActivity extends AppCompatActivity {
    Button btnAddNguoiDung;
    NguoiDungDao nguoiDungDao;
    EditText edtUser,edtPass,edtPhone,edtFullName;
    ArrayList<NguoiDung> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("Thêm Người Dùng");
        listUser = new ArrayList<>();
        btnAddNguoiDung = findViewById(R.id.btnAddNguoiDung);
        edtUser = findViewById(R.id.edtUserNameNguoiDung);
        edtPass = findViewById(R.id.edtPassNguoiDung);
        edtPhone = findViewById(R.id.edtPhoneNguoiDung);
        edtFullName = findViewById(R.id.edtFullNameNguoiDung);

    }

    public void addNguoiDung(View view){
        nguoiDungDao = new NguoiDungDao(NguoiDungActivity.this);
        NguoiDung nguoiDung = new NguoiDung(edtUser.getText().toString(),
                edtPass.getText().toString(),edtPhone.getText().toString(),
                edtFullName.getText().toString());
        if (nguoiDungDao.insertNguoiDung(nguoiDung) == 1){
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ListNguoiDungActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }

    }
//    public void updateNguoiDung(View view){
//        nguoiDungDao = new NguoiDungDao(NguoiDungActivity.this);
//        if (nguoiDungDao.updateUser(edtUser.getText().toString(),edtPhone.getText().toString(),
//                edtFullName.getText().toString())==1){
//            Toast.makeText(this, "Update thành công", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this,ListNguoiDungActivity.class);
//            startActivity(intent);
//        }else {
//            Toast.makeText(this, "Update thất bại - Update theo ten", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void ShowUsers(View view) {
        finish();
    }
}