package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.TheLoai;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateTheLoaiActivity extends AppCompatActivity {
    EditText edtTenTheLoai,edtMoTa,edtVitri;
    TextView edtMaTheLoai;
    TheLoaiDAO theLoaiDAO;
    TextInputLayout inputTenTheLoai,inputMoTa,inputViTri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_the_loai);
        setTitle("Thể Loại");
        edtMaTheLoai = findViewById(R.id.edtMaTheLoai1);
        edtTenTheLoai = findViewById(R.id.edtTenTheLoai1);
        edtMoTa = findViewById(R.id.edtMoTa1);
        edtVitri = findViewById(R.id.edtViTri1);

        inputTenTheLoai = findViewById(R.id.text_input_layout_tenTheLoai1);
        inputMoTa = findViewById(R.id.text_input_layout_moTa1);
        inputViTri = findViewById(R.id.text_input_layout_viTri1);

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
        if (!validateTenTheLoai() || !validateViTri()){
            return;
        }
        theLoaiDAO = new TheLoaiDAO(this);
        TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(),edtTenTheLoai.getText().toString(),edtMoTa.getText().toString(),Integer.parseInt(edtVitri.getText().toString()));
        if (theLoaiDAO.updateTheLoai(theLoai) ==1){
            Toast.makeText(this, "Cập nhật thể loại thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void huyUpdate(View view) {
        finish();
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