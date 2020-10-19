package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        DrawerLayout drawer1 = findViewById(R.id.drawer_layout_navview);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer1, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer1.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemsetting:
                Intent intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

@Override
public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout_navview);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
    } else {
        super.onBackPressed();
    }
}
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_nguoidung:
                intent = new Intent(MainActivity.this,ListNguoiDungActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_theloai:
                intent = new Intent(MainActivity.this,ListTheLoaiActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_sach:
                intent = new Intent(MainActivity.this,ListSachActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_hoadon:
                intent = new Intent(MainActivity.this,ListHoaDonActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_sachbanchay:
                intent = new Intent(MainActivity.this,SachBanChayActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_thongke:
                intent = new Intent(MainActivity.this,ThongKeActitvity.class);
                startActivity(intent);
                break;
            case R.id.nav_about:
                intent = new Intent(MainActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_navview);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}