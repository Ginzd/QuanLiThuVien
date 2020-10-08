package com.example.quanlithuvien;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.database.DataHelper;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName,edPass;
    DataHelper my_db;
    Boolean savelogin;
    CheckBox rememberCheckbox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng Nhập");
        edUserName = findViewById(R.id.edtUserNameLogin);
        edPass = findViewById(R.id.edtPassWordLogin);
        rememberCheckbox = findViewById(R.id.chkRememberLogin);
        my_db = new DataHelper(this);
        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        savelogin = sharedPreferences.getBoolean("savelogin",true);
        if (savelogin == true){
            edUserName.setText(sharedPreferences.getString("username",null));
            edPass.setText(sharedPreferences.getString("password",null));
        }
    }


    public void btnDangKi(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_dangki);
        final EditText edDangkiUser = dialog.findViewById(R.id.edDangkiUserName);
        final EditText edDangkiMK = dialog.findViewById(R.id.edDangkiMk);
        final EditText edDangkiReMK = dialog.findViewById(R.id.edDangkiReMK);
        final Button btnDangki = dialog.findViewById(R.id.btnDangKiDiaglog);
        final Button btnHuyDangki = dialog.findViewById(R.id.btnHuyDangKiDiaglog);
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edDangkiUser.getText().toString();
                String pass = edDangkiMK.getText().toString();
                String repass = edDangkiReMK.getText().toString();
                if ((user.equals("")) || (pass.equals("")) || (repass.equals(""))){
                    Toast.makeText(LoginActivity.this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = my_db.checkuserName(user);
                        if (checkuser == false) {
                            Boolean insert = my_db.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(LoginActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(LoginActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuyDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void checkLogin(View view) {
        String user = edUserName.getText().toString();
        String pass = edPass.getText().toString();
        if ((user.equals("Admin")) || (pass.equals("Admin"))){
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            },500);
        }else {
            Boolean checkuserPass = my_db.checkUserPassWord(user,pass);
            if (checkuserPass == true){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                },500);
            }else {
                Toast.makeText(this, "Người dùng không tồn tại vui lòng đăng kí", Toast.LENGTH_SHORT).show();
            }
        }
        if(rememberCheckbox.isChecked()){
            editor.putBoolean("savelogin",true);
            editor.putString("username",user);
            editor.putString("password",pass);
            editor.commit();
        }
    }


}