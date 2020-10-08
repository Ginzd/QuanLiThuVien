package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.Sach;
import com.example.quanlithuvien.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spinnerTheLoai;
    EditText edtMaSach,edtTenSach,edtNXB,edtTacGia,edtGiaBia,edtSoLuong;
    String ma_TheLoai = "";
    List<TheLoai> theLoaiList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        setTitle("Sách");
        spinnerTheLoai = findViewById(R.id.spinerTheLoai);
        edtMaSach = findViewById(R.id.edtMaSach);
        edtTenSach = findViewById(R.id.edtTenSach);
        edtNXB = findViewById(R.id.edtNhaXuatBan);
        edtTacGia = findViewById(R.id.edtTacGia);
        edtGiaBia = findViewById(R.id.edtGiaBia);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        getTheLoai();
        spinnerTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ma_TheLoai = theLoaiList.get(spinnerTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public int checkPositionTheLoai(String the_loai){
        for (int i = 0;i <theLoaiList.size();i++){
            if (the_loai.equals(theLoaiList.get(i).getMaTheLoai())){
                return i;
            }
        }
        return 0;
    }
    public void getTheLoai(){
        theLoaiDAO = new TheLoaiDAO(this);
        theLoaiList = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> adapterTheLoai = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_dropdown_item,theLoaiDAO.getAllTheLoai());
        spinnerTheLoai.setAdapter(adapterTheLoai);
    }
    public void AddBook(View view) {
        sachDAO = new SachDAO(this);
        Sach sach = new Sach(edtMaSach.getText().toString(),ma_TheLoai,edtTenSach.getText().toString(),edtTacGia.getText().toString(),edtNXB.getText().toString(),Double.parseDouble(edtGiaBia.getText().toString()),Integer.parseInt(edtSoLuong.getText().toString()));
        try {
            if (sachDAO.inserSach(sach) >0){
                Toast.makeText(this, "Add Book Success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Add Book Fail", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Log.e("Error AddBook",ex.toString());
        }
    }

    public void ShowBook(View view) {
        finish();
    }

}