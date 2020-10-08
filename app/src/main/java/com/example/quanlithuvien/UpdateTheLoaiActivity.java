package com.example.quanlithuvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.TheLoai;

public class UpdateTheLoaiActivity extends AppCompatActivity {
    EditText edtMaTheLoai,edtTenTheLoai,edtMoTa,edtVitri;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_the_loai);
        setTitle("Thể Loại");
        edtMaTheLoai = findViewById(R.id.edtMaTheLoai1);
        edtTenTheLoai = findViewById(R.id.edtTenTheLoai1);
        edtMoTa = findViewById(R.id.edtMoTa1);
        edtVitri = findViewById(R.id.edtViTri1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            edtMaTheLoai.setText(bundle.getString("MATHELOAI"));
            edtTenTheLoai.setText(bundle.getString("TENTHELOAI"));
            edtMoTa.setText(bundle.getString("MOTA"));
            edtVitri.setText(bundle.getString("VITRI"));
        }
    }
    public void UpdateTheLoai(View view) {
        theLoaiDAO = new TheLoaiDAO(this);
        TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(),edtTenTheLoai.getText().toString(),edtMoTa.getText().toString(),Integer.parseInt(edtVitri.getText().toString()));
        if (theLoaiDAO.updateTheLoai(theLoai) ==1){
            Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void huyUpdate(View view) {
        finish();
    }

}