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

import com.example.ph20617_mob201_assignment.DTO.Lop;
import com.example.ph20617_mob201_assignment.Fragment.LopFragment;
import com.example.ph20617_mob201_assignment.R;

import java.util.ArrayList;

public class AdapterLop extends ArrayAdapter<Lop> {
    private Context context;
    private ArrayList<Lop> list;
    private LopFragment fragment;
    private TextView tvMaLop;
    private TextView tvTenLop;
    private Button btnXoa;


    public AdapterLop(@NonNull Context context, LopFragment fragment, ArrayList<Lop> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lop, null);
        final Lop lop = list.get(position);
        if (lop != null) {
            tvMaLop = view.findViewById(R.id.tv_maLop);
            tvTenLop = view.findViewById(R.id.tv_tenLop);
            btnXoa = view.findViewById(R.id.btnXoa);

            tvMaLop.setText(String.valueOf(lop.getMaLop()));
            tvTenLop.setText("Tên lớp: " + lop.getTenLop());
        }
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(lop.getMaLop());
            }
        });

        return view;
    }
}
