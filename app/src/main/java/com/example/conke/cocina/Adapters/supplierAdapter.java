package com.example.conke.cocina.Adapters;

import android.content.res.Resources;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conke.cocina.Entities.Supplier;
import com.example.conke.cocina.R;

import java.util.ArrayList;

/**
 * Created by conke on 01/03/2019.
 */

public class supplierAdapter extends RecyclerView.Adapter<supplierAdapter.ViewHolderSupplier> implements View.OnClickListener {
    ArrayList<Supplier> suppliers;
    private View.OnClickListener listener;
    public supplierAdapter(ArrayList<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @NonNull
    @Override
    public supplierAdapter.ViewHolderSupplier onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_supplier,null,false);
        view.setOnClickListener(this);
        return new ViewHolderSupplier(view);
    }

    @Override
    public void onBindViewHolder(@NonNull supplierAdapter.ViewHolderSupplier holder, int position) {
        Supplier supplier=suppliers.get(position);
        String iconName= supplier.getSupplierIcon();
        String uri = "com.example.conke.cocina:drawable/"+iconName;
        Resources resources = holder.itemView.getContext().getResources();
        int icon = resources.getIdentifier(uri,null,null);
        holder.assignSupplier(icon,supplier.getSupplierId());

    }

    @Override
    public int getItemCount() {
        return suppliers.size();
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



    public class ViewHolderSupplier extends RecyclerView.ViewHolder {
        ImageView supplierIcon;
        TextView supplierId;
        public ViewHolderSupplier(View itemView) {
            super(itemView);
            supplierIcon = itemView.findViewById(R.id.supplierIcon);
            supplierId = itemView.findViewById(R.id.supplierId);

        }

        public void assignSupplier(int icon,int idSupplier ) {
            supplierIcon.setImageResource(icon);
            supplierId.setText(""+idSupplier);


        }
    }
}
