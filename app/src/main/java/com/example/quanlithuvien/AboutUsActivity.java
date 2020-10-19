package com.example.quanlithuvien;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription(" Ứng Dụng Quản Lí Thư Viện ")
                .addItem(new Element().setTitle("Phiên bản 1.0"))
                .addGroup("Kết nối với tôi!")
                .addEmail("dinhmtph11567@fpt.edu.vn ")
                .addWebsite("https://caodang.fpt.edu.vn/")
                .addYoutube("https://www.youtube.com/channel/UCA-FrnG3RiD9xwxhdrvNwPg?view_as=subscriber")
                .addPlayStore("com.example.quanlithuvien")
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }
    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d by Mạc Trung Đỉnh", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
    
}