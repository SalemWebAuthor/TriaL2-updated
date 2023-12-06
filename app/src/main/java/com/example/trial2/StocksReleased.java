package com.example.trial2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StocksReleased extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView recyclerView;
    DBHelperStock DB;
    ArrayList<String> receive_id, supplier_name, prod_name, order_quantity, price;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stocks_released);

        recyclerView = findViewById(R.id.recycle_release);

        DB = new DBHelperStock(this);

        receive_id = new ArrayList<>();
        supplier_name = new ArrayList<>();
        prod_name = new ArrayList<>();
        order_quantity = new ArrayList<>();
        price = new ArrayList<>();

        showData();

        customAdapter = new CustomAdapter(this, receive_id, supplier_name, prod_name, order_quantity, price, this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
    void showData(){
        Cursor cursor = DB.readAllDatastock();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                receive_id.add(cursor.getString(0));
                supplier_name.add(cursor.getString(1));
                prod_name.add(cursor.getString(2));
                order_quantity.add(cursor.getString(3));
                price.add(cursor.getString(4));
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Cursor cursor = DB.readAllDatastock();
        cursor.moveToPosition(position);

        String rls_id = cursor.getString(0);
        String rls_supplier_name = cursor.getString(1);
        String rls_prod = cursor.getString(2);
        String rls_stockQty = cursor.getString(3);
        String rls_price = cursor.getString(4);

        Intent intent = new Intent(this, ReleaseItemProfile.class);

        intent.putExtra("rls_id", rls_id);
        intent.putExtra("rls_supplier_name", rls_supplier_name);
        intent.putExtra("rls_prod", rls_prod);
        intent.putExtra("rls_stockQty", rls_stockQty);
        intent.putExtra("rls_price", rls_price);

        startActivity(intent);
    }


    public class CustomAdapter extends RecyclerView.Adapter<StocksReleased.CustomAdapter.MyViewHolder> {

        private final RecyclerViewInterface recyclerViewInterface;
        private Context context;
        private ArrayList receive_id, supplier_name, prod_name, order_quantity, price;

        CustomAdapter(Context context, ArrayList receive_id, ArrayList supplier_name, ArrayList prod_name,
                      ArrayList order_quantity, ArrayList price, RecyclerViewInterface recyclerViewInterface) {
            this.context = context;
            this.receive_id = receive_id;
            this.supplier_name = supplier_name;
            this.prod_name = prod_name;
            this.order_quantity = order_quantity;
            this.price = price;
            this.recyclerViewInterface = recyclerViewInterface;
        }

        @NonNull
        @Override
        public StocksReleased.CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.release_stocks_layout, parent, false);
            return new StocksReleased.CustomAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StocksReleased.CustomAdapter.MyViewHolder holder, int position) {
            holder.rls_supplier_name.setText(String.valueOf(supplier_name.get(position)));
            holder.rls_prod.setText(String.valueOf(prod_name.get(position)));
            holder.rls_stockQty.setText(String.valueOf(order_quantity.get(position)));
            holder.rls_price.setText(String.valueOf(price.get(position)));

        }

        @Override
        public int getItemCount() {
            return receive_id.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView rls_prod, rls_supplier_name, rls_stockQty, rls_price;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                rls_supplier_name = itemView.findViewById(R.id.rls_supplier_name);
                rls_prod = itemView.findViewById(R.id.rls_prod);
                rls_stockQty = itemView.findViewById(R.id.rls_stockQty);
                rls_price = itemView.findViewById(R.id.rls_price);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (recyclerViewInterface != null){
                            int pos = getAdapterPosition();

                            if (pos != RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(pos);
                            }
                        }
                    }
                });
            }

        }
    }

}