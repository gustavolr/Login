package com.example.gustavor.login.activities;

import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavor.login.R;
import com.example.gustavor.login.adapters.ItemsAdapter;
import com.example.gustavor.login.adapters.ListsAdapter;
import com.example.gustavor.login.daos.ItemCompraDao;
import com.example.gustavor.login.daos.ListaDao;
import com.example.gustavor.login.models.ItemCompra;
import com.example.gustavor.login.models.Lista;
import com.example.gustavor.login.utils.ComprasRecyclerViewSetUp;
import com.example.gustavor.login.utils.LoginUtils;
import com.example.gustavor.login.utils.RecyclerViewSetUps;

import java.util.List;

public class ComprasActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclerView;
    private ItemsAdapter mAdapter;
    private ItemCompraDao itemCompraDao;
    private List<ItemCompra> itemCompras;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Bundle b = getIntent().getExtras();
        String name = b.getString("NAME");
        this.setTitle(name);
        id = b.getInt("ID");
        itemCompraDao = new ItemCompraDao(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_lista);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_add);
        itemCompras = itemCompraDao.getItemsCompra(id);
        mAdapter = new ItemsAdapter(itemCompras, getApplicationContext());
        if (itemCompras.size() == 0) {
            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setmId(-1);
            itemCompra.setComprado(0);
            itemCompra.setmItemName(getString(R.string.no_items));
            itemCompra.setmQtd(0);
            itemCompras.add(itemCompra);
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        ComprasRecyclerViewSetUp recyclerViewSetUps = new ComprasRecyclerViewSetUp(getApplicationContext(), mRecyclerView);
        recyclerViewSetUps.setUpItemTouchHelper();
        recyclerViewSetUps.setUpAnimationDecoratorHelper();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ComprasActivity.this);
                alertDialog.setTitle(getString(R.string.item_title));

                final EditText input = new EditText(ComprasActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                input.setHint(getString(R.string.item_hint));
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.ic_format_list_bulleted_black_24dp);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String itemName = input.getText().toString();
                                int quantidade;
                                if (itemName.contains(", ")) {
                                    quantidade = Integer.parseInt(itemName.split(", ")[1]);
                                    itemName = itemName.split(", ")[0];
                                } else if (itemName.contains(",")) {
                                    quantidade = Integer.parseInt(itemName.split(",")[1]);
                                    itemName = itemName.split(",")[0];
                                } else {
                                    quantidade = 1;
                                }
                                if (itemName.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.invalid_item_title), Toast.LENGTH_SHORT).show();
                                } else {
                                    itemCompraDao.insertItemCompra(id, itemName, quantidade);
                                    List<ItemCompra> itemCompras = itemCompraDao.getItemsCompra(id);
                                    ItemCompra itemCompra = itemCompras.get(itemCompras.size() - 1);
                                    mAdapter.addList(itemCompra);
                                    Toast.makeText(getApplicationContext(), getString(R.string.item_added), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        });
    }
}
