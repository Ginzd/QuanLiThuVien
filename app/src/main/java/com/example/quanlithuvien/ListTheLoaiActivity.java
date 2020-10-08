package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.adapter.TheLoaiAdapter;
import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
    public List<TheLoai> list_TheLoai = new ArrayList<>();
    ListView lvTheLoai;
    TheLoaiAdapter adapter;
    TheLoaiDAO theLoaiDAO;
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