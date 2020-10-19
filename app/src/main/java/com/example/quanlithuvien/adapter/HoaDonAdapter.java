package com.example.quanlithuvien.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.quanlithuvien.ListHoaDonChiTietById;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.UpdateHoaDonActivity;
import com.example.quanlithuvien.dao.HoaDonDAO;
import com.example.quanlithuvien.model.HoaDon;

import java.text.SimpleDateFormat;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private List<HoaDon> hoaDonList;
    private Activity context;
    private LayoutInflater inflater;
    private HoaDonDAO hoaDonDAO;
    private int m_index;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonAdapter(List<HoaDon> hoaDonList, Activity context) {
        super();
        this.hoaDonList = hoaDonList;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonDAO = new HoaDonDAO(context);
    }

    @Override
    public int getCount() {
        return hoaDonList.size();
    }

    @Override
    public Object getItem(int i) {
        return hoaDonList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        ImageView img;
        TextView txtMaHoaDon;
        TextView txtNgayMua;
        ImageView imgDelete;
        ImageView imgDetailHd;
        ImageView imgEdit;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_hoadon,null);
            holder.img = view.findViewById(R.id.ivIconHoaDon);
            holder.txtMaHoaDon = view.findViewById(R.id.tv_maHoaDon);
            holder.txtNgayMua = view.findViewById(R.id.tvNgayMua);
            holder.imgDelete = view.findViewById(R.id.ivDeleteHoaDon);
            holder.imgDetailHd = view.findViewById(R.id.ivDetailHD);
            holder.imgEdit = view.findViewById(R.id.ivEditHoaDon);
            holder.imgDetailHd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HoaDon hd = hoaDonList.get(i);
                    Intent intent = new Intent(context, ListHoaDonChiTietById.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MAHOADON", hd.getMaHoaDon());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m_index = i;
                    try{
                        showDialog("Bạn có chắc muốn xoá hóa đơn này ?");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateHoaDonActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MAHOADON", hoaDonList.get(i).getMaHoaDon());
                    bundle.putString("NGAYMUA", dateFormat.format(hoaDonList.get(i).getNgayMua()));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();

        HoaDon entry_hd = hoaDonList.get(i);
        holder.img.setImageResource(R.drawable.cart_ion1);
        holder.txtMaHoaDon.setText(entry_hd.getMaHoaDon());
        holder.txtNgayMua.setText(dateFormat.format(entry_hd.getNgayMua()));
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<HoaDon> items){
        this.hoaDonList = items;
        notifyDataSetChanged();
    }
    public void showDialog(final String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("" + text);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

                HoaDon hd = hoaDonList.get(m_index);
                hoaDonDAO.deleteHoaDon(hoaDonList.get(m_index).getMaHoaDon());
                hoaDonList.remove(hd);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
