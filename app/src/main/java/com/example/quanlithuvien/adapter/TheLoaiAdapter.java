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

import com.example.quanlithuvien.ListTheLoaiActivity;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.UpdateTheLoaiActivity;
import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    private List<TheLoai> list_TheLoai;
    private Context context;
    private LayoutInflater inflater;
    private TheLoaiDAO theLoaiDAO;
    int m_index ;

    public TheLoaiAdapter(List<TheLoai> list_TheLoai, Context cotext) {
        this.list_TheLoai = list_TheLoai;
        this.context = cotext;
        this.inflater = (LayoutInflater) cotext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.theLoaiDAO = new TheLoaiDAO(cotext);
    }

    @Override
    public int getCount() {
        return list_TheLoai.size();
    }

    @Override
    public Object getItem(int i) {
        return list_TheLoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete;
        ImageView imgEdit;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_the_loai, null);
            holder.img = view.findViewById(R.id.ivIconTheLoai);
            holder.txtMaTheLoai = view.findViewById(R.id.tvItemMaTheLoai);
            holder.txtTenTheLoai = view.findViewById(R.id.tvItemTenTheLoai);
            holder.imgDelete = view.findViewById(R.id.ivItemDeleteTheLoai);
            holder.imgEdit = view.findViewById(R.id.ivEditTheLoai);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateTheLoaiActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MATHELOAI",list_TheLoai.get(i).getMaTheLoai());
                    b.putString("TENTHELOAI",list_TheLoai.get(i).getTenTheLoai());
                    b.putString("MOTA",list_TheLoai.get(i).getMoTa());
                    b.putString("VITRI",String.valueOf(list_TheLoai.get(i).getViTri()));
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m_index = i;
                    try{
                        showDialog("Bạn có chắc muốn xoá thể loại này ? ?");
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }

            });
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        TheLoai entry_theLoai = list_TheLoai.get(i);
        holder.img.setImageResource(R.drawable.category_book);
        holder.txtMaTheLoai.setText(entry_theLoai.getMaTheLoai());
        holder.txtTenTheLoai.setText(entry_theLoai.getTenTheLoai());
        return view;

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(List<TheLoai> list_itemTheLoai) {
        this.list_TheLoai = list_itemTheLoai;
        notifyDataSetChanged();
    }
    public void showDialog(final String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("" + text);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

                TheLoai tl = list_TheLoai.get(m_index);
                theLoaiDAO.deleteTheLoai(list_TheLoai.get(m_index).getMaTheLoai());
                list_TheLoai.remove(tl);
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
