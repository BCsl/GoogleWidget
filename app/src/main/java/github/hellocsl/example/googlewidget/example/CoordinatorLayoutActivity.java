package github.hellocsl.example.googlewidget.example;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.adapter.TestFragmentAdapter;
import github.hellocsl.example.googlewidget.base.BaseActivity;
import github.hellocsl.example.googlewidget.behavior.uc.NewsTitleBehavior;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/2/29 0029.
 */
public class CoordinatorLayoutActivity extends BaseActivity {
    private ViewPager mViewPager;
    private View mTitle;
    private View mNewsContent;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CoordinatorLayoutActivity.class);
        return intent;
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_coordinator);
    }

    @Override
    protected void initView() {
        mTitle = findViewById(R.id.news_title_fl);
//        mNewsContent = findViewById(R.id.id_uc_news_content);
//        mNewsContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewCompat.offsetTopAndBottom(mNewsContent, -10);
//            }
//        });

        ArrayList<Fragment> fragments = new ArrayList<>(3);
        for (int i = 0; i < 4; i++) {
            fragments.add(TestFragment.newInstance(String.valueOf(i)));
        }
        mNewsContent = findViewById(R.id.id_uc_news_content);
        mViewPager = (ViewPager) findViewById(R.id.news_pager);
        mViewPager.setAdapter(new TestFragmentAdapter(fragments, getSupportFragmentManager()));
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mTitle.getLayoutParams();
        NewsTitleBehavior newsTitleBehavior = new NewsTitleBehavior(mNewsContent);
        layoutParams.setBehavior(newsTitleBehavior);
    }

    @Override
    protected void initData() {

    }
}
