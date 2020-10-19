package com.example.quanlithuvien;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.quanlithuvien.dao.HoaDonDAO;
import com.example.quanlithuvien.model.HoaDon;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText edtNgayMua,edtMaHoaDon;
    HoaDonDAO hoaDonDAO;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    TextInputLayout textInputMaHD,textInputNgayThang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        setTitle("Hóa Đơn");
        edtNgayMua = findViewById(R.id.edtNgayMua);
        edtMaHoaDon = findViewById(R.id.edtMaHoaDon);

        textInputMaHD =findViewById(R.id.text_input_layout_maHoaDon);
        textInputNgayThang =findViewById(R.id.text_input_layout_ngayThang);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        @SuppressLint("SimpleDateFprmat")DateFormat dateFormat = new
                SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = dateFormat.format(calendar.getTime());
        edtNgayMua.setText(currentDateString);
    }

    public static class DatePickerFragment extends DialogFragment{
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
    public void datePicker(View view){
        String tag = "date";
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),tag);//
    }
    public void AddHoaDon(View view) throws ParseException {
        if (!validateMaHoaDon() || !validateNgayThang()){
            return;
        }
        hoaDonDAO = new HoaDonDAO(this);
        HoaDon hd = new HoaDon(edtMaHoaDon.getText().toString(),dateFormat.parse(edtNgayMua.getText().toString()));
        if (hoaDonDAO.insertHoaDon(hd)>0){
            Toast.makeText(this, "Thêm Hóa Đơn Thành Công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HoaDonActivity.this,HoaDonChiTietActivity.class);
            Bundle b = new Bundle();
            b.putString("MAHOADON",edtMaHoaDon.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Mã hóa đơn đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }
    public void huyHoaDon(View view) {
        finish();
    }

    private boolean validateMaHoaDon() {
        String val = textInputMaHD.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            textInputMaHD.setError("Mã hóa đơn không để trống !");
            return false;
        } else {
            textInputMaHD.setError(null);
            textInputMaHD.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateNgayThang() {
        String val = textInputNgayThang.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            textInputNgayThang.setError("Ngày tháng không để trống !");
            return false;
        } else {
            textInputNgayThang.setError(null);
            textInputNgayThang.setErrorEnabled(false);
            return true;
        }
    }
}