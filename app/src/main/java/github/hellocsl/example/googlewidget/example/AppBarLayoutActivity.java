package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.adapter.RecyclerViewAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/2/29 0029.
 */
public class AppBarLayoutActivity extends BaseActivity implements RecyclerViewAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AppBarLayoutActivity.class);
        return intent;
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_appbarlayout);
    }

    @Override
    protected void initView() {
        showBackIcon(true);
        setToolbarTitle("AppBarLayout Example");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Snackbar.make(mRecyclerView, position + ":click", Snackbar.LENGTH_SHORT).show();
    }
}
