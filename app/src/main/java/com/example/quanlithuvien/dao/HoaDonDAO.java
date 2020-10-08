package com.example.quanlithuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlithuvien.database.DataHelper;
import com.example.quanlithuvien.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private DataHelper dbHelper;

    public static final String TABLE_NAME = "HoaDon";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public HoaDonDAO(Context context){
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hd.getMaHoaDon());
        values.put("ngayMua",dateFormat.format(hd.getNgayMua()));
        try {
            if (db.insert(TABLE_NAME,null,values)==-1){
                return -1;
            }
        }catch (Exception e){
            Log.e("Error InsertHoaDon",e.toString());
        }
        return 1;
    }
    public List<HoaDon> getAllHoaDon() throws ParseException {
        Cursor c = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        List<HoaDon> hoaDonList = new ArrayList<>();
        c.moveToFirst();
        while (c.isAfterLast()==false){
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(c.getString(0));
            hd.setNgayMua(dateFormat.parse(c.getString(1)));
            hoaDonList.add(hd);
            Log.d("Error",hd.toString());
            c.moveToNext();
        }
        c.close();
        return hoaDonList;
    }
    public int updateHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hd.getMaHoaDon());
        values.put("ngayMua",dateFormat.format(hd.getNgayMua()));
        int result = db.update(TABLE_NAME,values,"maHoaDon=?",new String[]{hd.getMaHoaDon()});
        if (result==0){
            return -1;
        }
        return 1;
    }
    public int deleteHoaDon(String maHoaDon){
        int result = db.delete(TABLE_NAME,"maHoaDon=?",new String[]{maHoaDon});
        if (result==0){
            return -1;
        }else {
            return 1;
        }
    }

}

