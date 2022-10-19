package com.example.ph20617_mob201_assignment.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.example.ph20617_mob201_assignment.Adapter.AdapterSinhVien;
import com.example.ph20617_mob201_assignment.Adapter.AdapterSpinnerKH;
import com.example.ph20617_mob201_assignment.Adapter.AdapterSpinnerLop;
import com.example.ph20617_mob201_assignment.DAO.DAOKhoaHoc;
import com.example.ph20617_mob201_assignment.DAO.DAOLop;
import com.example.ph20617_mob201_assignment.DAO.DAOSinhVien;
import com.example.ph20617_mob201_assignment.DTO.KhoaHoc;
import com.example.ph20617_mob201_assignment.DTO.Lop;
import com.example.ph20617_mob201_assignment.DTO.SinhVien;
import com.example.ph20617_mob201_assignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SinhVienFragment extends Fragment {
    private ListView lst;
    DAOSinhVien daoSinhVien;

    ArrayList<SinhVien> list;
    ArrayList<Lop> lstLop;
    ArrayList<KhoaHoc> lstKH;
    SinhVien sinhVien;

    DAOLop daoLop;
    DAOKhoaHoc daoKhoaHoc;
    AdapterSinhVien adapterSv;
    AdapterSpinnerLop adapterSpinnerLop;
    AdapterSpinnerKH adapterSpinnerKH;

    private TextView tvMaSv;
    private EditText edtTenSv;
    private EditText edtNamSinh;

    int maKH, maLop;
    int positionMaLop;
    int positionMaKH;


    public SinhVienFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.student_fragment, null, false);

        lst = view.findViewById(R.id.lst);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        daoSinhVien = new DAOSinhVien(getContext());
        fab.setOnClickListener(v -> openDiaLog(getContext(), 0));
        capNhatLv();
        //Log.d("aaaaaaaaaaaaa", "onCreateView: ");
        return view;
    }

    protected void openDiaLog(final Context context, final int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sinhvien, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.show();


        tvMaSv = view.findViewById(R.id.tv_maSv);
        edtTenSv = view.findViewById(R.id.edt_TenSv);
        edtNamSinh = view.findViewById(R.id.edt_namSinh);
        Spinner spnKH = view.findViewById(R.id.spn_KH);
        Spinner spnLop = view.findViewById(R.id.spn_Lop);
        Button btnHuy = view.findViewById(R.id.btn_Huy);
        Button btnThem = view.findViewById(R.id.btn_Them);

        //lay ma lop
        daoLop = new DAOLop(getContext());
        lstLop = new ArrayList<>();
        lstLop = (ArrayList<Lop>) daoLop.getAll();
        adapterSpinnerLop = new AdapterSpinnerLop(getContext(), lstLop);
        spnLop.setAdapter(adapterSpinnerLop);
        spnLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLop = lstLop.get(position).getMaLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // lay ma KH
        daoKhoaHoc = new DAOKhoaHoc(getContext());
        lstKH = new ArrayList<>();
        lstKH = (ArrayList<KhoaHoc>) daoKhoaHoc.getAll();
        adapterSpinnerKH = new AdapterSpinnerKH(getContext(), lstKH);
        spnKH.setAdapter(adapterSpinnerKH);
        spnKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKH = lstKH.get(position).getMaKH();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (type != 0) {
            tvMaSv.setText(String.valueOf(sinhVien.getMaSv()));
            edtTenSv.setText(sinhVien.getTenSv());
            edtNamSinh.setText(sinhVien.getNamSinh());

            // set selectedItem lop
            for (int i = 0; i < lstLop.size(); i++) {
                if (sinhVien.getMaLop() == (lstLop.get(i).getMaLop())) {
                    {
                        positionMaLop = i;
                    }
                }
            }
            spnLop.setSelection(positionMaLop);
            // set selectedItem KH
            for (int i = 0; i < lstKH.size(); i++) {
                if (sinhVien.getMaKH() == (lstKH.get(i).getMaKH())) {
                    {
                        positionMaKH = i;
                    }
                }
            }
            spnLop.setSelection(positionMaKH);
        }


        btnHuy.setOnClickListener(v -> alertDialog.dismiss());
        btnThem.setOnClickListener(v -> {
            if (validate() > 0) {
                sinhVien = new SinhVien();
                sinhVien.setMaKH(maKH);
                sinhVien.setMaLop(maLop);
                sinhVien.setTenSv(edtTenSv.getText().toString());
                sinhVien.setNamSinh(edtNamSinh.getText().toString());
                if (type == 0) {
                    if (daoSinhVien.insert(sinhVien) > 0) {
                        Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        //Log.d("aaaaaaaa", "openDiaLog: "+lop.getMaLop()+"---"+lop.getTenLop());
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    sinhVien.setMaSv(Integer.parseInt(tvMaSv.getText().toString()));
                    if (daoSinhVien.update(sinhVien) > 0) {
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

    public void xoa(final int maSv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo").setMessage("Bạn muốn xóa sinh viên này?");
        builder.setNegativeButton("Hủy", (dialog, which) -> {

        });
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            if (daoSinhVien.delete(maSv) > 0) {
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
//        tvMaSv = view.findViewById(R.id.tv_maSv);
//        edtTenSv = view.findViewById(R.id.edt_TenKH);
        int check = 1;
        if (edtTenSv.getText().length() == 0) {
            Toast.makeText(getContext(), "Không để trống các trường!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (edtTenSv.getText().length() < 4) {
            Toast.makeText(getContext(), "Tên lớp tối thiểu phải 2 kí tự!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhatLv() {
        list = (ArrayList<SinhVien>) daoSinhVien.getAll();
        Log.d("zzzzzz", "capNhatLv: " + list);
        adapterSv = new AdapterSinhVien(getContext(), this, list);
        lst.setAdapter(adapterSv);
    }
}
