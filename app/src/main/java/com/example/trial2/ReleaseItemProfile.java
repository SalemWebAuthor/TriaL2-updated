package com.example.trial2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReleaseItemProfile extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_item);

        String cart_id = getIntent().getStringExtra("rls_id");
        String supplier_name = getIntent().getStringExtra("rls_supplier_name");
        String prod_name = getIntent().getStringExtra("rls_prod");
        String prod_stock = getIntent().getStringExtra("rls_stockQty");
        String prod_price = getIntent().getStringExtra("rls_price");

        // Find TextViews in your layout
        TextView id = findViewById(R.id.rls_id);
        TextView supplier = findViewById(R.id.rls_supname_prof);
        TextView product = findViewById(R.id.prod_name_prof);
        TextView stocks = findViewById(R.id.listStock_prof);
        TextView price = findViewById(R.id.listPrice_prof);

        // Set data to TextViews with null checks
        if (id != null && cart_id != null) {
            id.setText(cart_id);
        }

        if (supplier != null && supplier_name != null) {
            supplier.setText(supplier_name);
        }

        if (product != null && prod_name != null) {
            product.setText(prod_name);
        }

        if (stocks != null && prod_stock != null) {
            stocks.setText(prod_stock);
        }

        if (price != null && prod_price != null) {
            price.setText(prod_price);
        }
    }

}
