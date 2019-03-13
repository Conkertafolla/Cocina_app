package com.example.conke.cocina.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.conke.cocina.Entities.Product;
import com.example.conke.cocina.R;

import java.util.ArrayList;

/**
 * Created by conke on 03/03/2019.
 */

public class productAdapter  extends RecyclerView.Adapter<productAdapter.ViewHolderProduct>  {
    ArrayList<Product> products= new ArrayList <>();
    private Context mContext;
    public itemsListener onClickListener;

    public productAdapter(Context context, ArrayList<Product> products, itemsListener listener) {
        this.products = products;
        this.onClickListener = listener;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product,null,false);
        return new ViewHolderProduct(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct holder, int position) {
        holder.assignProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productCost;
        TextView productQTY;
        Button moreProduct;
        Button lessProduct;
        public ViewHolderProduct(View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.prdName);
            productCost=itemView.findViewById(R.id.prdCost);
            moreProduct= itemView.findViewById(R.id.moreBtn);
            lessProduct= itemView.findViewById(R.id.lessBtn);
            productQTY= itemView.findViewById(R.id.prdQuantity);

            
            moreProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.addProduct(v,getAdapterPosition());
                    productQTY.setText(""+products.get(getAdapterPosition()).getProductQTY());
                }
            });
            lessProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.subtracProduct(v,getAdapterPosition());
                    productQTY.setText(""+products.get(getAdapterPosition()).getProductQTY());
                }
            });
        }

        public void assignProduct(Product product) {
            productName.setText(product.getProductName().toString());
            productCost.setText("$"+product.getProductCost());
            productQTY.setText(""+product.getProductQTY());

        }
    }
    public interface itemsListener {

        void addProduct(View v, int position);


        void subtracProduct(View v, int adapterPosition);
    }
}
