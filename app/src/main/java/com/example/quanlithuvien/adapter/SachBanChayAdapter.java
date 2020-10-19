package com.example.quanlithuvien.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlithuvien.R;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.Sach;

import java.util.List;

public class SachBanChayAdapter extends BaseAdapter{
    List<Sach> list_sach;
    public Activity context;
    public LayoutInflater inflater;
    SachDAO sachDAO;

    public SachBanChayAdapter(List<Sach> list_sach, Activity context) {
        super();
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
        TextView txtTacGia;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_sachbanchay,null);
            holder.img = view.findViewById(R.id.ivIconBook);
            holder.txtBookName = view.findViewById(R.id.tvTenSachBanChay);
            holder.txtTacGia = view.findViewById(R.id.tvTacGiaSachBanChay);
            view.setTag(holder);
        }
        holder = (ViewHolder)view.getTag();
        Sach entry_sach = list_sach.get(i);
        holder.img.setImageResource(R.drawable.bookicon1);
        holder.txtBookName.setText("Mã Sách :"+entry_sach.getMaSach());
        holder.txtTacGia.setText("Số lượng :"+entry_sach.getSoLuong());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
