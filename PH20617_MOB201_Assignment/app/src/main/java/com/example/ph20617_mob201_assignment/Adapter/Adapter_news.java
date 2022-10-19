package com.example.ph20617_mob201_assignment.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ph20617_mob201_assignment.DTO.News;
import com.example.ph20617_mob201_assignment.News2;
import com.example.ph20617_mob201_assignment.R;

import java.util.List;

public class Adapter_news extends RecyclerView.Adapter<Adapter_news.adapterViewHolder>{
    List<News> news;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<News> baos) {
        this.news = baos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public adapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull adapterViewHolder holder, int position) {
        News news1 = news.get(position);
        Log.d("sss44", "onBindViewHolder: "+news1);

        try{
            holder.txtTitle.setText(news1.getTitle());
            holder.txtGioiThieu.setText(Html.fromHtml(news1.getGioiThieu()));
            holder.txtNgay.setText(news1.getNgay());
            Glide.with(holder.txtNgay.getContext()).load(news1.getImg()).into(holder.img);
        }catch (Exception e){

        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), News2.class);
                intent.putExtra("a",news1.getLink());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(news==null){
            return 0;
        }
        return news.size();

    }

    public class adapterViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtGioiThieu, txtNgay;
        ImageView img;
        LinearLayout linearLayout;
        public adapterViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.LL);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            img = itemView.findViewById(R.id.img);
            txtGioiThieu = itemView.findViewById(R.id.txtGioiThieu);
            txtNgay = itemView.findViewById(R.id.txtNgay);
        }
    }
}
