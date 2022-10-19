package com.example.ph20617_mob201_assignment.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ph20617_mob201_assignment.Fragment.KhoaHocFragment;
import com.example.ph20617_mob201_assignment.Fragment.LopFragment;
import com.example.ph20617_mob201_assignment.Fragment.SinhVienFragment;

public class AdapterTabLayout extends FragmentStateAdapter {


    public AdapterTabLayout(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return new SinhVienFragment();
            }
            case 1: {
                return new LopFragment();
            }
            case 2: {
                return new KhoaHocFragment();
            }
            default: {
                return new SinhVienFragment();
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
