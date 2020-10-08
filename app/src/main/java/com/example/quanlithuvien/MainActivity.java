package com.example.quanlithuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


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
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
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
                Intent intent1 = new Intent(MainActivity.this,ListNguoiDungActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_theloai:
                 Intent intent2 = new Intent(MainActivity.this,ListTheLoaiActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_sach:
                 Intent intent3 = new Intent(MainActivity.this,ListSachActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_hoadon:
                Intent intent4 = new Intent(MainActivity.this,ListHoaDonActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_sachbanchay:
                Intent intent5 = new Intent(MainActivity.this,SachBanChayActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_thongke:
                Intent intent6 = new Intent(MainActivity.this,ThongKeActitvity.class);
                startActivity(intent6);
                break;

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_navview);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}