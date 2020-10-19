package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.Sach;
import com.example.quanlithuvien.model.TheLoai;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class UpdateSachActivity extends AppCompatActivity {
    EditText edtTenSach,edtNXB,edtTacGia,edtGiaBia,edtSoLuong;
    TextView edtMaSach;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    String ma_TheLoai = "";
    Spinner spinnerTheLoai1;
    List<TheLoai> theLoaiList = new ArrayList<>();
    TextInputLayout inputTenSach, inputTacGia, inputGiaBia,inputSoLuong,inputNXB;
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
        inputGiaBia = findViewById(R.id.text_input_layout_giaBia1);
        inputTenSach = findViewById(R.id.text_input_layout_tenSach1);
        inputSoLuong = findViewById(R.id.text_input_layout_soLuong1);
        inputNXB = findViewById(R.id.text_input_layout_NXB1);
        inputTacGia = findViewById(R.id.text_input_layout_tacGia1);
    }
    public void UpdateBook(View view) {
        List<Sach> sachList = new ArrayList<>();
        if (!validateTenSach() || !validateTacGia() || !validateNXB() || !validateGiaBia() || !validateSoLuong()) {
            return;
        }
        sachDAO = new SachDAO(this);
        Sach sach = new Sach(edtMaSach.getText().toString(),ma_TheLoai,edtTenSach.getText().toString(),edtTacGia.getText().toString(),edtNXB.getText().toString(),Double.parseDouble(edtGiaBia.getText().toString()),Integer.parseInt(edtSoLuong.getText().toString()));
        try {
            if (sachDAO.updateSach(sach) == 1){
                sachList.add(sach);
                Toast.makeText(this, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Cập nhật sách thất bại", Toast.LENGTH_SHORT).show();
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

    private boolean validateTenSach() {
        String val = inputTenSach.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            inputTenSach.setError("Tên sách không để trống !");

            return false;
        } else {
            inputTenSach.setError(null);
            inputTenSach.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateTacGia() {
        String val = inputTacGia.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            inputTacGia.setError("Tác giả không để trống !");
            return false;
        } else {
            inputTacGia.setError(null);
            inputTacGia.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateNXB() {
        String val = inputNXB.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            inputNXB.setError("Nhà xuất bản không để trống !");
            return false;
        } else {
            inputNXB.setError(null);
            inputNXB.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateGiaBia() {
        String val = inputGiaBia.getEditText().getText().toString().trim();
        try {
            Double.parseDouble(val);
        } catch (NumberFormatException e) {
            inputGiaBia.setError("Giá bìa phải là số thực");
            return false;
        }
        if (val.isEmpty()) {
            inputGiaBia.setError("Giá bìa không để trống !");
            return false;
        } else {
            inputGiaBia.setError(null);
            inputGiaBia.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validateSoLuong() {
        String val = inputSoLuong.getEditText().getText().toString().trim();
        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            inputSoLuong.setError("Số lượng phải là số");
            return false;
        }
        if (val.isEmpty()) {
            inputSoLuong.setError("Số Lượng không để trống !");
            return false;
        } else {
            inputSoLuong.setError(null);
            inputSoLuong.setErrorEnabled(false);
            return true;
        }

    }
}