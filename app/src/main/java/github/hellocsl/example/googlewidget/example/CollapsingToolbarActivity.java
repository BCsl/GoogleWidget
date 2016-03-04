package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.adapter.RecyclerViewAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/3/1 0001.
 */
public class CollapsingToolbarActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener, RecyclerViewAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private AppBarLayout mAppBarLayout;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CollapsingToolbarActivity.class);
        return intent;
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_collapsing);
    }

    @Override
    protected void initView() {
        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        collapsingToolbarLayout.setTitle("CollapsingLayout");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimaryIcons));
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.coordinator_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
        showBackIcon(true);
    }

    @Override
    protected void initData() {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            res.add("item content :" + i);
        }
        mRecyclerView.setAdapter(new RecyclerViewAdapter(res).setOnItemClickListener(this));
    }

    @Override
    public void onItemClick(View view, int position) {
        Snackbar.make(mRecyclerView, position + ":click", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAppBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        mRefreshLayout.setEnabled(verticalOffset == 0);
    }
}
