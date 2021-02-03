package com.geekbrains.nasalib.view.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.databinding.ActivityMainBinding;
import com.geekbrains.nasalib.presenter.MainPresenter;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private ActivityMainBinding mainBinding;
    private final String TAG = "Retrofit";
    private int columns;
    private final String stdQuery = "hubble";
    @InjectPresenter
    MainPresenter mainPresenter;

    private MainRVA mainRVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        initToolbar();
        columns = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        mainPresenter.requestFromServer(stdQuery);
    }

    private void initRecycler(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columns);
        mainBinding.recyclerView.setLayoutManager(gridLayoutManager);
        mainRVA = new MainRVA(mainPresenter.getMedia(), mainPresenter);
        mainBinding.recyclerView.setAdapter(mainRVA);
    }

    @Override
    public void updateRecyclerView(){
        initRecycler();
        mainRVA.notifyDataSetChanged();
    }

    private void initToolbar() {
        setSupportActionBar(mainBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar_main, menu);

        MenuItem searchViewItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchViewItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mainPresenter.requestFromServer(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ImageView closeButton = searchView.findViewById(R.id.search_close_btn);
        EditText et = searchView.findViewById(R.id.search_src_text);
        closeButton.setOnClickListener(v -> {
            String query = et.getText().toString();
            if ("".equals(query)) {
                searchView.onActionViewCollapsed();
                searchViewItem.collapseActionView();
            } else {
                et.setText("");
            }
        });
        return true;
    }
}