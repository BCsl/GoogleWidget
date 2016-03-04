package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.adapter.ImageAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/3/2 0002.
 */
public class PaletteActivity extends BaseActivity implements ImageAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private static List<String> sItems = new ArrayList<>();

    static {
        for (int i = 1; i <= 10; i++) {
            sItems.add("http://lorempixel.com/500/500/nature/" + i);
        }
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PaletteActivity.class);
        return intent;
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_appbarlayout);
    }

    @Override
    protected void initView() {
        showBackIcon(true);
        setToolbarTitle("Palette Example");
        mRecyclerView = (RecyclerView) findViewById(R.id.coordinator_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        setRecyclerAdapter();
        mRecyclerView.scheduleLayoutAnimation();
    }

    private void setRecyclerAdapter() {
        mRecyclerView.setAdapter(new ImageAdapter(sItems).setOnItemClickListener(this));
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setRecyclerAdapter();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
//        Snackbar.make(mRecyclerView, position + ":click", Snackbar.LENGTH_SHORT).show();
        DetailActivity.navigate(this, view.findViewById(R.id.item_image), sItems.get(position));
    }
}
