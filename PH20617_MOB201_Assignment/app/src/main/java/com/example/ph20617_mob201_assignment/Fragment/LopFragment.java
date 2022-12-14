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


import com.example.ph20617_mob201_assignment.Adapter.AdapterLop;
import com.example.ph20617_mob201_assignment.DAO.DAOLop;
import com.example.ph20617_mob201_assignment.DTO.Lop;
import com.example.ph20617_mob201_assignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LopFragment extends Fragment {
    private ListView lst;
    private EditText edtTenLop;
    private TextView tvMaLop;


    static DAOLop daoLop;
    Lop lop;
    AdapterLop adapterLop;

    public LopFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_fragment, null, false);
        // anh xa
        lst = view.findViewById(R.id.lst);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        daoLop = new DAOLop(getContext());
        fab.setOnClickListener(v -> {
            openDiaLog(getContext(), 0);
        });
        capNhatLv();
        return view;
    }


    protected void openDiaLog(final Context context, final int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_lop, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.show();

        tvMaLop = view.findViewById(R.id.tv_MaLop);
        edtTenLop = view.findViewById(R.id.edt_TenLop);
        Button btnHuy = view.findViewById(R.id.btn_Huy);
        Button btnThem = view.findViewById(R.id.btn_Them);
        if (type != 0) {
            tvMaLop.setText(String.valueOf(lop.getMaLop()));
            edtTenLop.setText(lop.getTenLop());
        }

        btnHuy.setOnClickListener(v -> {
            alertDialog.dismiss();
        });
        btnThem.setOnClickListener(v -> {
            if (validate() > 0) {
                lop = new Lop();
                lop.setTenLop(edtTenLop.getText().toString());
                if (type == 0) {
                    if (daoLop.insert(lop) > 0) {
                        Toast.makeText(getContext(), "Th??m th??nh c??ng!", Toast.LENGTH_SHORT).show();
                        //Log.d("aaaaaaaa", "openDiaLog: "+lop.getMaLop()+"---"+lop.getTenLop());
                    } else {
                        Toast.makeText(getContext(), "Th??m th???t b???i!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    lop.setMaLop(Integer.parseInt(tvMaLop.getText().toString()));
                    if (daoLop.update(lop) > 0) {
                        Toast.makeText(getContext(), "Update th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Update th???t b???i!", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                alertDialog.dismiss();
            }
        });

    }

    public void xoa(final int maLop) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Th??ng b??o").setMessage("B???n mu???n x??a l???p n??y?");
        builder.setNegativeButton("H???y", (dialog, which) -> {

        });
        builder.setPositiveButton("X??a", (dialog, which) -> {
            if (daoLop.delete(maLop) > 0) {
                capNhatLv();
                Toast.makeText(getContext(), "???? x??a!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "X??a th???t b???i!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public int validate() {
        int check = 1;
        if (edtTenLop.getText().length() == 0) {
            Toast.makeText(getContext(), "Kh??ng ????? tr???ng c??c tr?????ng!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (edtTenLop.getText().length() < 4) {
            Toast.makeText(getContext(), "T??n l???p t???i thi???u ph???i 4 k?? t???!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhatLv() {
        ArrayList<Lop> list = (ArrayList<Lop>) daoLop.getAll();
        adapterLop = new AdapterLop(getContext(), this, list);
        lst.setAdapter(adapterLop);
    }
}
