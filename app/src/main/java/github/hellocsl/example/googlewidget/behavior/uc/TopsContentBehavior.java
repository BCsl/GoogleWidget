package github.hellocsl.example.googlewidget.behavior.uc;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import github.hellocsl.example.googlewidget.R;

/**
 * Created by chensuilun on 16/7/9.
 */
public class TopsContentBehavior extends CoordinatorLayout.Behavior<View> {

    public TopsContentBehavior() {
    }

    public TopsContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.id_uc_news_content;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}