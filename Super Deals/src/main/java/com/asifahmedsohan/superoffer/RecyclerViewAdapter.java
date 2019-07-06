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

import com.squareup.picasso.Callback;


import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Offer> mData;

    int positn;
    MyViewHolder hldr;

    public RecyclerViewAdapter(Context mContext, List<Offer> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(mContext).inflate(R.layout.activity_dealsmini,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);

        return vHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        positn=position;
        hldr=holder;

        holder.of_name.setText(mData.get(position).getCompanyName());
        holder.of_desMini.setText(mData.get(position).getDescription_short());
        holder.of_date.setText(mData.get(position).getValidityDate());


    /*    Picasso
                .get()
                .load(mData.get(position).getCompanyLogo())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.of_logo, new Callback() {
            @Override
            public void onSuccess() {

                Log.d("ofln","nooo");
            }

            @Override
            public void onError(Exception e) {

                Log.d("ofln","offline");

                Picasso
                        .get()
                        .load(mData.get(positn).getCompanyLogo())
                        .into(hldr.of_logo);
                    }
        });
*/

/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,OfferFull.class);
                intent.putExtra("description", mData.get(position).getDescription());
                mContext.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemCount() {

        return mData.size();

    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView of_name;
        private  TextView of_desMini;
        private  TextView of_date;
        private ImageView of_logo;

        public MyViewHolder(View itemview){
            super(itemview);

            of_name=(TextView) itemView.findViewById(R.id.name);
            of_desMini=(TextView)itemView.findViewById(R.id.offer_short);
            of_date=(TextView)itemView.findViewById(R.id.expire_date);
            of_logo=(ImageView)itemview.findViewById(R.id.logo);
        }

    }


}
