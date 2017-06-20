package com.example.gustavor.login.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gustavor.login.R;
import com.example.gustavor.login.daos.ListaDao;
import com.example.gustavor.login.models.Lista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavor on 20/06/2017.
 */

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.MyViewHolder> {

    private List<Lista> listas;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public FrameLayout card;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.list_title);
            card = view.findViewById(R.id.card);
        }
    }


    public ListsAdapter(List<Lista> listas, Context ctx) {
        this.listas = listas;
        mContext = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Lista lista = listas.get(position);
        holder.title.setText(lista.getmListName());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public void addList(Lista lista){
        if(listas == null){
            listas = new ArrayList<>();
            listas.add(lista);
        } else {
            listas.add(lista);
        }
        notifyItemInserted(listas.size() - 1);
    }

    public void removeList(int position){
        ListaDao listaDao = new ListaDao(mContext);
        listaDao.removeLista(listas.get(position).getmId());
        listas = listaDao.getListas(listas.get(0).getmUserId());
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listas.size());
    }
}
