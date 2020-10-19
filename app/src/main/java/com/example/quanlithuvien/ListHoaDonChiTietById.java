package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.adapter.CartHoaDonAdapter;
import com.example.quanlithuvien.dao.HoaDonChiTietDAO;
import com.example.quanlithuvien.model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietById extends AppCompatActivity {
    List<HoaDonChiTiet> list_hdct = new ArrayList<>();
    ListView lvCartCT;
    CartHoaDonAdapter adapter =null;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hóa Đơn Chi Tiết");
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_id);
        lvCartCT = findViewById(R.id.lvHoaDonCT);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ListHoaDonChiTietById.this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            list_hdct = hoaDonChiTietDAO.getAllHDCTbyID(bundle.getString("MAHOADON"));
        }

        adapter = new CartHoaDonAdapter(list_hdct,this);
        lvCartCT.setAdapter(adapter);
        adapter.changeDataSet(list_hdct);
    }

}