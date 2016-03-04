package github.hellocsl.example.googlewidget.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2015/11/30 0030.
 */
public class GlobalGlideModule implements GlideModule {
    private static final String CACHE_DIR = "glide";
    private static final int CACHE_SIZE = 100 * 1024 * 1024;

    public GlobalGlideModule() {
    }

    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        glideBuilder.setDiskCache(new ExternalCacheDiskCacheFactory(context, CACHE_DIR, CACHE_SIZE));
    }

    @Override
    public void registerComponents(Context arg0, Glide arg1) {

    }


}