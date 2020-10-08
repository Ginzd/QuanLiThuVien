package com.example.quanlithuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quanlithuvien.adapter.CartHoaDonAdapter;
import com.example.quanlithuvien.dao.HoaDonChiTietDAO;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.HoaDon;
import com.example.quanlithuvien.model.HoaDonChiTiet;
import com.example.quanlithuvien.model.Sach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edtMaHoaDon,edtSoLuong;
    Spinner spnMaSach;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SachDAO sachDAO;
    ListView lvCart;
    List<HoaDonChiTiet> list_hdct = new ArrayList<>();
    List<Sach> sachList = new ArrayList<>();
    CartHoaDonAdapter adapter;
    double thanhTien = 0;
    String maSach = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        spnMaSach = findViewById(R.id.spnMaSachCT);
        edtMaHoaDon = findViewById(R.id.edtMaHoaDonCT);
        edtSoLuong = findViewById(R.id.edtSoLuongMuaCT);
        lvCart = findViewById(R.id.lvCartHoaDon);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        sachDAO = new SachDAO(this);
        sachList = sachDAO.getAllSach();
        ArrayAdapter<Sach> adapterSach = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,sachList);
        spnMaSach.setAdapter(adapterSach);
        spnMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = sachList.get(spnMaSach.getSelectedItemPosition()).getMaSach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter = new CartHoaDonAdapter(list_hdct,this);
        lvCart.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            edtMaHoaDon.setText(bundle.getString("MAHOADON"));
        }
    }

    public void AddHoaDonCT(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        sachDAO = new SachDAO(HoaDonChiTietActivity.this);
//        Sach sach = sachDAO.getSachByID(edtMaSach.getText().toString());
        Sach sach = sachDAO.getSachByID(maSach);
        if (sach != null){
//            int pos = checkMaSach(list_hdct,edtMaSach.getText().toString());
            int pos = checkMaSach(list_hdct,maSach);
            HoaDon hd = new HoaDon(edtMaHoaDon.getText().toString(),new Date());
            HoaDonChiTiet hdct = new HoaDonChiTiet(1,hd,sach,Integer.parseInt(edtSoLuong.getText().toString()));
            if (pos >= 0){
                int soLuong = list_hdct.get(pos).getSoLuongMua();
                hdct.setSoLuongMua(soLuong + Integer.parseInt(edtSoLuong.getText().toString()));
                list_hdct.set(pos,hdct);
            }else {
                list_hdct.add(hdct);
            }
            adapter.changeDataSet(list_hdct);
        }else {
            Toast.makeText(this, "ID Book No Exists", Toast.LENGTH_SHORT).show();
        }
    }
    public int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach){
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++){
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)){
                pos = i;
                break;
            }
        }
        return pos;
    }
    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd: list_hdct) {
                hoaDonChiTietDAO.insertHoaDonCT(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " +thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
}