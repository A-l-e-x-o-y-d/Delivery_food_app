package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Products extends AppCompatActivity {

//    ArrayList<Product_model> arrFruitsProducts = new ArrayList<>();
//    ArrayList<Product_model> arrVegetablesProducts = new ArrayList<>();
//    ArrayList<Product_model> arrMeatProducts = new ArrayList<>();
//    ArrayList<Product_model> arrDrinksProducts = new ArrayList<>();
//    ArrayList<Product_model> arrBreadProducts = new ArrayList<>();

    ArrayList<Product_model> arrProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        int numOfViewProduct = getIntent().getIntExtra("numOfViewProduct",0);

        RecyclerView recyclerProducts = findViewById(R.id.recyclerItemCart);
        TextView view_product = findViewById(R.id.viewProduct);
        ImageView cart = findViewById(R.id.cart);
        ImageView personal_account = findViewById(R.id.personal_account);

        personal_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_personal_account = new Intent(Products.this, Personal_account.class);
                startActivity(switch_personal_account);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_cart = new Intent(Products.this, Cart.class);
                startActivity(switch_cart);
            }
        });

        recyclerProducts.setLayoutManager(new LinearLayoutManager(this));

        if(numOfViewProduct == 0){
            arrProducts.add(new Product_model(R.drawable.apple, "Яблоко","50", 1));
            arrProducts.add(new Product_model(R.drawable.pear, "Груша","40",2));
            arrProducts.add(new Product_model(R.drawable.cherry, "Вишня","70", 3));
            arrProducts.add(new Product_model(R.drawable.orange, "Апельсин","60",4));
            arrProducts.add(new Product_model(R.drawable.banana, "Банан","55",5));

            Product_adapter adapter = new Product_adapter(this, arrProducts);
            recyclerProducts.setAdapter(adapter);

            view_product.setText("Фрукты");
        }
        else if(numOfViewProduct == 1){
            arrProducts.add(new Product_model(R.drawable.cabbage, "Капуста","100",6));
            arrProducts.add(new Product_model(R.drawable.potato, "Картофель","120",7));
            arrProducts.add(new Product_model(R.drawable.cucumbers, "Огурец","90",8));
            arrProducts.add(new Product_model(R.drawable.tomato, "Помидор","95",9));
            arrProducts.add(new Product_model(R.drawable.carrot, "Морковь","100",10));

            Product_adapter adapter = new Product_adapter(this, arrProducts);
            recyclerProducts.setAdapter(adapter);

            view_product.setText("Овощи");
        }
        else if(numOfViewProduct == 2){
            arrProducts.add(new Product_model(R.drawable.beef, "Говядина","300",11));
            arrProducts.add(new Product_model(R.drawable.sheepmeat, "Баранина","400",12));
            arrProducts.add(new Product_model(R.drawable.pork, "Свинина","350",13));
            arrProducts.add(new Product_model(R.drawable.chicken, "Курица","340",14));

            Product_adapter adapter = new Product_adapter(this, arrProducts);
            recyclerProducts.setAdapter(adapter);

            view_product.setText("Мясо, птица");
        }
        else if(numOfViewProduct == 3){
            arrProducts.add(new Product_model(R.drawable.water, "Вода","100",15));
            arrProducts.add(new Product_model(R.drawable.cocacola, "Coca-cola","130",16));
            arrProducts.add(new Product_model(R.drawable.fanta, "Fanta","120",17));
            arrProducts.add(new Product_model(R.drawable.sevenup, "Seven-Up","110",18));
            arrProducts.add(new Product_model(R.drawable.pepsi, "Pepsi","115",19));

            Product_adapter adapter = new Product_adapter(this, arrProducts);
            recyclerProducts.setAdapter(adapter);

            view_product.setText("Напитки");
        }
        else if(numOfViewProduct == 4){
            arrProducts.add(new Product_model(R.drawable.blackbread, "Белый хлеб","50",20));
            arrProducts.add(new Product_model(R.drawable.blackbread, "Чёрный хлеб","60",21));
            arrProducts.add(new Product_model(R.drawable.donut, "Пончики","100",22));
            arrProducts.add(new Product_model(R.drawable.breadwithcherry, "Вишнёвая булочка","55",23));

            Product_adapter adapter = new Product_adapter(this, arrProducts);
            recyclerProducts.setAdapter(adapter);

            view_product.setText("Хлеб, выпечка");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent switch_to_catalog = new Intent(Products.this, Catalog.class);
        Products.this.startActivity(switch_to_catalog);
    }
}