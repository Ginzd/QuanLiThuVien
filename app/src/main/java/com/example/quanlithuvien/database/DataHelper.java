package com.example.quanlithuvien.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlithuvien.dao.HoaDonChiTietDAO;
import com.example.quanlithuvien.dao.HoaDonDAO;
import com.example.quanlithuvien.dao.NguoiDungDao;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.dao.TheLoaiDAO;


public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NANE = "dbBookManager";
    public static final int VERSION = 1;

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NANE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(username TEXT primary key, password TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE NguoiDung (userName TEXT NOT NULL PRIMARY KEY, password TEXT,phone TEXT, hoTen TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO NguoiDung(userName,password,phone,hoTen) VALUES ('Nam','1234','0856066333','Nguyen Nam')");
        sqLiteDatabase.execSQL("INSERT INTO NguoiDung(userName,password,phone,hoTen) VALUES ('Kim','1234','0856066359','Nguyen Kim')");
        sqLiteDatabase.execSQL("INSERT INTO NguoiDung(userName,password,phone,hoTen) VALUES ('Tuyet','1234','0856066222','Nguyen Tuyet')");
        sqLiteDatabase.execSQL("INSERT INTO NguoiDung(userName,password,phone,hoTen) VALUES ('Thach','1234','0856066111','Nguyen Thach')");

        sqLiteDatabase.execSQL("CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri int);");
        sqLiteDatabase.execSQL("INSERT INTO TheLoai(maTheLoai,tentheLoai,mota,vitri) VALUES ('TL1','Kiem Hiep','The Loai Kiem Hiep','VT1')");
        sqLiteDatabase.execSQL("INSERT INTO TheLoai(maTheLoai,tentheLoai,mota,vitri) VALUES ('TL2','Tri Tue','The Loai Kiem Hiep','VT1')");
        sqLiteDatabase.execSQL("INSERT INTO TheLoai(maTheLoai,tentheLoai,mota,vitri) VALUES ('TL3','Tinh Yeu','The Loai Kiem Hiep','VT1')");
        sqLiteDatabase.execSQL("INSERT INTO TheLoai(maTheLoai,tentheLoai,mota,vitri) VALUES ('TL4','Tieu Thuyet','The Loai Kiem Hiep','VT1')");

        sqLiteDatabase.execSQL("CREATE TABLE Sach (maSach text primary key, maTheLoai text, tensach text," +
                "tacGia text, NXB text, giaBia double, soLuong number);");
        sqLiteDatabase.execSQL("INSERT INTO Sach(maSach,maTheLoai,tensach,tacGia,NXB,giaBia,soLuong) VALUES ('MS1','22','HTML','2','5','22000 VND','11')");
        sqLiteDatabase.execSQL("INSERT INTO Sach(maSach,maTheLoai,tensach,tacGia,NXB,giaBia,soLuong) VALUES ('MS2','22','HTML','2','5','42000 VND','17')");
        sqLiteDatabase.execSQL("INSERT INTO Sach(maSach,maTheLoai,tensach,tacGia,NXB,giaBia,soLuong) VALUES ('MS3','22','HTML','2','5','52000 VND','21')");
        sqLiteDatabase.execSQL("INSERT INTO Sach(maSach,maTheLoai,tensach,tacGia,NXB,giaBia,soLuong) VALUES ('MS4','22','HTML','2','5','62000 VND','45')");

        sqLiteDatabase.execSQL("CREATE TABLE HoaDon (maHoaDon text primary key, ngayMua date);");
        sqLiteDatabase.execSQL("INSERT INTO HoaDon(maHoaDon,ngayMua) VALUES ('HD1','2020-05-05')");
        sqLiteDatabase.execSQL("INSERT INTO HoaDon(maHoaDon,ngayMua) VALUES ('HD2','2020-05-05')");
        sqLiteDatabase.execSQL("INSERT INTO HoaDon(maHoaDon,ngayMua) VALUES ('HD3','2020-05-05')");
        sqLiteDatabase.execSQL("INSERT INTO HoaDon(maHoaDon,ngayMua) VALUES ('HD4','2020-05-05')");

        sqLiteDatabase.execSQL("CREATE TABLE HoaDonChiTiet (maHDCT integer primary key autoincrement, maHoaDon text NOT NULL, maSach text NOT NULL, soLuong integer);");
        //Làm theo mở danh sách học sinh trong lớp bên android nâng cao
//        sqLiteDatabase.execSQL(HoaDonChiTietDAO.SQL_HOADONCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NguoiDungDao.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TheLoaiDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SachDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HoaDonDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HoaDonChiTietDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
    }
    public boolean insertData(String userName, String passWord){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",userName);
        contentValues.put("password",passWord);
        long result = myDB.insert("users",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean checkuserName(String userName){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username = ?",new String[]{userName});
        if (cursor.getCount() >0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkUserPassWord(String userName, String passWord){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username = ? and password = ?",new String[]{userName,passWord});
        if (cursor.getCount() >0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updatePassword(String userName, String passWord){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",passWord);
        long result = myDB.update("users",contentValues,"userName=?",new String[]{userName});
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

}
