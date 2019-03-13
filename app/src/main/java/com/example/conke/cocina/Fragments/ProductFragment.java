package com.example.conke.cocina.Fragments;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.conke.cocina.Adapters.productAdapter;
import com.example.conke.cocina.Entities.Product;
import com.example.conke.cocina.Home;
import com.example.conke.cocina.R;
import com.example.conke.cocina.SQL.SQLQueries;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerProduct;
    ArrayList<Product> products ;
    private Button btnOrder;
    private TextView totalOrder;
    private Home.ComunicacionInterfaz interfaz;
    private Float total= 0.0f;
    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_product, container, false);
        btnOrder=vista.findViewById(R.id.btnOrder);
        totalOrder = vista.findViewById(R.id.totalOrder);
        products = new ArrayList<>();
        recyclerProduct = vista.findViewById(R.id.productRecycler);
        recyclerProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        loadProducts(interfaz.getSupplierId());
        final productAdapter adapter = new productAdapter(getContext(), products, new productAdapter.itemsListener() {
            @Override
            public void addProduct(View v, int position) {
                 products.get(position).setProductQTY(products.get(position).getProductQTY()+1);
                 setTotalOrder();
            }

            @Override
            public void subtracProduct(View v, int adapterPosition) {
                if (products.get(adapterPosition).getProductQTY()>0){
                products.get(adapterPosition).setProductQTY(products.get(adapterPosition).getProductQTY()-1);
                }else{
                    products.get(adapterPosition).setProductQTY(0);
                }
                setTotalOrder();

            }
        });
        recyclerProduct.setAdapter(adapter);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                builder.setMessage("Confirmar Pedido")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(),"Confrimado",Toast.LENGTH_LONG).show();
                                makeOrder(products, interfaz.getStoreId(),interfaz.getSupplierId(),total);
                            }
                        }).setNegativeButton("Cancelar",null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return vista;
    }

   private void makeOrder(ArrayList <Product> products, int storeId, int supplierId, float total) {
        long result =0 ;
       SQLQueries queries = new SQLQueries(getContext());
       result =queries.insertOrder(products,storeId,supplierId,total);
       products.clear();
       getFragmentManager().popBackStack();
    }

    private void setTotalOrder() {
        total = 0.0f;
        Product product;
        for (int i=0 ; i<products.size(); i++  ){
            product= new Product();
            product = products.get(i);
            total = total + (product.getProductQTY()*product.getProductCost());
        }
      totalOrder.setText("$"+total);
    }


    private void loadProducts(int supplierId) {
        products.clear();
        SQLQueries queries = new SQLQueries(getContext());
        products=queries.loadProductsBySupplier(supplierId);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public void setInterfaz(Home.ComunicacionInterfaz interfaz){
        this.interfaz = interfaz;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
