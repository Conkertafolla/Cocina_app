package com.example.conke.cocina.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conke.cocina.Adapters.supplierAdapter;
import com.example.conke.cocina.Entities.Supplier;
import com.example.conke.cocina.Home;
import com.example.conke.cocina.R;
import com.example.conke.cocina.SQL.SQLQueries;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SupplierFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SupplierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SupplierFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerSupplier;
    ArrayList<Supplier> suppliers;
    private Home.ComunicacionInterfaz interfaz;


    public SupplierFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SupplierFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SupplierFragment newInstance(String param1, String param2) {
        SupplierFragment fragment = new SupplierFragment();
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
        View vista =  inflater.inflate(R.layout.fragment_supplier, container, false);
        suppliers= new ArrayList<>();
        recyclerSupplier= vista.findViewById(R.id.supplierRecycler);
        recyclerSupplier.setLayoutManager(new LinearLayoutManager(getContext()));
        loadSuppliers();
        supplierAdapter adapter = new supplierAdapter(suppliers);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaz.loadProducts(suppliers.get(recyclerSupplier.getChildAdapterPosition(v)).getSupplierId());
            }
        });
        recyclerSupplier.setAdapter(adapter);
        return vista;
    }

    public void setInterfaz(Home.ComunicacionInterfaz interfaz){
        this.interfaz = interfaz;
    }

    private void loadSuppliers() {
        SQLQueries queries = new SQLQueries(getContext());
        suppliers=queries.loadSuppliers();


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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
