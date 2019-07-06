package com.asifahmedsohan.superoffer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Offer> mData;

    int positn;
    MyViewHolder hldr;

    private ItemClickListener mItemClickListener;


    public RecyclerViewAdapter(Context mContext, List<Offer> mData , ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mItemClickListener=itemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(mContext).inflate(R.layout.activity_dealsmini,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v, mItemClickListener);

        return vHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        positn=position;
        hldr=holder;

        holder.of_name.setText(mData.get(position).getCompanyName());
        holder.of_desMini.setText(mData.get(position).getDescription_short());
        holder.of_date.setText(mData.get(position).getValidityDate());

        Picasso.with(mContext)
                .load(mData.get(position).getCompanyLogo())
                .into(holder.of_logo);

    }

    @Override
    public int getItemCount() {

        return mData.size();

    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView of_name;
        private  TextView of_desMini;
        private  TextView of_date;
        private ImageView of_logo;

        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView , ItemClickListener itemClickListener){
            super(itemView);

            of_name=(TextView) itemView.findViewById(R.id.name);
            of_desMini=(TextView)itemView.findViewById(R.id.offer_short);
            of_date=(TextView)itemView.findViewById(R.id.expire_date);
            of_logo=(ImageView)itemView.findViewById(R.id.logo);
            this.itemClickListener=itemClickListener;


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            itemClickListener.ItemClickListener(getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void ItemClickListener(int position);
    }


}
