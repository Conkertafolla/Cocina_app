package com.example.conke.cocina.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.conke.cocina.Entities.Order;
import com.example.conke.cocina.Entities.Product;
import com.example.conke.cocina.R;

import java.util.ArrayList;

/**
 * Created by conke on 12/03/2019.
 */

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.ViewHolderOrder>  {
    ArrayList<Product> products = new ArrayList<>();

    public orderAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_order,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new orderAdapter.ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOrder holder, int position) {
        holder.assignProducts(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }



    public class ViewHolderOrder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productQTY;
        TextView productNet;
        public ViewHolderOrder(View itemView) {
            super(itemView);
             productName =itemView.findViewById(R.id.prd_lbl_ord);
             productQTY=itemView.findViewById(R.id.qty_lbl_ord);;
             productNet=itemView.findViewById(R.id.net_lbl_ord);;
        }

        public void assignProducts(Product product) {
            productName.setText(product.getProductName().toString());
            productQTY.setText(""+product.getProductQTY());
            productNet.setText(""+getNetCost(product.getProductQTY(),product.getProductCost()));
        }

        private float getNetCost(int productQTY, Float productCost) {
            float net=0.0f;
            net= productQTY*productCost;
            return net;
        }
    }
}
