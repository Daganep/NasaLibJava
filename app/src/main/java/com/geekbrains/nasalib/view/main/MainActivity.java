package com.geekbrains.nasalib.view.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.databinding.ActivityMainBinding;
import com.geekbrains.nasalib.model.entity.Element;
import com.geekbrains.nasalib.presenter.MainPresenter;
import com.geekbrains.nasalib.view.aboutapp.AboutActivity;

import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private ActivityMainBinding mainBinding;
    private int columns;
    private String lastQuery;
    private MainRVA mainRVA;
    @InjectPresenter
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        columns = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        initToolbar();
        initRecycler();
        loadLastKey();
        String currentQuery = getString(R.string.std_keyword);
        if(lastQuery.equals(getString(R.string.empty_string)))lastQuery = currentQuery;
        mainPresenter.requestFromDB();
    }

    private void initRecycler(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columns);
        mainBinding.mainRV.setLayoutManager(gridLayoutManager);
        mainRVA = new MainRVA();
        mainBinding.mainRV.setAdapter(mainRVA);
    }

    @Override
    public void updateRecyclerView(List<Element> elements){
        if (elements != null){
            mainBinding.mainPB.setVisibility(View.GONE);
            mainBinding.emptyResult.setVisibility(View.GONE);
            mainBinding.mainRV.setVisibility(View.VISIBLE);
            saveLastKey(lastQuery);
            mainRVA.setMedia(elements);
            mainRVA.notifyDataSetChanged();
        }
    }

    @Override
    public void checkDB(List<Element> elements){
        if (elements != null && elements.size() != 0){
            updateRecyclerView(elements);
        }
        else {
            mainPresenter.requestFromServer(lastQuery);
        }
    }

    @Override
    public void showError(int msg) {
        mainBinding.mainPB.setVisibility(View.GONE);
        mainBinding.emptyResult.setText(msg);
        mainBinding.emptyResult.setVisibility(View.VISIBLE);
        mainBinding.mainRV.setVisibility(View.GONE);
    }



    private void initToolbar() {
        setSupportActionBar(mainBinding.mainToolbar);
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
                mainBinding.emptyResult.setVisibility(View.GONE);
                mainBinding.mainRV.setVisibility(View.GONE);
                mainBinding.mainPB.setVisibility(View.VISIBLE);
                mainPresenter.requestFromServer(query);
                lastQuery = query;
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

        MenuItem aboutItem = menu.findItem(R.id.about);
        aboutItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, AboutActivity.class));
            return false;
        });
        return true;
    }

    private void saveLastKey(String key){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.last_key), key);
        editor.apply();
    }

    private void loadLastKey(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        lastQuery = prefs.getString(getString(R.string.last_key), getString(R.string.empty_string));
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.saveLastResult();
    }
}