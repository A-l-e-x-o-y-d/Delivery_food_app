package com.example.delivery_food;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.ViewHolder>{

    Context context;
    ArrayList<Cart_model> arrItemCart;

    Cart_adapter(Context context, ArrayList<Cart_model> arrItemCart){
        this.context = context;
        this.arrItemCart = arrItemCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        Cart_adapter.ViewHolder viewHolder = new Cart_adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_adapter.ViewHolder holder, int position) {
        holder.imgProduct.setImageResource(arrItemCart.get(position).img);
        holder.txtName.setText((arrItemCart).get(position).name);
        holder.txtCost.setText((arrItemCart).get(position).cost);
    }

    @Override
    public int getItemCount() {
        return arrItemCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName, txtCost;
        ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.name);
            txtCost = itemView.findViewById(R.id.cost);
            imgProduct = itemView.findViewById(R.id.view_product);
            ImageView img_delete = itemView.findViewById(R.id.delete_item);

            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cart_model.items_cost.remove(getAdapterPosition());
                    Integer full_cost = 0;
                    for(int i = 0; i < Cart_model.items_cost.size(); i++){
                        full_cost += Integer.parseInt(Cart_model.items_cost.get(i));
                    }
                    Intent switch_to_cart = new Intent(context, Cart.class);
                    switch_to_cart.putExtra("full_cost", full_cost);
                    context.startActivity(switch_to_cart);
                    arrItemCart.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Cart_model.items_product = arrItemCart;;
                }
            });
        }
    }
}
