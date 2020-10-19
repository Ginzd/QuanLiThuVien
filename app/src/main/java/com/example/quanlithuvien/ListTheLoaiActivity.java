package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.adapter.TheLoaiAdapter;
import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
    public static List<TheLoai> list_TheLoai = new ArrayList<>();
    ListView lvTheLoai;
    TheLoaiAdapter adapter;
    TheLoaiDAO theLoaiDAO;
    TheLoaiAdapter theLoaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);
        setTitle("Thể Loại");
        lvTheLoai = findViewById(R.id.lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(ListTheLoaiActivity.this);
        list_TheLoai = theLoaiDAO.getAllTheLoai();

        adapter = new TheLoaiAdapter(list_TheLoai,this);
        lvTheLoai.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai,menu);
        MenuItem menuItem = menu.findItem(R.id.item_searchTheLoai);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập tên thể loại");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                List<TheLoai> list_theLoai1 = new ArrayList<>();
                for (TheLoai theloai:list_TheLoai){
                    if (theloai.getTenTheLoai().toUpperCase().contains(s.toUpperCase())){
                        list_theLoai1.add(theloai);
                    }
                }
                theLoaiAdapter = new TheLoaiAdapter(list_theLoai1,ListTheLoaiActivity.this);
                lvTheLoai.setAdapter(theLoaiAdapter);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemThemTheLoai:
                Intent intent = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        list_TheLoai.clear();
        list_TheLoai = theLoaiDAO.getAllTheLoai();
        adapter.changeDataSet(list_TheLoai);
    }

}