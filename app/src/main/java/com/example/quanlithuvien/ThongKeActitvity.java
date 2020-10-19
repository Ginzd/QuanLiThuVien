package com.example.quanlithuvien;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.dao.HoaDonChiTietDAO;

public class ThongKeActitvity extends AppCompatActivity {
    TextView tvNgay,tvThang,tvNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_actitvity);
        setTitle("Thống Kê");
        tvNgay = findViewById(R.id.tv_thongkeNgay);
        tvThang = findViewById(R.id.tv_thongkeThang);
        tvNam = findViewById(R.id.tv_thongkeNam);

        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvNgay.setText(hoaDonChiTietDAO.getDoanhThuNgay() + " VNĐ");
        tvThang.setText(hoaDonChiTietDAO.getDoanhThuTheoThang() + " VNĐ");
        tvNam.setText(hoaDonChiTietDAO.getDoanhThuTheoNam() + " VNĐ");
    }
}