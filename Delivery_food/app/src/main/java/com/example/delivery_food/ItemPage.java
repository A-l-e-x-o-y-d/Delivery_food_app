package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        TextView name = findViewById(R.id.nameItem);
        TextView cost = findViewById(R.id.price);
        ImageView image_item = findViewById(R.id.view_item);
        Button add_to_cart = findViewById(R.id.add_to_cart);

        name.setText(getIntent().getStringExtra("name"));
        cost.setText(getIntent().getStringExtra("cost"));
        image_item.setImageResource(getIntent().getIntExtra("img", 0));

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String name_product = getIntent().getStringExtra("name");
              String cost_product = getIntent().getStringExtra("cost");
              int img_product = getIntent().getIntExtra("img", 0);
              int id_product = getIntent().getIntExtra("id", 0);

              Cart_model.items_product.add(new Cart_model(img_product, name_product, cost_product, id_product));
              Cart_model.items_cost.add(cost_product);

              Intent switch_to_products = new Intent(ItemPage.this, Catalog.class);
              ItemPage.this.startActivity(switch_to_products);
              Cart_model.isPlus = true;
              Toast.makeText(ItemPage.this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show();
            }
        });
    }
}