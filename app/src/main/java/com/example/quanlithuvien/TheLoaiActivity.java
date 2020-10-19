package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.TheLoai;
import com.google.android.material.textfield.TextInputLayout;

public class TheLoaiActivity extends AppCompatActivity {
    EditText edtMaTheLoai, edtTenTheLoai, edtMoTa, edtVitri;
    TheLoaiDAO theLoaiDAO;
    TextInputLayout inputMaTheLoai, inputTenTheLoai, inputMoTa, inputViTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        setTitle("Thể Loại");
        edtMaTheLoai = findViewById(R.id.edtMaTheLoai);
        edtTenTheLoai = findViewById(R.id.edtTenTheLoai);
        edtMoTa = findViewById(R.id.edtMoTa);
        edtVitri = findViewById(R.id.edtViTri);

        inputMaTheLoai = findViewById(R.id.text_input_layout_maTheLoai);
        inputTenTheLoai = findViewById(R.id.text_input_layout_tenTheLoai);
        inputMoTa = findViewById(R.id.text_input_layout_moTa);
        inputViTri = findViewById(R.id.text_input_layout_viTri);

    }

    public void AddTheLoai(View view) {
        if (!validateMaTheLoai() || !validateTenTheLoai() || !validateViTri()) {
            return;
        }
        theLoaiDAO = new TheLoaiDAO(this);
        TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(), edtTenTheLoai.getText().toString(), edtMoTa.getText().toString(), Integer.parseInt(edtVitri.getText().toString()));
        if (theLoaiDAO.insertTheLoai(theLoai) > 0) {
            Toast.makeText(this, "Thêm thể loại thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Mã thể loại đã tồn tại", Toast.LENGTH_SHORT).show();
        }

    }


    public void ShowTheLoai(View view) {
        Intent intent = new Intent(this, ListTheLoaiActivity.class);
        startActivity(intent);
    }

    private boolean validateMaTheLoai() {
        String val = inputMaTheLoai.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            inputMaTheLoai.setError("Mã thể loại không để trống !");
            return false;
        } else {
            inputMaTheLoai.setError(null);
            inputMaTheLoai.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateTenTheLoai() {
        String val = inputTenTheLoai.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            inputTenTheLoai.setError("Tên thể loại không để trống !");
            return false;
        } else {
            inputTenTheLoai.setError(null);
            inputTenTheLoai.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateViTri() {
        String val = inputViTri.getEditText().getText().toString().trim();
        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            inputViTri.setError("Vị trí phải là số");
            return false;
        }

        if (val.isEmpty()) {
            inputViTri.setError("Vị trí không để trống !");
            return false;
        } else {
            inputViTri.setError(null);
            inputViTri.setErrorEnabled(false);
            return true;
        }
    }


}