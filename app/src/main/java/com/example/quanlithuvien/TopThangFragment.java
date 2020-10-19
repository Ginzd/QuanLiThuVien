package com.example.quanlithuvien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.quanlithuvien.adapter.SachBanChayAdapter;
import com.example.quanlithuvien.dao.SachDAO;
import com.example.quanlithuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class TopThangFragment extends Fragment {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvTopBook;
    SachBanChayAdapter adapter = null;
    SachDAO sachDAO;
    Spinner spnTopThang;
    int valueThang;

    public TopThangFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_thang, container, false);

        lvTopBook = view.findViewById(R.id.lvTopBookThang);
        spnTopThang = view.findViewById(R.id.spnTopThang);
        ArrayAdapter<CharSequence> adapterSpn = ArrayAdapter.createFromResource(
                getActivity(), R.array.spn_items, android.R.layout.simple_spinner_item
        );
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTopThang.setAdapter(adapterSpn);
        spnTopThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valueThang = spnTopThang.getSelectedItemPosition() + 1;
                sachDAO = new SachDAO(getActivity());
                dsSach = sachDAO.topBookMonth(String.valueOf(valueThang));
                adapter = new SachBanChayAdapter(dsSach, getActivity());
                lvTopBook.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sachDAO = new SachDAO(getActivity());
        dsSach = sachDAO.topBookMonth(String.valueOf(valueThang));
        adapter = new SachBanChayAdapter(dsSach, getActivity());
        lvTopBook.setAdapter(adapter);

        return view;
    }
}