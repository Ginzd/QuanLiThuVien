package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlithuvien.adapter.AdapterNguoiDung;
import com.example.quanlithuvien.dao.NguoiDungDao;
import com.example.quanlithuvien.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {
    private List<NguoiDung> dsNguoiDung = new ArrayList<>();
    private NguoiDungDao nguoiDungDao;
    private RecyclerView recyclerView;
    private AdapterNguoiDung adapterNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);
        setTitle("Người Dùng");
        recyclerView = findViewById(R.id.recyclerViewNguoiDung);

        nguoiDungDao = new NguoiDungDao(ListNguoiDungActivity.this);
        dsNguoiDung = nguoiDungDao.getAllnguoiDung();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterNguoiDung = new AdapterNguoiDung(ListNguoiDungActivity.this,dsNguoiDung);
        recyclerView.setAdapter(adapterNguoiDung);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adduser,menu);
        MenuItem searchItem = menu.findItem(R.id.item_searchUser);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Nhập tên sách");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterNguoiDung.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_user_menu:
                Intent intent = new Intent(ListNguoiDungActivity.this,NguoiDungActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}