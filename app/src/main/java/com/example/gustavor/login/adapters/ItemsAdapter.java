package com.example.gustavor.login.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gustavor.login.R;
import com.example.gustavor.login.models.ItemCompra;

import java.util.List;

/**
 * Created by gustavor on 20/06/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private List<ItemCompra> itemCompras;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView qtd;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.items_title);
            qtd = view.findViewById(R.id.items_qtd);
        }
    }


    public ItemsAdapter(List<ItemCompra> itemCompras) {
        this.itemCompras = itemCompras;
    }

    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compra_item, parent, false);

        return new ItemsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.MyViewHolder holder, int position) {
        ItemCompra itemCompra = itemCompras.get(position);
        holder.title.setText(itemCompra.getmItemName());
        holder.qtd.setText(itemCompra.getmQtd());
    }

    @Override
    public int getItemCount() {
        return itemCompras.size();
    }
}
