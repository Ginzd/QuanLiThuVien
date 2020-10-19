package com.example.quanlithuvien;

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
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spinnerTheLoai;
    EditText edtMaSach, edtTenSach, edtNXB, edtTacGia, edtGiaBia, edtSoLuong;
    String ma_TheLoai = "";
    List<TheLoai> theLoaiList = new ArrayList<>();
    TextInputLayout inputMaSach, inputTenSach, inputTacGia, inputGiaBia,inputSoLuong,inputNXB;

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
        inputGiaBia = findViewById(R.id.text_input_layout_giaBia);
        inputMaSach = findViewById(R.id.text_input_layout_maSach);
        inputTenSach = findViewById(R.id.text_input_layout_tenSach);
        inputSoLuong = findViewById(R.id.text_input_layout_soLuong);
        inputNXB = findViewById(R.id.text_input_layout_NXB);
        inputTacGia = findViewById(R.id.text_input_layout_tacGia);
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(this);
        theLoaiList = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> adapterTheLoai = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_dropdown_item, theLoaiDAO.getAllTheLoai());
        spinnerTheLoai.setAdapter(adapterTheLoai);
    }

    public void addBook(View view) {
        if (!validateMaSach() || !validateTenSach() || !validateTacGia() || !validateNXB() || !validateGiaBia() || !validateSoLuong()) {
            return;
        }
        sachDAO = new SachDAO(this);
        Sach sach = new Sach(edtMaSach.getText().toString(), ma_TheLoai, edtTenSach.getText().toString(), edtTacGia.getText().toString(), edtNXB.getText().toString(), Double.parseDouble(edtGiaBia.getText().toString()), Integer.parseInt(edtSoLuong.getText().toString()));
        try {
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Mã sách đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error AddBook", ex.toString());
        }


    }

    public void huyThemSach(View view) {
        finish();
    }

    private boolean validateMaSach() {
        String val = inputMaSach.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            inputMaSach.setError("Mã sách không để trống !");
            return false;
        } else {
            inputMaSach.setError(null);
            inputMaSach.setErrorEnabled(false);
            return true;
        }
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