package com.example.quanlithuvien.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlithuvien.R;
import com.example.quanlithuvien.UpdateUserActivity;
import com.example.quanlithuvien.dao.NguoiDungDao;
import com.example.quanlithuvien.model.NguoiDung;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdapterNguoiDung extends RecyclerView.Adapter<AdapterNguoiDung.RecycleViewHolder> implements Filterable {
    private Context context;
    private List<NguoiDung> arrListNguoiDung;
    private List<NguoiDung> arrListNguoiDungAll;
    private LayoutInflater inflater;
    private NguoiDungDao nguoiDungDao;
    private int m_index;

    public AdapterNguoiDung(Context context, List<NguoiDung> arrListNguoiDung) {
        this.context = context;
        this.arrListNguoiDung = arrListNguoiDung;
        this.arrListNguoiDungAll = new ArrayList<>(arrListNguoiDung);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao = new NguoiDungDao(context);
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int index) {
        View view = inflater.inflate(R.layout.item_nguoi_dung, null);
        return new RecycleViewHolder(view);
    }

    //lay du lieu
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, final int position) {
        NguoiDung nguoiDung = arrListNguoiDung.get(position);
        holder.tvNameNguoiDung.setText(nguoiDung.getUserName());
        holder.tvPhoneNguoiDung.setText(nguoiDung.getPhone());
        holder.ivEditNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, UpdateUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName_key", arrListNguoiDung.get(position).getUserName());
                bundle.putString("password_key", arrListNguoiDung.get(position).getPassword());
                bundle.putString("phone_key", arrListNguoiDung.get(position).getPhone());
                bundle.putString("hoTen_key", arrListNguoiDung.get(position).getHoTen());
                intent.putExtra("bundle", bundle);
                context.startActivity(intent);

            }
        });
        holder.ivDeleteNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_index = position;
                try {
                    showDialog("Bạn có chắc muốn xóa người dùng này ?");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrListNguoiDung.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<NguoiDung> filterList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
               filterList.addAll(arrListNguoiDungAll);
            }else {
                for (NguoiDung nguoidung : arrListNguoiDungAll){
                    if (nguoidung.getUserName().toUpperCase().contains(charSequence.toString().toUpperCase())){
                        filterList.add(nguoidung);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            arrListNguoiDung.clear();
            arrListNguoiDung.addAll((Collection<? extends NguoiDung>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIconNguoiDung;
        TextView tvNameNguoiDung, tvPhoneNguoiDung;
        ImageView ivDeleteNguoiDung;
        ImageView ivEditNguoiDung;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivIconNguoiDung = itemView.findViewById(R.id.ivIconNguoiDung);
            this.tvNameNguoiDung = itemView.findViewById(R.id.tvNameNguoiDung);
            this.tvPhoneNguoiDung = itemView.findViewById(R.id.tvPhoneNguoiDung);
            this.ivDeleteNguoiDung = itemView.findViewById(R.id.ivDeleteNguoiDung);
            this.ivEditNguoiDung = itemView.findViewById(R.id.ivEditNguoiDung);
        }
    }

    public void showDialog(final String phone){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("" + phone);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                NguoiDung nd = arrListNguoiDung.get(m_index);
                nguoiDungDao.deleteUser(arrListNguoiDung.get(m_index).getUserName());
                arrListNguoiDung.remove(nd);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
