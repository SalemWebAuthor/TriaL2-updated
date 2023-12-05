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

public class CartStock extends AppCompatActivity {

    EditText cart_rcv_id, cart_rcv_supplier, cart_rcv_prod, cart_rcv_order, cart_rcv_price;
    Button insert, update, delete, view;
    DBHelperStock DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_receive);

        cart_rcv_id = findViewById(R.id.cart_rcv_id);
        cart_rcv_supplier = findViewById(R.id.cart_rcv_supplier);
        cart_rcv_prod = findViewById(R.id.cart_rcv_prod);
        cart_rcv_order = findViewById(R.id.cart_rcv_order);
        cart_rcv_price = findViewById(R.id.cart_rcv_price);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelperStock(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = cart_rcv_id.getText().toString();
                String supplierTXT = cart_rcv_supplier.getText().toString();
                String prodnameTXT = cart_rcv_prod.getText().toString();
                String orderTXT = cart_rcv_order.getText().toString();
                String priceTXT = cart_rcv_price.getText().toString();

                Boolean checkinsertdata = DB.addstock(idTXT, supplierTXT, prodnameTXT, orderTXT, priceTXT);
                if (checkinsertdata == true)
                    Toast.makeText(CartStock.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CartStock.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = cart_rcv_id.getText().toString();
                String supplierTXT = cart_rcv_supplier.getText().toString();
                String prodnameTXT = cart_rcv_prod.getText().toString();
                String orderTXT = cart_rcv_order.getText().toString();
                String priceTXT = cart_rcv_price.getText().toString();

                Boolean checkupdatedata = DB.updatestock(idTXT, supplierTXT, prodnameTXT, orderTXT, priceTXT);
                if (checkupdatedata == true)
                    Toast.makeText(CartStock.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CartStock.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = cart_rcv_id.getText().toString();

                Boolean checkdeletedata = DB.deletestock(idTXT);
                if (checkdeletedata == true)
                    Toast.makeText(CartStock.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CartStock.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdatastock();
                if (res.getCount() == 0) {
                    Toast.makeText(CartStock.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Supplier :" + res.getString(1) + "\n");
                    buffer.append("Product Name :" + res.getString(2) + "\n");
                    buffer.append("Quantity :" + res.getString(3) + "\n");
                    buffer.append("Price :" + res.getString(4) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CartStock.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
