package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.adapter.TestFragmentAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/3/1 0001.
 */
public class TabLayoutActivity extends BaseActivity implements OnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout mTableLayout;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TabLayoutActivity.class);
        return intent;
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_tablayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab_menu, menu);
        return true;
    }

    @Override
    protected void initView() {
        showBackIcon(true);
        setToolbarTitle("TabLayoutExample");
        mTableLayout = (TabLayout) findViewById(R.id.tabs);
        ArrayList<Fragment> fragments = new ArrayList<>(3);
        for (int i = 0; i < 4; i++) {
            fragments.add(TestFragment.newInstance(String.valueOf(i)));
            mTableLayout.addTab(mTableLayout.newTab().setText("Tab" + i));
        }
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        mTableLayout.setOnTabSelectedListener(this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTableLayout));
        mViewPager.setAdapter(new TestFragmentAdapter(fragments, getSupportFragmentManager()));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Snackbar.make(mViewPager, tab.getText() + " selected", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_center:
                mTableLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
                return true;
            case R.id.menu_fill:
                mTableLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                return true;
            case R.id.menu_fixed:
                mTableLayout.setTabMode(TabLayout.MODE_FIXED);
                return true;
            case R.id.menu_scroller:
                mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
