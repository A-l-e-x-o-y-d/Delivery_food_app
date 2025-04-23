package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity{
    ArrayList<Cart_model> arrItemCart = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerItemCart = findViewById(R.id.recyclerItemCart);
        Button order = findViewById(R.id.order);
        TextView full_cost_text_view = findViewById(R.id.res_full_cost);

        for (int i = 0; i < Cart_model.items_product.size(); i++){
            arrItemCart.add(Cart_model.items_product.get(i));
        }

        Cart_adapter adapter = new Cart_adapter(this, arrItemCart);
        recyclerItemCart.setAdapter(adapter);
        recyclerItemCart.setLayoutManager(new LinearLayoutManager(this));

        Integer res_full_cost = null;
        if (Cart_model.isPlus = true){
            Integer full_cost = 0;
            for(int i = 0; i < Cart_model.items_cost.size(); i++){
                full_cost += Integer.parseInt(Cart_model.items_cost.get(i));
            }
            full_cost_text_view.setText(full_cost.toString());
            res_full_cost = full_cost;
            Cart_model.isPlus = false;
        } else if (Cart_model.isPlus == false){
            Integer cost = getIntent().getIntExtra("full_cost",0);
            full_cost_text_view.setText(cost.toString());
            res_full_cost = cost;
        }

        Integer finalRes_full_cost = res_full_cost;
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalRes_full_cost == 0){
                    Toast.makeText(Cart.this, "Корзина пустая", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent switch_to_order = new Intent(Cart.this, Order.class);
                    switch_to_order.putExtra("res_full_cost", finalRes_full_cost.toString());
                    Cart.this.startActivity(switch_to_order);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent switch_to_cart = new Intent(Cart.this, Catalog.class);
        startActivity(switch_to_cart);
    }
}