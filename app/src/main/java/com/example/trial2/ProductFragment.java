package com.example.trial2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    RecyclerView recyclerView;
    DBHelper DB;
    ArrayList<String> name, category, stocks, price;
    CustomAdapter customAdapter;

    SwipeRefreshLayout swipeRefreshLayout;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DB = new DBHelper(activity);
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private android.widget.ListAdapter ListAdapter;

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


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);

        recyclerView = view.findViewById(R.id.listProducts);

        DB = new DBHelper(getActivity());

        name = new ArrayList<>();
        category = new ArrayList<>();
        stocks = new ArrayList<>();
        price = new ArrayList<>();

        showData();

        customAdapter = new CustomAdapter(getActivity(), name, category, stocks, price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = view.findViewById(R.id.fab_btn);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshProd);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshData();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCrudProduct();
            }
        });

        return view;
    }


    private void openCrudProduct() {
        Intent intent = new Intent(getActivity(), CrudProduct.class);
        startActivity(intent);
    }

    void showData(){
        Cursor cursor = DB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(getActivity(), "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                category.add(cursor.getString(1));
                stocks.add(cursor.getString(2));
                price.add(cursor.getString(3));
            }
        }
    }

    private void refreshData() {
        name.clear();
        category.clear();
        stocks.clear();
        price.clear();

        Cursor cursor = DB.readAllData();
        while (cursor.moveToNext()) {
            name.add(cursor.getString(0));
            category.add(cursor.getString(1));
            stocks.add(cursor.getString(2));
            price.add(cursor.getString(3));

            customAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        private Context context;
        private ArrayList name, category, stocks, price;

        CustomAdapter(Context context, ArrayList name, ArrayList category, ArrayList stocks, ArrayList price) {
            this.context = context;
            this.name = name;
            this.category = category;
            this.stocks = stocks;
            this.price = price;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.listName.setText(String.valueOf(name.get(position)));
            holder.listCategory.setText(String.valueOf(category.get(position)));
            holder.listStock.setText(String.valueOf(stocks.get(position)));
            holder.listPrice.setText(String.valueOf(price.get(position)));
        }

        @Override
        public int getItemCount() {
            return name.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView listName, listCategory, listStock, listPrice;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                listName = itemView.findViewById(R.id.listName);
                listCategory = itemView.findViewById(R.id.listCategory);
                listStock = itemView.findViewById(R.id.listStock);
                listPrice = itemView.findViewById(R.id.listPrice);
            }

        }
    }
}