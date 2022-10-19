package com.example.ph20617_mob201_assignment.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.example.ph20617_mob201_assignment.Adapter.AdapterKhoaHoc;
import com.example.ph20617_mob201_assignment.DAO.DAOKhoaHoc;
import com.example.ph20617_mob201_assignment.DTO.KhoaHoc;
import com.example.ph20617_mob201_assignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KhoaHocFragment extends Fragment {

    private ListView lst;
    private TextView tvMaKH;
    private EditText edtTenKH;
    private EditText edtSoGioHoc;


    static DAOKhoaHoc daoKhoaHoc;
    KhoaHoc khoaHoc;
    AdapterKhoaHoc adapterKhoaHoc;

    public KhoaHocFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_fragment, null, false);

        lst = view.findViewById(R.id.lst);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        daoKhoaHoc = new DAOKhoaHoc(getContext());
        fab.setOnClickListener(v -> openDiaLog(getContext(), 0));
        capNhatLv();

        return view;
    }


    protected void openDiaLog(final Context context, final int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_khoahoc, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.show();

        tvMaKH = view.findViewById(R.id.tv_maKH);
        edtTenKH = view.findViewById(R.id.edt_TenKH);
        edtSoGioHoc = view.findViewById(R.id.edt_soGioHoc);
        Button btnHuy = view.findViewById(R.id.btn_Huy);
        Button btnThem = view.findViewById(R.id.btn_Them);

        if (type != 0) {
            tvMaKH.setText(String.valueOf(khoaHoc.getMaKH()));
            edtTenKH.setText(khoaHoc.getTenKH());
            edtSoGioHoc.setText(String.valueOf(khoaHoc.getSoGioHoc()));

        }

        btnHuy.setOnClickListener(v -> {
            alertDialog.dismiss();
        });
        btnThem.setOnClickListener(v -> {
            if (validate() > 0) {
                KhoaHoc khoaHoc = new KhoaHoc();
                khoaHoc.setTenKH(edtTenKH.getText().toString());
                khoaHoc.setSoGioHoc(Integer.parseInt(edtSoGioHoc.getText().toString()));
                if (type == 0) {
                    if (daoKhoaHoc.insert(khoaHoc) > 0) {
                        Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        //Log.d("aaaaaaaa", "openDiaLog: "+lop.getMaLop()+"---"+lop.getTenLop());
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    khoaHoc.setMaKH(Integer.parseInt(tvMaKH.getText().toString()));
                    if (daoKhoaHoc.update(khoaHoc) > 0) {
                        Toast.makeText(getContext(), "Update thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Update thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                alertDialog.dismiss();
            }
        });

    }

    public void xoa(final int maKH) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo").setMessage("Bạn muốn xóa lớp này?");
        builder.setNegativeButton("Hủy", (dialog, which) -> {

        });
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            if (daoKhoaHoc.delete(maKH) > 0) {
                capNhatLv();
                Toast.makeText(getContext(), "Đã xóa!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Xóa thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public int validate() {
        int check = 1;
        if (edtTenKH.getText().length() == 0 ||
                edtSoGioHoc.getText().length() == 0) {
            Toast.makeText(getContext(), "Không để trống các trường!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (edtTenKH.getText().length() < 4) {
            Toast.makeText(getContext(), "Tên lớp tối thiểu phải 4 kí tự!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhatLv() {
        ArrayList<KhoaHoc> list = (ArrayList<KhoaHoc>) daoKhoaHoc.getAll();
        adapterKhoaHoc = new AdapterKhoaHoc(getContext(), this, list);
        lst.setAdapter(adapterKhoaHoc);
    }
}
