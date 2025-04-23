package com.example.delivery_food;

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

public class View_product_adapter extends RecyclerView.Adapter<View_product_adapter.ViewHolder> {

    Context context;
    ArrayList<View_product_model> arrViewProducts;

    View_product_adapter(Context context, ArrayList<View_product_model> arrViewProducts){
        this.context = context;
        this.arrViewProducts = arrViewProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgViewProduct.setImageResource(arrViewProducts.get(position).img);
        holder.txtName.setText((arrViewProducts).get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Products.class);
                int numOfViewProduct = holder.getAdapterPosition();
                intent.putExtra("numOfViewProduct", numOfViewProduct);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return arrViewProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgViewProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.name);
            imgViewProduct = itemView.findViewById(R.id.view_product);
        }
    }
}
