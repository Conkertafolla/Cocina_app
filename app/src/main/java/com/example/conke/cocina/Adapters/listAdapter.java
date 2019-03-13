package com.example.conke.cocina.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conke.cocina.Entities.Order;
import com.example.conke.cocina.R;

import java.util.ArrayList;

/**
 * Created by conke on 12/03/2019.
 */

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolderList> implements View.OnClickListener {
    ArrayList<Order> orders = new ArrayList<>();
    private View.OnClickListener listener;

    public listAdapter(ArrayList<Order> orders){
        this.orders = orders;

    }
    @NonNull
    @Override
    public listAdapter.ViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);
        return new ViewHolderList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.ViewHolderList holder, int position) {
        holder.assignOrders(orders.get(position));


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }
    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }

    }

    public class ViewHolderList extends RecyclerView.ViewHolder {
        TextView supplierName;
        TextView totalOrder;
        TextView orderId;
        public ViewHolderList(View itemView) {
            super(itemView);
            supplierName = itemView.findViewById(R.id.sup_lbl_lis);
            orderId = itemView.findViewById(R.id.ord_lbl_lis);
            totalOrder = itemView.findViewById(R.id.tot_lbl_lis);
        }

        public void assignOrders(Order order) {
            orderId.setText(""+order.getOrderId().toString());
            totalOrder.setText("$"+order.getTotal().toString());
            supplierName.setText(order.getSupplierName().toString());
        }
    }
}
