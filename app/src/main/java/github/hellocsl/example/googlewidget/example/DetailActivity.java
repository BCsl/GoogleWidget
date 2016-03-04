package github.hellocsl.example.googlewidget.example;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import github.hellocsl.example.googlewidget.R;
import github.hellocsl.example.googlewidget.base.BaseActivity;
import github.hellocsl.example.googlewidget.config.PaletteBitmap;
import github.hellocsl.example.googlewidget.config.PaletteBitmapTranscoder;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/3/2 0002.
 */
public class DetailActivity extends BaseActivity {

    private static final String EXTRA_IMAGE = "EXTRA.IMAGE";
    private TextView mTvTitle;
    private TextView mTvDest;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mFloatingActionButton;

    public static void navigate(AppCompatActivity activity, View transitionImage, String url) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void initContentView() {
        initActivityTransitions();
        setContentView(R.layout.activity_detail);
        ViewCompat.setTransitionName(findViewById(R.id.appbar), EXTRA_IMAGE);
    }

    @Override
    protected void initView() {
        showBackIcon(true);
        String url = getIntent().getStringExtra(EXTRA_IMAGE);
        mCollapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        mCollapsingToolbarLayout.setTitle("Palette Example");
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimaryIcons));
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.detail_fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mCollapsingToolbarLayout, "FAB ON CLICK", Snackbar.LENGTH_SHORT).show();
            }
        });
        mTvDest = (TextView) findViewById(R.id.detail_description);
        mTvTitle = (TextView) findViewById(R.id.detail_title);
        final ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        Glide.with(this).load(url).asBitmap().transcode(new PaletteBitmapTranscoder(imageView.getContext()), PaletteBitmap.class).
                fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(new ImageViewTarget<PaletteBitmap>(imageView) {

            @Override
            protected void setResource(PaletteBitmap resource) {
                super.view.setImageBitmap(resource.bitmap);
                applyPalette(resource.palette);
            }
        });

    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    @Override
    protected void initData() {

    }

    private void applyPalette(Palette palette) {
//        supportStartPostponedEnterTransition();
        int primaryDark = getResources().getColor(R.color.colorPrimary);
        int primary = getResources().getColor(R.color.colorPrimaryDark);
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.colorAccent));
        mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        mFloatingActionButton.setRippleColor(lightVibrantColor);
        mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
        Palette.Swatch vibrantSwatch = palette.getMutedSwatch();
        if (vibrantSwatch != null) {
            mTvTitle.setTextColor(vibrantSwatch.getTitleTextColor());
            mTvDest.setTextColor(vibrantSwatch.getBodyTextColor());
        }
    }
}
