package com.example.ph20617_mob201_assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.ph20617_mob201_assignment.DTO.KhoaHoc;
import com.example.ph20617_mob201_assignment.R;

import java.util.ArrayList;

public class AdapterSpinnerKH extends ArrayAdapter<KhoaHoc> {
    private Context context;
    private ArrayList<KhoaHoc> list;
    private TextView tvMaKH;
    private TextView tvTenKH;


    public AdapterSpinnerKH(@NonNull Context context, ArrayList<KhoaHoc> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_spinner_none_kh, null);
        }
        final KhoaHoc khoaHoc = list.get(position);
        if (khoaHoc != null) {

            tvMaKH = view.findViewById(R.id.tv_maKH);
            tvTenKH = view.findViewById(R.id.tv_tenKH);

            tvMaKH.setText(String.valueOf(khoaHoc.getMaKH()));
            tvTenKH.setText(khoaHoc.getTenKH());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_spinner_drop_kh, null);
        }
        final KhoaHoc khoaHoc = list.get(position);
        if (khoaHoc != null) {

            tvMaKH = view.findViewById(R.id.tv_maKH);
            tvTenKH = view.findViewById(R.id.tv_tenKH);

            tvMaKH.setText(String.valueOf(khoaHoc.getMaKH()));
            tvTenKH.setText(khoaHoc.getTenKH());
        }
        return view;
    }
}
