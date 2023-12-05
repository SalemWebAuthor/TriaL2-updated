package com.example.trial2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclePrac extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper DB;
    ArrayList<String> name, category, stocks, price;
    CustomAdapter customAdapter;

    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_prac);

        recyclerView = findViewById(R.id.recyclerview);

        DB = new DBHelper(this);

        name = new ArrayList<>();
        category = new ArrayList<>();
        stocks = new ArrayList<>();
        price = new ArrayList<>();

        displayData();

        customAdapter = new CustomAdapter(this, name, category, stocks, price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void displayData(){
        Cursor cursor = DB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(RecyclePrac.this, "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                category.add(cursor.getString(1));
                stocks.add(cursor.getString(2));
                price.add(cursor.getString(3));
            }
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

        private Context context;
        private ArrayList name, category, stocks, price;

        CustomAdapter (Context context, ArrayList name, ArrayList category, ArrayList stocks, ArrayList price) {
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
            View view = inflater.inflate(R.layout.recycle_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.prod_name.setText(String.valueOf(name.get(position)));
            holder.prod_category.setText(String.valueOf(category.get(position)));
            holder.prod_stocks.setText(String.valueOf(stocks.get(position)));
            holder.prod_price.setText(String.valueOf(price.get(position)));
        }

        @Override
        public int getItemCount() {
            return name.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView prod_name, prod_category, prod_stocks, prod_price;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                prod_name = itemView.findViewById(R.id.prod_name);
                prod_category = itemView.findViewById(R.id.prod_category);
                prod_stocks = itemView.findViewById(R.id.prod_stocks);
                prod_price = itemView.findViewById(R.id.prod_price);
            }
        }
    }

}
