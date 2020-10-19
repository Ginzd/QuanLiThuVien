package com.example.quanlithuvien;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.adapter.CartHoaDonAdapter;
import com.example.quanlithuvien.dao.HoaDonChiTietDAO;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.HoaDon;
import com.example.quanlithuvien.model.HoaDonChiTiet;
import com.example.quanlithuvien.model.Sach;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    TextView edtMaHoaDon;
    EditText edtSoLuong;
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
    Dialog dialog;
    TextInputLayout textInputSoluongSachMua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        dialog = new Dialog(this);
        spnMaSach = findViewById(R.id.spnMaSachCT);
        edtMaHoaDon = findViewById(R.id.edtMaHoaDonCT);
        edtSoLuong = findViewById(R.id.edtSoLuongMuaCT);
        lvCart = findViewById(R.id.lvCartHoaDon);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        textInputSoluongSachMua = findViewById(R.id.text_input_layout_soLuongSachMua);
        sachDAO = new SachDAO(this);
        sachList = sachDAO.getAllSach();
        ArrayAdapter<Sach> adapterSach = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sachList);
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

        adapter = new CartHoaDonAdapter(list_hdct, this);
        lvCart.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            edtMaHoaDon.setText(bundle.getString("MAHOADON"));
        }
        lvCart.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                thanhTien = 0;
                try {
                    for (HoaDonChiTiet hd : list_hdct) {
                        thanhTien = thanhTien + hd.getSoLuongMua() *
                                hd.getSach().getGiaBia();
                    }
                    tvThanhTien.setText("Tổng tiền: " + thanhTien + " VNĐ");
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });
        lvCart.removeOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                thanhTien = 0;
                try {
                    for (HoaDonChiTiet hd : list_hdct) {
                        thanhTien = thanhTien + hd.getSoLuongMua() *
                                hd.getSach().getGiaBia();
                    }
                    tvThanhTien.setText("Tổng tiền: " + thanhTien + " VNĐ");
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });
    }

    public void AddHoaDonCT(View view) {
        if (!validateSoSachMua()) {
            return;
        }
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        sachDAO = new SachDAO(HoaDonChiTietActivity.this);
        Sach sach = sachDAO.getSachByID(maSach);
        if (sach != null) {
            int pos = checkMaSach(list_hdct, maSach);
            HoaDon hd = new HoaDon(edtMaHoaDon.getText().toString(), new Date());
            HoaDonChiTiet hdct = new HoaDonChiTiet(1, hd, sach, Integer.parseInt(edtSoLuong.getText().toString()));
            if (pos >= 0) {
                int soLuong = list_hdct.get(pos).getSoLuongMua();
                hdct.setSoLuongMua(soLuong + Integer.parseInt(edtSoLuong.getText().toString()));
                list_hdct.set(pos, hdct);
            } else {
                list_hdct.add(hdct);
            }
            adapter.changeDataSet(list_hdct);
        } else {
            Toast.makeText(this, "ID Book No Exists", Toast.LENGTH_SHORT).show();
        }

    }

    public int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd : list_hdct) {
                hoaDonChiTietDAO.insertHoaDonCT(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien + "VNĐ");
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
        if (thanhTien > 0) {
            hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
            list_hdct = hoaDonChiTietDAO.getThanhToanHoaDonCT();
            adapter = new CartHoaDonAdapter(list_hdct, this);
            lvCart.setAdapter(adapter);
        }
        openThanhToanDialog();
    }

    private void openThanhToanDialog() {
        dialog.setContentView(R.layout.dialog_thanh_toan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hd = edtMaHoaDon.getText().toString();
                Intent intent = new Intent(HoaDonChiTietActivity.this, ListHoaDonChiTietById.class);
                Bundle bundle = new Bundle();
                bundle.putString("MAHOADON", hd);
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private boolean validateSoSachMua() {
        String val = textInputSoluongSachMua.getEditText().getText().toString().trim();
        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            textInputSoluongSachMua.setError("Số sách cần mua phải là số");
            return false;
        }
        if (val.isEmpty()) {
            textInputSoluongSachMua.setError("Số sách cần mua không để trống !");
            return false;
        } else {
            textInputSoluongSachMua.setError(null);
            textInputSoluongSachMua.setErrorEnabled(false);
            return true;
        }
    }


}