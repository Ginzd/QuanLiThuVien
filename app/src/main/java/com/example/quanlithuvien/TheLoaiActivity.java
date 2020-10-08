package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
    EditText edtMaTheLoai,edtTenTheLoai,edtMoTa,edtVitri;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        setTitle("Thể Loại");
        edtMaTheLoai = findViewById(R.id.edtMaTheLoai);
        edtTenTheLoai = findViewById(R.id.edtTenTheLoai);
        edtMoTa = findViewById(R.id.edtMoTa);
        edtVitri = findViewById(R.id.edtViTri);

    }

    public void AddTheLoai(View view) {
        theLoaiDAO = new TheLoaiDAO(this);
        try {
            if (validation() < 0){
                Toast.makeText(this, "Ko de trong", Toast.LENGTH_SHORT).show();
            }else {
                TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(),edtTenTheLoai.getText().toString(),edtMoTa.getText().toString(),Integer.parseInt(edtVitri.getText().toString()));
                if (theLoaiDAO.insertTheLoai(theLoai)>0){
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "No success", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }


    public void ShowTheLoai(View view) {
        Intent intent = new Intent(this,ListTheLoaiActivity.class);
        startActivity(intent);
    }
    public int validation(){
        int check = 1;
        if (edtMaTheLoai.getText().length() == 0 || edtTenTheLoai.getText().length() == 0){
            check = -1;
        }
        return check;
    }


}