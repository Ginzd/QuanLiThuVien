package com.example.quanlithuvien;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlithuvien.dao.HoaDonDAO;
import com.example.quanlithuvien.dao.TheLoaiDAO;
import com.example.quanlithuvien.model.HoaDon;
import com.example.quanlithuvien.model.TheLoai;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateHoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText edtNgayMua,edtMaHoaDon;
    HoaDonDAO hoaDonDAO;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hoa_don);
        setTitle("Hóa Đơn");
        edtNgayMua = findViewById(R.id.edtNgayMua1);
        edtMaHoaDon = findViewById(R.id.edtMaHoaDon1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            edtMaHoaDon.setText(bundle.getString("MAHOADON"));
//            edtNgayMua.setText(bundle.getString("NGAYMUA"));
        }
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        @SuppressLint("SimpleDateFprmat") DateFormat dateFormat = new
                SimpleDateFormat("dd-MM-yyyy");
        String currentDateString = dateFormat.format(calendar.getTime());
        edtNgayMua.setText(currentDateString);
    }

    public static class DatePickerFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(), year, month, day);
        }

    }
    public void datePicker2(View view){
        String tag = "date";
        UpdateHoaDonActivity.DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),tag);//
    }
    public void updateHoaDon(View view) throws ParseException {
        hoaDonDAO = new HoaDonDAO(this);
        HoaDon hd = new HoaDon(edtMaHoaDon.getText().toString(),dateFormat.parse(edtNgayMua.getText().toString()));
        if (hoaDonDAO.updateHoaDon(hd) ==1){
            Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void huyUpdateHoaDon(View view) {
    }
}