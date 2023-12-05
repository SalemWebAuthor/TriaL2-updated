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

public class StocksReceived extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelperStock DB;
    ArrayList<String> receive_id, supplier_name, prod_name, order_quantity, price;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stocks_received);

        recyclerView = findViewById(R.id.recycle_stock);

        DB = new DBHelperStock(this);

        receive_id = new ArrayList<>();
        supplier_name = new ArrayList<>();
        prod_name = new ArrayList<>();
        order_quantity = new ArrayList<>();
        price = new ArrayList<>();

        showData();

        customAdapter = new CustomAdapter(this, receive_id, supplier_name, prod_name, order_quantity, price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab_btn_rcv);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCartStock();
            }
        });

    }
    private void openCartStock() {
        Intent intent = new Intent(this, CartStock.class);
        startActivity(intent);
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

    public class CustomAdapter extends RecyclerView.Adapter<StocksReceived.CustomAdapter.MyViewHolder> {

        private Context context;
        private ArrayList receive_id, supplier_name, prod_name, order_quantity, price;

        CustomAdapter(Context context, ArrayList receive_id, ArrayList supplier_name, ArrayList prod_name, ArrayList order_quantity, ArrayList price) {
            this.context = context;
            this.receive_id = receive_id;
            this.supplier_name = supplier_name;
            this.prod_name = prod_name;
            this.order_quantity = order_quantity;
            this.price = price;
        }

        @NonNull
        @Override
        public StocksReceived.CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.receive_stocks_layout, parent, false);
            return new StocksReceived.CustomAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StocksReceived.CustomAdapter.MyViewHolder holder, int position) {
            holder.rcv_supplier_name.setText(String.valueOf(supplier_name.get(position)));
            holder.rcv_prod.setText(String.valueOf(prod_name.get(position)));
            holder.rcv_stockQty.setText(String.valueOf(order_quantity.get(position)));
            holder.rcv_price.setText(String.valueOf(price.get(position)));

        }

        @Override
        public int getItemCount() {
            return receive_id.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView rcv_prod, rcv_supplier_name, rcv_stockQty, rcv_price;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                rcv_supplier_name = itemView.findViewById(R.id.rcv_supplier_name);
                rcv_prod = itemView.findViewById(R.id.rcv_prod);
                rcv_stockQty = itemView.findViewById(R.id.rcv_stockQty);
                rcv_price = itemView.findViewById(R.id.rcv_price);
            }

        }
    }

    }
