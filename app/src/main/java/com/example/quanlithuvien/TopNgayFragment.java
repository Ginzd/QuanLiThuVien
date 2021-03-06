package com.example.quanlithuvien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.quanlithuvien.adapter.SachBanChayAdapter;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class TopNgayFragment extends Fragment {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvTopBook;
    SachBanChayAdapter adapter = null;
    SachDAO sachDAO;

    public TopNgayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_ngay, container, false);
        lvTopBook = view.findViewById(R.id.lvTopBook);
        sachDAO = new SachDAO(getActivity());
        dsSach = sachDAO.topBookDay();
        adapter = new SachBanChayAdapter(dsSach, getActivity());
        lvTopBook.setAdapter(adapter);

        return view;
    }
}