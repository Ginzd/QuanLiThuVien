package com.example.quanlithuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlithuvien.database.DataHelper;
import com.example.quanlithuvien.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DataHelper dataHelper;

    public static final String TABLE_NAME = "TheLoai";

    public TheLoaiDAO(Context context) {
        dataHelper = new DataHelper(context);
        db = dataHelper.getWritableDatabase();
    }

    public int insertTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());
        values.put("vitri", theLoai.getViTri());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e("Error Insert", ex.toString());
        }
        return 1;
    }

    public List<TheLoai> getAllTheLoai() {
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        List<TheLoai> list_TheLoai = new ArrayList<>();
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TheLoai the_loai = new TheLoai();
            the_loai.setMaTheLoai(c.getString(0));
            the_loai.setTenTheLoai(c.getString(1));
            the_loai.setMoTa(c.getString(2));
            the_loai.setViTri(c.getInt(3));
            list_TheLoai.add(the_loai);
            c.moveToNext();
        }
        c.close();
        return list_TheLoai;
    }

    public int updateTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());
        values.put("vitri", theLoai.getViTri());
        int result = db.update(TABLE_NAME, values, "maTheLoai=?", new String[]{theLoai.getMaTheLoai()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteTheLoai(String maTheLoai) {
        int result = db.delete(TABLE_NAME, "maTheLoai=?", new String[]{maTheLoai});
        if (result < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
