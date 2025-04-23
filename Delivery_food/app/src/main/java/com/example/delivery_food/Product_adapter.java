package com.example.delivery_food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Product_adapter extends RecyclerView.Adapter<Product_adapter.ViewHolder> {

    Context context;
    ArrayList<Product_model> arrProducts;

    Product_adapter(Context context, ArrayList<Product_model> arrProducts){
        this.context = context;
        this.arrProducts = arrProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgProduct.setImageResource(arrProducts.get(position).getImg());
        holder.txtName.setText((arrProducts).get(position).getName());
        holder.txtCost.setText((arrProducts).get(position).getCost());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_to_item_page = new Intent(context, ItemPage.class);
                switch_to_item_page.putExtra("img", arrProducts.get(position).getImg());
                switch_to_item_page.putExtra("name", arrProducts.get(position).getName());
                switch_to_item_page.putExtra("cost", arrProducts.get(position).getCost());
                switch_to_item_page.putExtra("id", arrProducts.get(position).getId());
                context.startActivity(switch_to_item_page);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtCost;
        ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.name);
            txtCost = itemView.findViewById(R.id.cost);
            imgProduct = itemView.findViewById(R.id.view_product);
        }
    }
}
