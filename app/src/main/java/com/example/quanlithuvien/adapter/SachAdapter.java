package com.example.quanlithuvien.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.quanlithuvien.LoginActivity;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.UpdateSachActivity;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.Sach;
import com.example.quanlithuvien.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends BaseAdapter{
    private List<Sach> list_sach;
    private Activity context;
    private LayoutInflater inflater;
    private SachDAO sachDAO;
    int m_index;
    public SachAdapter(List<Sach> list_sach, Activity context) {
        this.list_sach = list_sach;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sachDAO = new SachDAO(context);
    }

    @Override
    public int getCount() {
        return list_sach.size();
    }

    @Override
    public Object getItem(int i) {
        return list_sach.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        ImageView img;
        TextView txtBookName;
        TextView txtBookPrice;
        TextView txtBookSoLuong;
        ImageView imgDelete;
        ImageView imgEdit;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.sach_item,null);
            holder.img = view.findViewById(R.id.ivIconBook);
            holder.txtBookName = view.findViewById(R.id.tvBookName);
            holder.txtBookPrice = view.findViewById(R.id.tvBookPrice);
            holder.txtBookSoLuong = view.findViewById(R.id.tvSoLuongBook);
            holder.imgDelete = view.findViewById(R.id.ivDeleteBook);
            holder.imgEdit = view.findViewById(R.id.ivEditSach);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateSachActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MASACH",list_sach.get(i).getMaSach());
                    bundle.putString("MATHELOAI",list_sach.get(i).getMaTheLoai());
                    bundle.putString("TENSACH",list_sach.get(i).getTenSach());
                    bundle.putString("TACGIA",list_sach.get(i).getTacGia());
                    bundle.putString("NXB",list_sach.get(i).getNXB());
                    bundle.putString("GIABIA",String.valueOf(list_sach.get(i).getGiaBia()));
                    bundle.putString("SOLUONG",String.valueOf(list_sach.get(i).getSoLuong()));
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m_index = i;
                    try{
                        showDialog("Bạn có chắc muốn xóa sách này ?");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            view.setTag(holder);
        }
        holder = (ViewHolder)view.getTag();
        Sach entry_sach = list_sach.get(i);
        holder.img.setImageResource(R.drawable.bookicon1);
        holder.txtBookName.setText("Ma sach:"+entry_sach.getMaSach());
        holder.txtBookSoLuong.setText("So Luong:"+entry_sach.getSoLuong());
        holder.txtBookPrice.setText("Gia:"+entry_sach.getGiaBia());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<Sach> items_listSach){
        this.list_sach = items_listSach;
        notifyDataSetChanged();
    }

    public void showDialog(final String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Xac Nhan: " + text);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

                Sach sach = list_sach.get(m_index);
                sachDAO.deleteSach(list_sach.get(m_index).getMaSach());
                list_sach.remove(sach);
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
