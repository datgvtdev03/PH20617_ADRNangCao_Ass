package com.example.ph20617_mob201_assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.ph20617_mob201_assignment.DTO.KhoaHoc;
import com.example.ph20617_mob201_assignment.Fragment.KhoaHocFragment;
import com.example.ph20617_mob201_assignment.R;

import java.util.ArrayList;

public class AdapterKhoaHoc extends ArrayAdapter<KhoaHoc> {
    private final Context context;
    private final ArrayList<KhoaHoc> list;
    private final KhoaHocFragment fragment;
    private Button btnXoa;


    public AdapterKhoaHoc(@NonNull Context context, KhoaHocFragment fragment, ArrayList<KhoaHoc> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_khoahoc, null);
        final KhoaHoc khoaHoc = list.get(position);
        if (khoaHoc != null) {

            TextView tvMaKh = view.findViewById(R.id.tv_maKh);
            TextView tvTenKH = view.findViewById(R.id.tv_tenKH);
            TextView tvSoGioHoc = view.findViewById(R.id.tv_soGioHoc);
            btnXoa = view.findViewById(R.id.btnXoa);

            tvMaKh.setText(String.valueOf(khoaHoc.getMaKH()));
            tvTenKH.setText("Tên khóa học: " + khoaHoc.getTenKH());
            tvSoGioHoc.setText("Số giờ học: " + khoaHoc.getSoGioHoc() + "h");
        }
        btnXoa.setOnClickListener(v -> {
            if (khoaHoc != null) {
                fragment.xoa(khoaHoc.getMaKH());
            }
        });

        return view;
    }
}
