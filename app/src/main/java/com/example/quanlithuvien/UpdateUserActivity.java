package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.NguoiDungDao;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateUserActivity extends AppCompatActivity {
    EditText edtPass, edtPhone, edtFullName;
    TextView edtUser;
    Button btnUp2,btnHuy2;
    NguoiDungDao nguoiDungDao;
    TextInputLayout inputMK, inputSDT, inputFullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        setTitle("Người Dùng");
        edtUser = findViewById(R.id.edtUserNameNguoiDung2);
        edtPass = findViewById(R.id.edtPassNguoiDung2);
        edtPhone = findViewById(R.id.edtPhoneNguoiDung2);
        edtFullName = findViewById(R.id.edtFullNameNguoiDung2);

        inputMK = findViewById(R.id.text_input_layout_matkhauUser2);
        inputSDT = findViewById(R.id.text_input_layout_sdtUser2);
        inputFullName = findViewById(R.id.text_input_layout_fullnameUser2);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            edtUser.setText(bundle.getString("userName_key"));
            edtPass.setText(bundle.getString("password_key"));
            edtPhone.setText(bundle.getString("phone_key"));
            edtFullName.setText(bundle.getString("hoTen_key"));
        }
        btnUp2 = findViewById(R.id.btnUpdate2);
        btnUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateMK() || !validatePhoneNumber() || !validateFullName()) {
                    return;
                }
                nguoiDungDao = new NguoiDungDao(UpdateUserActivity.this);
                if (nguoiDungDao.updateUser(edtUser.getText().toString(),edtPhone.getText().toString(),
                        edtFullName.getText().toString())==1){
                    Toast.makeText(UpdateUserActivity.this, "Cập nhật người dùng thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateUserActivity.this,ListNguoiDungActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(UpdateUserActivity.this, "Cập nhật thất bại - Update theo ten", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy2 = findViewById(R.id.btnHuy2);
        btnHuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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