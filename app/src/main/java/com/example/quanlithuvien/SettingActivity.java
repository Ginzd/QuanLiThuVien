package com.example.quanlithuvien;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.model.NguoiDung;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Thiết Lập");
    }

    public void clickDoiMK(View view) {
        Intent intent = new Intent(this,ChangePasswordActivity.class);
        startActivity(intent);
    }
    public void clickDangXuat(View view) {
        try{
            showDialog("Bạn có chắc muốn đăng xuất ?");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showDialog(final String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

        builder.setMessage("" + text);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

                Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }
}