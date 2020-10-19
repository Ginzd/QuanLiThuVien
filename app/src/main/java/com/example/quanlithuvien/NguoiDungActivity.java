package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.NguoiDungDao;
import com.example.quanlithuvien.model.NguoiDung;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class NguoiDungActivity extends AppCompatActivity {
    Button btnAddNguoiDung;
    NguoiDungDao nguoiDungDao;
    EditText edtUser, edtPass, edtPhone, edtFullName;
    ArrayList<NguoiDung> listUser;
    TextInputLayout inputTenTK, inputMK, inputSDT, inputFullName;

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

        inputTenTK = findViewById(R.id.text_input_layout_tenUser);
        inputMK = findViewById(R.id.text_input_layout_matkhauUser);
        inputSDT = findViewById(R.id.text_input_layout_sdtUser);
        inputFullName = findViewById(R.id.text_input_layout_fullnameUser);

    }

    public void addNguoiDung(View view) {
        if (!validateNameUser() || !validateMK() || !validatePhoneNumber() || !validateFullName()) {
            return;
        }
        nguoiDungDao = new NguoiDungDao(NguoiDungActivity.this);
        NguoiDung nguoiDung = new NguoiDung(edtUser.getText().toString(),
                edtPass.getText().toString(), edtPhone.getText().toString(),
                edtFullName.getText().toString());
        if (nguoiDungDao.insertNguoiDung(nguoiDung) == 1) {
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListNguoiDungActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
        }

    }

    public void ShowUsers(View view) {
        finish();
    }

    private boolean validateNameUser() {
        String val = inputTenTK.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            inputTenTK.setError("Tên tài khoản không để trống !");
            return false;
        } else {
            inputTenTK.setError(null);
            inputTenTK.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateMK() {
        String val = inputMK.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            inputMK.setError("Mật khẩu không để trống !");
            return false;
        } else {
            inputMK.setError(null);
            inputMK.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFullName() {
        String val = inputFullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            inputFullName.setError("Họ và tên không để trống !");
            return false;
        } else {
            inputFullName.setError(null);
            inputFullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhoneNumber() {
        String val = inputSDT.getEditText().getText().toString().trim();
        String checkspaces = "^0\\d{9}$";
        if (val.isEmpty()) {
            inputSDT.setError("Số điện thoại không để trống");
            return false;
        } else if (!val.matches(checkspaces)) {
            inputSDT.setError("Số điện thoại cần 10 chữ số và bằng đầu bằng số 0 ");
            return false;
        } else {
            inputSDT.setError(null);
            inputSDT.setErrorEnabled(false);
            return true;
        }
    }
}