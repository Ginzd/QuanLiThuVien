package com.example.quanlithuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.Sach;
import com.example.quanlithuvien.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class UpdateSachActivity extends AppCompatActivity {
    EditText edtMaSach,edtTenSach,edtNXB,edtTacGia,edtGiaBia,edtSoLuong;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    String ma_TheLoai = "";
    Spinner spinnerTheLoai1;
    List<TheLoai> theLoaiList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sach);
        setTitle("Sách");
        spinnerTheLoai1 = findViewById(R.id.spinerTheLoai1);
        edtMaSach = findViewById(R.id.edtMaSach1);
        edtTenSach = findViewById(R.id.edtTenSach1);
        edtNXB = findViewById(R.id.edtNhaXuatBan1);
        edtTacGia = findViewById(R.id.edtTacGia1);
        edtGiaBia = findViewById(R.id.edtGiaBia1);
        edtSoLuong = findViewById(R.id.edtSoLuong1);
        getTheLoai();
        spinnerTheLoai1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ma_TheLoai = theLoaiList.get(spinnerTheLoai1.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            edtMaSach.setText(bundle.getString("MASACH"));
            String idTheLoai = bundle.getString("MATHELOAI");
            edtTenSach.setText(bundle.getString("TENSACH"));
            edtNXB.setText(bundle.getString("NXB"));
            edtTacGia.setText(bundle.getString("TACGIA"));
            edtGiaBia.setText(bundle.getString("GIABIA"));
            edtSoLuong.setText(bundle.getString("SOLUONG"));
            spinnerTheLoai1.setSelection(checkPositionTheLoai(idTheLoai));
        }
    }
    public void UpdateBook(View view) {
        sachDAO = new SachDAO(this);
        Sach sach = new Sach(edtMaSach.getText().toString(),ma_TheLoai,edtTenSach.getText().toString(),edtTacGia.getText().toString(),edtNXB.getText().toString(),Double.parseDouble(edtGiaBia.getText().toString()),Integer.parseInt(edtSoLuong.getText().toString()));
        try {
            if (sachDAO.updateSach(sach) == 1){
                Toast.makeText(this, "Update Book Success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Update Book Fail", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Log.e("Error UpdateBook",ex.toString());
        }
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
        spinnerTheLoai1.setAdapter(adapterTheLoai);
    }

    public void huyUpdateBook(View view) {
        finish();
    }
}