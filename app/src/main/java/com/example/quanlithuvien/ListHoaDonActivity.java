package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.adapter.HoaDonAdapter;
import com.example.quanlithuvien.dao.HoaDonDAO;
import com.example.quanlithuvien.model.HoaDon;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity {
    public List<HoaDon> list_hoadon = new ArrayList<>();
    ListView lvHoaDon;
    HoaDonAdapter adapter;
    HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        setTitle("Hóa Đơn");
        lvHoaDon =findViewById(R.id.lvHoaDon);
        hoaDonDAO = new HoaDonDAO(ListHoaDonActivity.this);
        try {
            list_hoadon = hoaDonDAO.getAllHoaDon();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new HoaDonAdapter(list_hoadon,this);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HoaDon hd = (HoaDon) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ListHoaDonActivity.this, ListHoaDonChiTietById.class);
                Bundle bundle = new Bundle();
                bundle.putString("MAHOADON", hd.getMaHoaDon());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_addcart:
                Intent intent = new Intent(ListHoaDonActivity.this,HoaDonActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        list_hoadon.clear();
        try {
            list_hoadon = hoaDonDAO.getAllHoaDon();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter.changeDataSet(list_hoadon);
    }
}