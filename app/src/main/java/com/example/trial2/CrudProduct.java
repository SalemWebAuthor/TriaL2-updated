package com.example.trial2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CrudProduct extends AppCompatActivity {

    EditText name, category, stocks, price;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_product);

        name = findViewById(R.id.name);
        category = findViewById(R.id.category);
        stocks = findViewById(R.id.stocks);
        price = findViewById(R.id.price);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String categoryTXT = category.getText().toString();
                String stocksTXT = stocks.getText().toString();
                String priceTXT = price.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, categoryTXT, stocksTXT, priceTXT);
                if (checkinsertdata == true)
                    Toast.makeText(CrudProduct.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CrudProduct.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String categoryTXT = category.getText().toString();
                String stocksTXT = stocks.getText().toString();
                String priceTXT = price.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, categoryTXT, stocksTXT, priceTXT);
                if (checkupdatedata == true)
                    Toast.makeText(CrudProduct.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CrudProduct.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if (checkdeletedata == true)
                    Toast.makeText(CrudProduct.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CrudProduct.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(CrudProduct.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Category :" + res.getString(1) + "\n");
                    buffer.append("Stocks :" + res.getString(2) + "\n");
                    buffer.append("Price :" + res.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CrudProduct.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
