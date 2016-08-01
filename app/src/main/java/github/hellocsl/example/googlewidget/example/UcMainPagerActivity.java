package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import github.hellocsl.example.googlewidget.BuildConfig;
import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.adapter.TestFragmentAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;
import github.hellocsl.example.googlewidget.behavior.uc.UcNewsHeaderPagerBehavior;
import github.hellocsl.example.googlewidget.helper.StatusBarCompat;

/**
 * Created by chensuilun on 16/7/24.
 */
public class UcMainPagerActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, UcNewsHeaderPagerBehavior.OnPagerStateListener {
    private static final String TAG = "UcMainPagerActivity";
    private ViewPager mNewsPager;
    private TabLayout mTableLayout;
    private List<TestFragment> mFragments;
    private UcNewsHeaderPagerBehavior mPagerBehavior;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UcMainPagerActivity.class);
        return intent;
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_uc_main_pager);
    }

    @Override
    protected void tintStatusBar() {
        StatusBarCompat.setStatusBarColor(this, ActivityCompat.getColor(this, R.color.colorPrimary));
    }

    @Override
    protected void initView() {
        mPagerBehavior = (UcNewsHeaderPagerBehavior) ((CoordinatorLayout.LayoutParams) findViewById(R.id.id_uc_news_header_pager).getLayoutParams()).getBehavior();
        mPagerBehavior.setPagerStateListener(this);
        mNewsPager = (ViewPager) findViewById(R.id.id_uc_news_content);
        mTableLayout = (TabLayout) findViewById(R.id.id_uc_news_tab);
        mFragments = new ArrayList<TestFragment>();
        for (int i = 0; i < 4; i++) {
            mFragments.add(TestFragment.newInstance(String.valueOf(i), false));
            mTableLayout.addTab(mTableLayout.newTab().setText("Tab" + i));
        }
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        mTableLayout.setOnTabSelectedListener(this);
        mNewsPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTableLayout));
        mNewsPager.setAdapter(new TestFragmentAdapter(mFragments, getSupportFragmentManager()));
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mNewsPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPagerClosed() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPagerClosed: ");
        }
        for (TestFragment fragment : mFragments) {
            fragment.setRefreshEnable(true);
        }
    }

    @Override
    public void onPagerOpened() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPagerOpened: ");
        }
        for (TestFragment fragment : mFragments) {
            fragment.setRefreshEnable(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (mPagerBehavior != null && mPagerBehavior.isClosed()) {
            mPagerBehavior.openPager();
        } else {
            super.onBackPressed();
        }
    }
}
