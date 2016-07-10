package github.hellocsl.example.googlewidget.behavior.uc;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import github.hellocsl.example.googlewidget.BuildConfig;
import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.behavior.uc.helper.ViewOffsetBehavior;

/**
 * Created by chensuilun on 16/7/9.
 */
public class NewsTitleBehavior extends ViewOffsetBehavior<View> {
    private static final String TAG = "NewsTitleBehavior";
    private int mTitleHeight;
    private int mDependencyViewInitTop = -1;
    private int mDependencyDiff = -1;

    public NewsTitleBehavior(final View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                mDependencyViewInitTop = view.getTop();
            }
        });
    }

    public NewsTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        boolean handle = super.onLayoutChild(parent, child, layoutDirection);
        mTitleHeight = child.getHeight();
        return handle;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.id_uc_news_content;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (mTitleHeight == 1 || mDependencyViewInitTop == -1) {
            return false;
        }
        if (mDependencyDiff == -1) {
            mDependencyDiff = Math.max(0, mDependencyViewInitTop - mTitleHeight);
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "onDependentViewChanged: mDependencyDiff:" + mDependencyDiff + ",mDependencyViewInitTop:" + mDependencyViewInitTop + ",mTitleHeight:" + mDependencyViewInitTop);
            }
        }

        if (dependency.getTop() >= mDependencyViewInitTop) {
            setTopAndBottomOffset(0);
        } else {
            int diffDepend = mDependencyViewInitTop - dependency.getTop();
            setTopAndBottomOffset((int) (diffDepend / (mDependencyDiff * 1.0f) * mTitleHeight));
        }

        return true;
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}