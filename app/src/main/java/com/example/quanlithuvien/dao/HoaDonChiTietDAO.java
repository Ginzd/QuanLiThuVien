package com.example.quanlithuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlithuvien.database.DataHelper;
import com.example.quanlithuvien.model.HoaDon;
import com.example.quanlithuvien.model.HoaDonChiTiet;
import com.example.quanlithuvien.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private DataHelper dbHelper;

    public static final String TABLE_NAME = "HoaDonChiTiet";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public HoaDonChiTietDAO(Context context){
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertHoaDonCT(HoaDonChiTiet hdct){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hdct.getHoaDon().getMaHoaDon());
        values.put("maSach",hdct.getSach().getMaSach());
        values.put("soLuong",hdct.getSoLuongMua());
        if (db.insert(TABLE_NAME,null,values)==-1){
            return -1;
        }
        return 1;
    }
    public List<HoaDonChiTiet> getAllHoaDonCT(){
        List<HoaDonChiTiet> dsHoaDonCT = new ArrayList<>();
        String my_SQL =  "SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua,Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB, Sach.giaBia, " +
                "Sach.soLuong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach";
        Cursor c = db.rawQuery(my_SQL,null);
        c.moveToFirst();
        try {
            while (c.isAfterLast() == false){
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHDCT(c.getInt(0));
                hdct.setHoaDon(new HoaDon(c.getString(1),dateFormat.parse(c.getString(2))));
                hdct.setSach(new Sach(c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                        c.getString(7),c.getInt(8),c.getInt(9)));
                hdct.setSoLuongMua(c.getInt(10));
                dsHoaDonCT.add(hdct);
                Log.d("InsertHoaDonCT",hdct.toString());
                c.moveToNext();
            }
            c.close();
        }catch (Exception e){
            Log.d("Error InsertHoaDonCT",e.toString());
        }
        return dsHoaDonCT;
    }
    public List<HoaDonChiTiet> getAllHDCTbyID(String maHoaDon){
        List<HoaDonChiTiet> list_hdct = new ArrayList<>();
        String my_SQL = "SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua, " +
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB, Sach.giaBia, " +
                "Sach.soLuong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach where HoaDonChiTiet.maHoaDon='"+maHoaDon+"'";
        Cursor c = db.rawQuery(my_SQL,null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHDCT(c.getInt(0));
                hdct.setHoaDon(new HoaDon(c.getString(1),dateFormat.parse(c.getString(2))));
                hdct.setSach(new Sach(c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getInt(8),c.getInt(9)));
                hdct.setSoLuongMua(c.getInt(10));
                list_hdct.add(hdct);
                c.moveToNext();
            }
            c.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list_hdct;
    }

    public int updateHoaDonCT(HoaDonChiTiet hdct){
        ContentValues values = new ContentValues();
        values.put("maHDCT",hdct.getMaHDCT());
        values.put("maHoaDon",hdct.getHoaDon().getMaHoaDon());
        values.put("maSach",hdct.getSach().getMaSach());
        values.put("soLuong",hdct.getSoLuongMua());
        int result = db.update(TABLE_NAME,values,"maHDCT=?",new String[]{String.valueOf(hdct.getMaHDCT())});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public int deleteHoaDonCT(String maHDCT){
        int result = db.delete(TABLE_NAME,"maHDCT=?",new String[]{maHDCT});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public boolean checkHoaDon(String maHoaDon){
        String[] columns = {"maHoaDon"};
        String selection = "maHoaDon=?";
        String[] selectionArgs = {maHoaDon};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs,null,null,null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i<=0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public double getDoanhThuNgay(){
        double doanhThu = 0;
        String m_SQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where HoaDon.ngayMua = date('now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor c = db.rawQuery(m_SQL,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%m',HoaDon.ngayMua) = strftime('%m','now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%Y',HoaDon.ngayMua) = strftime('%Y','now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

}
