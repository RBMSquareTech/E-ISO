package com.rbmsquare.e_iso.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rbmsquare.e_iso.Model.OrderModel;
import com.rbmsquare.e_iso.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{

    private List<OrderModel> orderModels;
    private Context mContext;
    private AdapterView.OnItemClickListener itemClickListener;

    public OrderAdapter(List<OrderModel> orderModels, Context mContext) {
        this.orderModels = orderModels;
        this.mContext = mContext;
    }

    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order, viewGroup, false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder myViewHolder, int i) {

        OrderModel orderMode = orderModels.get(i);

        myViewHolder.date.setText(""+orderMode.getDate());
        myViewHolder.iso.setText(orderModels.get(i).getIso());
        myViewHolder.pending.setText(orderModels.get(i).getPending());

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

//    @Override
//    public void onClick(View v) {
//        RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();
//
//
//    }  if (v.getId() == holder.p.getId()) {
//        Toast.makeText(mContext, holder.pe.getText(), Toast.LENGTH_SHORT).show();
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView iso, date, pending;
        ImageView delete;

        public MyViewHolder(View itemView) {
            super(itemView);

            iso = (TextView) itemView.findViewById(R.id.iso);
            date = (TextView) itemView.findViewById(R.id.date);
            pending = (TextView) itemView.findViewById(R.id.pending);
            delete = (ImageView) itemView.findViewById(R.id.delete);

        }

    }
}
