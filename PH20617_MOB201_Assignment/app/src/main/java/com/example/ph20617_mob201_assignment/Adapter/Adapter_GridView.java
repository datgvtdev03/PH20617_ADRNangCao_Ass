package com.example.ph20617_mob201_assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ph20617_mob201_assignment.R;


public class Adapter_GridView extends BaseAdapter {
    private final Context context;
    private final int[] img;
    private final String[] name;

    public Adapter_GridView(Context context, int[] img, String[] name) {
        this.context = context;
        this.img = img;
        this.name = name;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.adapter_grid_item, null);
        TextView textView = convertView.findViewById(R.id.adapter_tv_name);
        ImageView imageView = convertView.findViewById(R.id.adapter_img_icon);

        textView.setText(name[position]);
        imageView.setImageResource(img[position]);
        return convertView;
    }
}
