package com.example.ph20617_mob201_assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ph20617_mob201_assignment.DTO.Lop;
import com.example.ph20617_mob201_assignment.R;

import java.util.ArrayList;


public class AdapterSpinnerLop extends ArrayAdapter<Lop> {
    private Context context;
    private ArrayList<Lop> list;
    private TextView tvMaLop;
    private TextView tvTenLop;


    public AdapterSpinnerLop(@NonNull Context context, ArrayList<Lop> list) {
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
            view = inflater.inflate(R.layout.adapter_spinner_none_lop, null);
        }
        final Lop lop = list.get(position);
        if (lop != null) {

            tvMaLop = view.findViewById(R.id.tv_maLop);
            tvTenLop = view.findViewById(R.id.tv_tenLop);

            tvMaLop.setText(String.valueOf(lop.getMaLop()));
            tvTenLop.setText(lop.getTenLop());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_spinner_drop_lop, null);
        }
        final Lop lop = list.get(position);
        if (lop != null) {

            tvMaLop = view.findViewById(R.id.tv_maLop);
            tvTenLop = view.findViewById(R.id.tv_tenLop);

            tvMaLop.setText(String.valueOf(lop.getMaLop()));
            tvTenLop.setText(lop.getTenLop());
        }
        return view;
    }
}
