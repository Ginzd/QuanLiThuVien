package com.example.quanlithuvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SachBanChayActivity extends AppCompatActivity {
    RecyclerView recycler_topbook;
    ArrayList<MainModelBook> mainModelBooks;
    SellingBookAdapter sellingBookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);
        setTitle("Sách");
        recycler_topbook = findViewById(R.id.recycler_TopBook);
        Integer[] langLogo = {R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1,R.drawable.bookicon1};
        String[] langName = {"Tôi tài giỏi, bạn cũng thế ","Đắc nhân tâm","Tội ác và trừng phạt "," Nhà giả kim"," Bắt trẻ đồng xanh ","Xách ba lô lên và đi","Cứ đi rồi sẽ đến ","7 thói quen để thành đạt","Thép đã tôi thế đấy "," Đọc vị bất kì ai","Tại Sao Phương Tây Vượt Trội?","Hài Hước Một Chút Thế Giới Sẽ Khác Đi","Tôi Thấy Hoa Vàng Trên Cỏ Xanh (Bản In Mới - 2018)","Từ Tốt Đến Vĩ Đại - Jim Collins (Tái Bản 2019)"};

        mainModelBooks = new ArrayList<>();
        for (int i = 0;i <langLogo.length;i++){
            MainModelBook modelBook = new MainModelBook(langLogo[i],langName[i]);
            mainModelBooks.add(modelBook);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                SachBanChayActivity.this,LinearLayoutManager.VERTICAL,false
        );
        recycler_topbook.setLayoutManager(layoutManager);
        recycler_topbook.setItemAnimator(new DefaultItemAnimator());


        sellingBookAdapter = new SellingBookAdapter(mainModelBooks,this);

        recycler_topbook.setAdapter(sellingBookAdapter);
    }
}