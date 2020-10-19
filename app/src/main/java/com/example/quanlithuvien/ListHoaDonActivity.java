package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

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
    HoaDonAdapter hoaDonAdapter;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        MenuItem menuItem = menu.findItem(R.id.item_searchHoaDon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập mã hóa đơn");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<HoaDon> list_hoadon1 = new ArrayList<>();
                for (HoaDon hoadon:list_hoadon){
                    if (hoadon.getMaHoaDon().toUpperCase().contains(s.toUpperCase())){
                        list_hoadon1.add(hoadon);
                    }
                }
                hoaDonAdapter = new HoaDonAdapter(list_hoadon1,ListHoaDonActivity.this);
                lvHoaDon.setAdapter(hoaDonAdapter);
                return false;
            }
        });
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