package com.example.quanlithuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.quanlithuvien.database.DataHelper;
import com.example.quanlithuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    private DataHelper dataHelper;

    public static final String TABLE_NAME = "Sach";
    public SachDAO(Context context) {
        dataHelper = new DataHelper(context);
        db = dataHelper.getWritableDatabase();
    }
    public int inserSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maSach",s.getMaSach());
        values.put("maTheLoai",s.getMaTheLoai());
        values.put("tensach",s.getTenSach());
        values.put("tacGia",s.getTacGia());
        values.put("NXB",s.getNXB());
        values.put("giaBia",s.getGiaBia());
        values.put("soLuong",s.getSoLuong());
        if (checkPrimaryKey(s.getMaSach())){
            int result = db.update(TABLE_NAME,values,"masach=?", new
                    String[]{s.getMaSach()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TABLE_NAME, ex.toString());
            }
        }
        return 1;
    }
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"masach"};
        //WHERE clause
        String selection = "masach=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<Sach> getAllSach(){
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<Sach> list_sach = new ArrayList<>();
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Sach sach = new Sach();
            sach.setMaSach(c.getString(0));
            sach.setMaTheLoai(c.getString(1));
            sach.setTenSach(c.getString(2));
            sach.setTacGia(c.getString(3));
            sach.setNXB(c.getString(4));
            sach.setGiaBia(c.getDouble(5));
            sach.setSoLuong(c.getInt(6));
            list_sach.add(sach);
            c.moveToNext();
        }
        c.close();
        return list_sach;
    }
    public int updateSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("maSach",sach.getMaSach());
        values.put("maTheLoai",sach.getMaTheLoai());
        values.put("tensach",sach.getTenSach());
        values.put("tacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
        int result = db.update(TABLE_NAME,values,"maSach=?",new String[]{sach.getMaSach()});
        if (result == 0){
            return -1;
        }else {
            return 1;
        }
    }
    public int deleteSach(String maSach){
        int result = db.delete(TABLE_NAME,"maSach=?",new String[]{maSach});
        if (result ==0){
            return -1;
        }else {
            return 1;
        }
    }
    public Sach getSachByID(String maSach){
        Sach s = null;
        String selection = "maSach=?";
        String[] selectionArgs = {maSach};
        Cursor c = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }

}
