package com.example.quanlithuvien.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.quanlithuvien.R;
import com.example.quanlithuvien.dao.HoaDonChiTietDAO;
import com.example.quanlithuvien.model.HoaDonChiTiet;

import java.util.List;

public class CartHoaDonAdapter extends BaseAdapter {
    private List<HoaDonChiTiet> list_hoadonCT;
    private Activity context;
    private LayoutInflater inflater;
    private HoaDonChiTietDAO hoaDonChiTietDAO;

    public CartHoaDonAdapter(List<HoaDonChiTiet> list_hoadonCT, Activity context) {
        super();
        this.list_hoadonCT = list_hoadonCT;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return list_hoadonCT.size();
    }

    @Override
    public Object getItem(int i) {
        return list_hoadonCT.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        TextView txtMaSach;
        TextView txtSoLuong;
        TextView txtGiaBia;
        TextView txtThanhTien;
        ImageView imgDelete;
        TextView tvThanhTien;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_cart,null);
            holder.txtMaSach = view.findViewById(R.id.tv_masach);
            holder.txtSoLuong = view.findViewById(R.id.tv_soluong);
            holder.txtGiaBia = view.findViewById(R.id.tv_giabia);
            holder.txtThanhTien = view.findViewById(R.id.tv_thanhtien);
            holder.imgDelete = view.findViewById(R.id.iv_delete);
            holder.tvThanhTien = view.findViewById(R.id.tvThanhTien);
            final ViewHolder finalHolder = holder;
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HoaDonChiTiet hd = list_hoadonCT.get(i);
                    hoaDonChiTietDAO.deleteHoaDonCT(String.valueOf(list_hoadonCT.get(i).getMaHDCT()));
                    list_hoadonCT.remove(hd);
                    double thanhTien = 0;
                    try {
                        for (HoaDonChiTiet hd2 : list_hoadonCT) {
                            thanhTien = thanhTien + hd2.getSoLuongMua() *
                                    hd2.getSach().getGiaBia();
                        }
                        finalHolder.tvThanhTien.setText("Tổng tiền: " + thanhTien + " VNĐ");
                    } catch (Exception ex) {
                        Log.e("Error", ex.toString());
                    }
                    changeDataSet(list_hoadonCT);
                }
            });
            view.setTag(holder);
        }
        holder = (ViewHolder)view.getTag();
        HoaDonChiTiet entry_hdct = list_hoadonCT.get(i);
        holder.txtMaSach.setText("Mã sách:"+entry_hdct.getSach().getMaSach());
        holder.txtSoLuong.setText("Số lượng:"+entry_hdct.getSoLuongMua());
        holder.txtGiaBia.setText("Giá bìa:"+entry_hdct.getSach().getGiaBia()+"VND");
        holder.txtThanhTien.setText("Thành Tiền:"+entry_hdct.getSoLuongMua()*entry_hdct.getSach().getGiaBia()+"VND");
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<HoaDonChiTiet> items){
        this.list_hoadonCT = items;
        notifyDataSetChanged();
    }
}
