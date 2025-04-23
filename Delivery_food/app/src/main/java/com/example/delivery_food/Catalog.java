package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Catalog extends AppCompatActivity {

    ArrayList<View_product_model> arrViewProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerItemCart);

        ImageView personal_account = findViewById(R.id.personal_account);
        ImageView cart = findViewById(R.id.cart);

        personal_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_personal_account = new Intent(Catalog.this, Personal_account.class);
                startActivity(switch_personal_account);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_cart = new Intent(Catalog.this, Cart.class);
                startActivity(switch_cart);
            }
        });

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        arrViewProducts.add(new View_product_model(R.drawable.fruits, "Фрукты"));
        arrViewProducts.add(new View_product_model(R.drawable.vegetables, "Овощи"));
        arrViewProducts.add(new View_product_model(R.drawable.meat, "Мясо, птица"));
        arrViewProducts.add(new View_product_model(R.drawable.drinks, "Напитки"));
        arrViewProducts.add(new View_product_model(R.drawable.baked, "Хлеб, выпечка"));

        View_product_adapter adapter = new View_product_adapter(this, arrViewProducts);
        recyclerViewProducts.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        this.finishAffinity();
    }
}