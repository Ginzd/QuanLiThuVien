package com.example.quanlithuvien.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlithuvien.ListHoaDonActivity;
import com.example.quanlithuvien.ListNguoiDungActivity;
import com.example.quanlithuvien.ListSachActivity;
import com.example.quanlithuvien.ListTheLoaiActivity;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.SachBanChayActivity;
import com.example.quanlithuvien.ThongKeActitvity;

public class HomeFragment extends Fragment {
    Button btnUser;
    Button btnTheLoai;
    Button btnSach;
    Button btnHoaDon;
    Button btnSachBanChay;
    Button btnThongKe;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnUser = view.findViewById(R.id.btnUser);
        btnTheLoai = view.findViewById(R.id.btnTheLoai);
        btnSach = view.findViewById(R.id.btnBook);
        btnHoaDon = view.findViewById(R.id.btnHoaDon);
        btnSachBanChay = view.findViewById(R.id.btnSachBanChay);
        btnThongKe = view.findViewById(R.id.btnThongKe);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListNguoiDungActivity.class));
            }
        });
        btnTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListTheLoaiActivity.class));
            }
        });
        btnSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListSachActivity.class));
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListHoaDonActivity.class));
            }
        });

        btnSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SachBanChayActivity.class));
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ThongKeActitvity.class));
            }
        });
        return view;
    }
}