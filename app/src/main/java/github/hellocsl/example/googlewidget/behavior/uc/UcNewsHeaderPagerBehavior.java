package github.hellocsl.example.googlewidget.behavior.uc;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;

import github.hellocsl.example.googlewidget.BuildConfig;
import github.hellocsl.example.googlewidget.DemoApplication;
import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.behavior.uc.helper.ViewOffsetBehavior;

/**
 * 可滚动的新闻列表头部
 * <p/>
 * Created by chensuilun on 16/7/24.
 */
public class UcNewsHeaderPagerBehavior extends ViewOffsetBehavior {
    private static final String TAG = "UcNewsHeaderPager";
    private int mTouchSlop;

    public UcNewsHeaderPagerBehavior() {
        init();
    }


    public UcNewsHeaderPagerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        final ViewConfiguration configuration = ViewConfiguration.get(DemoApplication.getAppContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onStartNestedScroll: ");
        }
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 && canScroll(child, 0);
    }

    private boolean canScroll(View child, float pendingDy) {
        int pendingTranslationY = (int) (child.getTranslationY() - pendingDy);
        if (pendingTranslationY >= getHeaderOffsetRange() && pendingTranslationY <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onNestedPreScroll() called with: dx = [" + dx + "], dy = [" + dy + "]");
        }
        //dy>0 scroll up;dy<0,scroll down
        float halfOfDis = dy / 2.0f;
        if (!canScroll(child, halfOfDis)) {
            child.setTranslationY(halfOfDis > 0 ? getHeaderOffsetRange() : 0);
            return;
        }
        child.setTranslationY(child.getTranslationY() - halfOfDis);
        consumed[1] = dy;
    }


    private int getHeaderOffsetRange() {
        return DemoApplication.getAppContext().getResources().getDimensionPixelOffset(R.dimen.uc_news_header_pager_offset);
    }


}
