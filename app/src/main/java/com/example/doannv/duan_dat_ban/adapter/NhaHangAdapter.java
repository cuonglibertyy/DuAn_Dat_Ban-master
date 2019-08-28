package com.example.doannv.duan_dat_ban.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doannv.duan_dat_ban.R;
import com.example.doannv.duan_dat_ban.model.MyItemOnClick;
import com.example.doannv.duan_dat_ban.model.NhaHang;
import com.example.doannv.duan_dat_ban.unti.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.doannv.duan_dat_ban.R.drawable.ic_sao;

public class NhaHangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  ArrayList<NhaHang> arrayList;
    private  Context context;
    private MyItemOnClick myItemOnClick;

    public void setMyItemOnClick(MyItemOnClick myItemOnClick) {
        this.myItemOnClick = myItemOnClick;
    }

    public NhaHangAdapter(ArrayList<NhaHang> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_layout1,viewGroup,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        NhaHang nhaHang = arrayList.get(i);
        ViewHodel itemHolder = (ViewHodel) viewHolder;

        Picasso.get().load(Server.duongdananh + nhaHang.getImgnhahang())
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .into(itemHolder.imgItem1);
        itemHolder.tvItem1.setMaxLines(1);
        itemHolder.tvItem1.setEllipsize(TextUtils.TruncateAt.END);
        itemHolder.tvItem1.setText(nhaHang.getTennhahang());

        itemHolder.tvItemDiaChi.setMaxLines(1);
        itemHolder.tvItemDiaChi.setEllipsize(TextUtils.TruncateAt.END);
        itemHolder.tvItemDiaChi.setText(nhaHang.getDiachi());
        if (nhaHang.getDanhgia() == 1){
            itemHolder.saoden1.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden2.setImageResource(R.drawable.ic_sao_den);
            itemHolder.saoden3.setImageResource(R.drawable.ic_sao_den);
            itemHolder.saoden4.setImageResource(R.drawable.ic_sao_den);
            itemHolder.saoden5.setImageResource(R.drawable.ic_sao_den);
        }
        if (nhaHang.getDanhgia() == 2){
            itemHolder.saoden1.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden2.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden3.setImageResource(R.drawable.ic_sao_den);
            itemHolder.saoden4.setImageResource(R.drawable.ic_sao_den);
            itemHolder.saoden5.setImageResource(R.drawable.ic_sao_den);
        }
        if (nhaHang.getDanhgia() == 3){
            itemHolder.saoden1.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden2.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden3.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden4.setImageResource(R.drawable.ic_sao_den);
            itemHolder.saoden5.setImageResource(R.drawable.ic_sao_den);
        }
        if (nhaHang.getDanhgia() == 4){
            itemHolder.saoden1.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden2.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden3.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden4.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden5.setImageResource(R.drawable.ic_sao_den);
        }
        if (nhaHang.getDanhgia() == 5){
            itemHolder.saoden1.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden2.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden3.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden4.setImageResource(R.drawable.ic_sao);
            itemHolder.saoden5.setImageResource(R.drawable.ic_sao);
        }
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItemOnClick.onClick(arrayList.get(i));
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHodel extends RecyclerView.ViewHolder{
        private ImageView imgItem1;
        private TextView tvItem1,tvItemDiaChi;
        private ImageView saoden1,saoden2,saoden3,saoden4,saoden5;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgItem1 = itemView.findViewById(R.id.imgItem1);
            tvItem1 = itemView.findViewById(R.id.tvItem1);
            tvItemDiaChi = itemView.findViewById(R.id.tvItemDiaChi);
            saoden1 = itemView.findViewById(R.id.imgsaoden1);
            saoden2 = itemView.findViewById(R.id.imgsaoden2);
            saoden3 = itemView.findViewById(R.id.imgsaoden3);
            saoden4 = itemView.findViewById(R.id.imgsaoden4);
            saoden5 = itemView.findViewById(R.id.imgsaoden5);
        }
    }
}
