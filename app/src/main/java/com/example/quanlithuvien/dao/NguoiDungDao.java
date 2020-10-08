package com.example.quanlithuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlithuvien.database.DataHelper;
import com.example.quanlithuvien.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDao {
    public DataHelper dbHelper;
    public SQLiteDatabase db;

    public static final String TABLE_NAME = "NguoiDung";
    public NguoiDungDao(Context context) {
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertNguoiDung(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("userName",nguoiDung.getUserName());
        values.put("password",nguoiDung.getPassword());
        values.put("phone",nguoiDung.getPhone());
        values.put("hoTen",nguoiDung.getHoTen());
        try {
            if (db.insert(TABLE_NAME,null,values)==-1){
                return -1;
            }
        }catch (Exception ex){
            Log.e("NguoiDungDAO",ex.getMessage());
        }
        return 1;
    }

    public List<NguoiDung> getAllnguoiDung() {
        Cursor cursor = db.query(TABLE_NAME, null,
                null, null, null, null, null);
        List<NguoiDung> ls = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setUserName(cursor.getString(0));
            nguoiDung.setPassword(cursor.getString(1));
            nguoiDung.setPhone(cursor.getString(2));
            nguoiDung.setHoTen(cursor.getString(3));
            ls.add(nguoiDung);
            cursor.moveToNext();
        }
        cursor.close();
        return ls;
    }

    public int deleteUser(String userName){
        int result = db.delete("NguoiDung","userName=?",new String[]{userName});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int updateUser(String userName,String phone,String hoTen){
        ContentValues values = new ContentValues();
        values.put("phone",phone);
        values.put("hoTen",hoTen);
        if (db.update(TABLE_NAME,values,"userName=?",new String[]{userName})<=0){
            return -1;
        }
        return 1;
    }

}
