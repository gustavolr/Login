package com.example.gustavor.login.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gustavor.login.R;
import com.example.gustavor.login.daos.ItemCompraDao;
import com.example.gustavor.login.models.ItemCompra;

import java.util.List;

/**
 * Created by gustavor on 20/06/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private List<ItemCompra> itemCompras;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView qtd;
        public View strike_t;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.items_title);
            qtd = view.findViewById(R.id.items_qtd);
            strike_t = view.findViewById(R.id.strike_t);
        }
    }


    public ItemsAdapter(List<ItemCompra> itemCompras, Context ctx) {
        this.itemCompras = itemCompras;
        mContext = ctx;
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
        holder.qtd.setText("" + itemCompra.getmQtd());
        if (itemCompra.getComprado() == 1) {
            holder.strike_t.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return itemCompras.size();
    }

    public void addList(ItemCompra itemCompra) {
        if (itemCompras.get(0).getmId() == -1) {
            itemCompras.set(0, itemCompra);
            notifyItemChanged(0);
        } else {
            itemCompras.add(itemCompra);
            notifyItemInserted(itemCompras.size() - 1);
        }
    }

    public void removeList(int position) {
        ItemCompraDao itemCompraDao = new ItemCompraDao(mContext);
        itemCompraDao.removeItem(itemCompras.get(position).getmId());
        itemCompras = itemCompraDao.getItemsCompra(itemCompras.get(position).getmListId());
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemCompras.size());
    }

    public void setComprado(int position) {
        ItemCompraDao itemCompraDao = new ItemCompraDao(mContext);
        if (itemCompras.get(position).getComprado() == 1) {
            itemCompraDao.unsetItemComprado(itemCompras.get(position).getmId());
            itemCompras.get(position).setComprado(0);
        } else {
            itemCompraDao.setItemComprado(itemCompras.get(position).getmId());
            itemCompras.get(position).setComprado(1);
        }
        notifyItemChanged(position);
    }
}
