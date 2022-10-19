package com.example.ph20617_mob201_assignment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;


import com.example.ph20617_mob201_assignment.DAO.DAOKhoaHoc;
import com.example.ph20617_mob201_assignment.DAO.DAOLop;
import com.example.ph20617_mob201_assignment.DTO.KhoaHoc;
import com.example.ph20617_mob201_assignment.DTO.Lop;
import com.example.ph20617_mob201_assignment.DTO.SinhVien;
import com.example.ph20617_mob201_assignment.Fragment.SinhVienFragment;
import com.example.ph20617_mob201_assignment.R;

import java.util.ArrayList;

public class AdapterSinhVien extends ArrayAdapter<SinhVien> {
    private final Context context;
    private final ArrayList<SinhVien> list;
    private final SinhVienFragment fragment;
    private AppCompatButton btnXoa;


    public AdapterSinhVien(@NonNull Context context, SinhVienFragment fragment, ArrayList<SinhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sinhvien, null);
        final SinhVien sinhVien = list.get(position);
        if (sinhVien != null) {
            TextView tvMasV = view.findViewById(R.id.tv_masV);
            TextView tvTenSv = view.findViewById(R.id.tv_tenSv);
            TextView tvTenLop = view.findViewById(R.id.tv_tenLop);
            TextView tvTenKH = view.findViewById(R.id.tv_tenKH);
            TextView tvNamSinh = view.findViewById(R.id.tv_namSinh);
            btnXoa = view.findViewById(R.id.btnXoa);

            tvMasV.setText(String.valueOf(sinhVien.getMaSv()));
            tvTenSv.setText("Tên sv: " + sinhVien.getTenSv());
            DAOKhoaHoc daoKhoaHoc = new DAOKhoaHoc(context);
            KhoaHoc khoaHoc = daoKhoaHoc.getID(sinhVien.getMaKH());
            tvTenKH.setText("Khóa học: " + khoaHoc.getTenKH());
            DAOLop daoLop = new DAOLop(context);
            Lop lop = daoLop.getID(sinhVien.getMaLop());
            tvTenLop.setText("Lớp: " + lop.getTenLop());
            tvNamSinh.setText("Năm sinh: " + sinhVien.getNamSinh());
            Log.d("hhhhhhhhhhh", "getView: ");
        }
        btnXoa.setOnClickListener(v -> fragment.xoa(sinhVien.getMaSv()));

        return view;
    }
}
