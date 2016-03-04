package github.hellocsl.example.googlewidget.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import github.hellocsl.example.googlewidget.R;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/2/29 0029.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimaryDark));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        initView();
        initData();
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void showBackIcon(boolean flag) {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(flag);
        }
    }

    public void setToolbarTitle(int resId) {
        if (mToolbar != null) {
            getSupportActionBar().setTitle(resId);
        }
    }

    public void setToolbarTitle(String res) {
        if (mToolbar != null) {
            getSupportActionBar().setTitle(res);
        }
    }


    public void setToolbarSubTitle(int resId) {
        if (mToolbar != null) {
            getSupportActionBar().setSubtitle(resId);
        }
    }

    public void setToolbarSubTitle(String res) {
        if (mToolbar != null) {
            getSupportActionBar().setSubtitle(res);
        }
    }


    protected abstract void initContentView();

    protected abstract void initView();

    protected abstract void initData();
}
