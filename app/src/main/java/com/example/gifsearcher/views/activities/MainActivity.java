package com.example.gifsearcher.views.activities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.example.gifsearcher.R;
import com.example.gifsearcher.views.fragments.MainFragment;
import com.example.gifsearcher.views.fragments.ResultsFragment;

public class MainActivity extends AppCompatActivity {

    private MainFragment mMainFragment;
    private ResultsFragment mResultsFragment;
    private ScrollView mScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        mScrollView = findViewById(R.id.scroll);

        mMainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mMainFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = menuItem.getActionView().findViewById(R.id.action_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();

                searchGifs(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    private void searchGifs(String query) {

        if (mResultsFragment == null) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            mResultsFragment = ResultsFragment.newInstance(query, metrics.heightPixels);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mResultsFragment)
                    .commit();
        } else {
            mResultsFragment.showResults(query);
        }

        scrollToResults();
    }

    private void scrollToResults() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollView.smoothScrollTo(0, mMainFragment.getRecycler().getBottom() + getActionBarSize());
            }
        }, 2000);
    }

    private int getActionBarSize() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return 0;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info != null && info.isConnectedOrConnecting();
    }
}