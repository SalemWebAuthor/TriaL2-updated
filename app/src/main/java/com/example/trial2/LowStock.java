package com.example.trial2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LowStock extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper DB;
    ArrayList<String> name, category, stocks;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowstocks);

        recyclerView = findViewById(R.id.lowstockData);

        DB = new DBHelper(this);

        name = new ArrayList<>();
        category = new ArrayList<>();
        stocks = new ArrayList<>();

        showData();

        customAdapter = new CustomAdapter(this, name, category, stocks);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    void showData() {
        Cursor cursor = DB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else  {
                while (cursor.moveToNext()) {
                    name.add(cursor.getString(0));
                    category.add(cursor.getString(1));
                    stocks.add(cursor.getString(2));

            }
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<LowStock.CustomAdapter.MyViewHolder> {

        private Context context;
        private ArrayList name, category, stocks;

        CustomAdapter(Context context, ArrayList name, ArrayList category, ArrayList stocks) {
            this.context = context;
            this.name = name;
            this.category = category;
            this.stocks = stocks;

        }

        @NonNull
        @Override
        public LowStock.CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.lowstock_layout, parent, false);
            return new LowStock.CustomAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LowStock.CustomAdapter.MyViewHolder holder, int position) {
            holder.low_prod.setText(String.valueOf(name.get(position)));
            holder.low_category.setText(String.valueOf(category.get(position)));
            holder.low_stockQty.setText(String.valueOf(stocks.get(position)));
        }

        @Override
        public int getItemCount() {
            return name.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView low_prod, low_category, low_stockQty;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                low_prod = itemView.findViewById(R.id.low_prod);
                low_category = itemView.findViewById(R.id.low_category);
                low_stockQty = itemView.findViewById(R.id.low_stockQty);
            }

        }
    }
}