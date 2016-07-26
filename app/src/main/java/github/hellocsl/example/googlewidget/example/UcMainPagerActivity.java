package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.adapter.TestFragmentAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;
import github.hellocsl.example.googlewidget.helper.StatusBarCompat;

/**
 * Created by chensuilun on 16/7/24.
 */
public class UcMainPagerActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private ViewPager mNewsPager;
    private TabLayout mTableLayout;

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
        mNewsPager = (ViewPager) findViewById(R.id.id_uc_news_content);
        mTableLayout = (TabLayout) findViewById(R.id.id_uc_news_tab);
        ArrayList<Fragment> fragments = new ArrayList<>(3);
        for (int i = 0; i < 4; i++) {
            fragments.add(TestFragment.newInstance(String.valueOf(i), false));
            mTableLayout.addTab(mTableLayout.newTab().setText("Tab" + i));
        }
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        mTableLayout.setOnTabSelectedListener(this);
        mNewsPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTableLayout));
        mNewsPager.setAdapter(new TestFragmentAdapter(fragments, getSupportFragmentManager()));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Snackbar.make(mNewsPager, tab.getText() + " selected", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
