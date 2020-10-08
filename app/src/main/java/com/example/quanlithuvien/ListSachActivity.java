package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.quanlithuvien.adapter.SachAdapter;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListSachActivity extends AppCompatActivity {
    public List<Sach> list_sach;
    ListView lvBook;
    SachAdapter sachAdapter;
    SachDAO sachDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sach);
        setTitle("Sách");
        lvBook = findViewById(R.id.lvBook);
        list_sach = new ArrayList<>();

        sachDAO = new SachDAO(this);
        list_sach = sachDAO.getAllSach();

        sachAdapter = new SachAdapter(list_sach,this);
        lvBook.setAdapter(sachAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_book_menu:
                Intent intent = new Intent(ListSachActivity.this,SachActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        list_sach.clear();
        list_sach = sachDAO.getAllSach();
        sachAdapter.changeDataSet(list_sach);

    }
}