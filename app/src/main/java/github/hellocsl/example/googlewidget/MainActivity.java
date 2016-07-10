package github.hellocsl.example.googlewidget;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import github.hellocsl.example.googlewidget.adapter.RecyclerViewAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;
import github.hellocsl.example.googlewidget.example.AppBarLayoutActivity;
import github.hellocsl.example.googlewidget.example.CollapsingToolbarActivity;
import github.hellocsl.example.googlewidget.example.CoordinatorLayoutActivity;
import github.hellocsl.example.googlewidget.example.PaletteActivity;
import github.hellocsl.example.googlewidget.example.TabLayoutActivity;

public class MainActivity extends BaseActivity implements RecyclerViewAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        setToolbarTitle(R.string.app_name);
        setToolbarSubTitle(R.string.app_desc);
        showBackIcon(true);
        initDrawer();
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_github:
                        openMyGitHub();
                        return true;
                    default:
                        Snackbar.make(mRecyclerView, "click menu:" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_github:
                openMyGitHub();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void openMyGitHub() {
        Uri uri = Uri.parse("https://github.com/BCsl");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    @Override
    protected void initData() {
        String[] items = getResources().getStringArray(R.array.items);
        ArrayList<String> itemList = new ArrayList<String>();
        for (String temp : items) {
            itemList.add(temp);
        }
        mRecyclerView.setAdapter(new RecyclerViewAdapter(itemList).setOnItemClickListener(this));
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                startActivity(PaletteActivity.newIntent(MainActivity.this));
                break;
            case 2:
                startActivity(CollapsingToolbarActivity.newIntent(MainActivity.this));
                break;
            case 3:
                startActivity(TabLayoutActivity.newIntent(MainActivity.this));
                break;
            case 4:
                startActivity(AppBarLayoutActivity.newIntent(MainActivity.this));
                break;
            case 5:
                startActivity(CoordinatorLayoutActivity.newIntent(MainActivity.this));
                break;
            default:
                break;
        }
    }
}
