package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.adapter.SachAdapter;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListSachActivity extends AppCompatActivity {
    public static List<Sach> list_sach;
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
        sachAdapter = new SachAdapter(list_sach, this);
        lvBook.setAdapter(sachAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView my_SearchView = (SearchView) searchItem.getActionView();
        my_SearchView.setQueryHint("Nhập tên sách");
        my_SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String new_text) {
//                List<Sach> list_sach1 = new ArrayList<>();
//                for (Sach userSach:list_sach){
//                    if (userSach.getTenSach().toUpperCase().contains(new_text.toUpperCase())){
//                        list_sach1.add(userSach);
//                    }
//
//                }
                String text_seach = new_text.toUpperCase();
                List<Sach> list_sach1 = sachDAO.timKiemSach(text_seach);
                sachAdapter = new SachAdapter(list_sach1, ListSachActivity.this);
                lvBook.setAdapter(sachAdapter);

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_book_menu:
                Intent intent = new Intent(ListSachActivity.this, SachActivity.class);
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