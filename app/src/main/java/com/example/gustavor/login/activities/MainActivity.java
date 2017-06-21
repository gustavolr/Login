package com.example.gustavor.login.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavor.login.R;
import com.example.gustavor.login.adapters.ListsAdapter;
import com.example.gustavor.login.daos.ListaDao;
import com.example.gustavor.login.models.Lista;
import com.example.gustavor.login.utils.LoginUtils;
import com.example.gustavor.login.utils.RecyclerViewSetUps;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclerView;
    private ListsAdapter mAdapter;
    private ListaDao listaDao;
    private LoginUtils loginUtils;
    private List<Lista> listas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        loginUtils = new LoginUtils(getApplicationContext());
        listaDao = new ListaDao(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_lista);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_add);
        listas = listaDao.getListas(loginUtils.getUserId());
        if (listas.size() == 0) {
            Lista lista = new Lista();
            lista.setmId(-1);
            lista.setmUserId(-1);
            lista.setmListName(getString(R.string.no_lists));
            listas.add(lista);
        }
        mAdapter = new ListsAdapter(listas, getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerViewSetUps recyclerViewSetUps = new RecyclerViewSetUps(getApplicationContext(), mRecyclerView);
        recyclerViewSetUps.setUpItemTouchHelper();
        recyclerViewSetUps.setUpAnimationDecoratorHelper();


        floatingActionButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(getString(R.string.list_title));

                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.ic_format_list_bulleted_black_24dp);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String listName = input.getText().toString();
                                if (listName.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.invalid_list_title), Toast.LENGTH_SHORT).show();
                                } else {
                                    listaDao.insertLista(listName, loginUtils.getUserId());
                                    List<Lista> listas = listaDao.getListas(loginUtils.getUserId());
                                    Lista lista = listas.get(listas.size() - 1);
                                    mAdapter.addList(lista);
                                    Toast.makeText(getApplicationContext(), getString(R.string.list_added), Toast.LENGTH_SHORT).show();
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

    //já que eu tenho a minha propria toolbar, eu tenho que popular ela com meu item de menu
    //no caso o de logout, ele esta definido em menu->toolbar_items.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //aqui eu trato o click dos botãoes de menu, no caso, realizo logout quando clicado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logoff:
                LoginUtils loginUtils = new LoginUtils(getApplicationContext());
                loginUtils.eraseData();
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.logout), Toast.LENGTH_SHORT);
                toast.show();
                final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true; //não sei pq diabos mas precisa desse return true

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }

    }

}
