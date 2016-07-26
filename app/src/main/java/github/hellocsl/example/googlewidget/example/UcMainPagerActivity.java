package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.base.BaseActivity;

/**
 * Created by chensuilun on 16/7/24.
 */
public class UcMainPagerActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UcMainPagerActivity.class);
        return intent;
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_uc_main_pager);
    }

    @Override
    protected void initView() {
        findViewById(R.id.id_uc_news_content).post(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.id_uc_news_header_pager);
            }
        });
    }

    @Override
    protected void initData() {

    }

}
