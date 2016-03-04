package github.hellocsl.example.googlewidget.config;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/3/2 0002.
 */
public class PaletteBitmap {
    public final Palette palette;
    public final Bitmap bitmap;

    public PaletteBitmap( Bitmap bitmap,  Palette palette) {
        this.bitmap = bitmap;
        this.palette = palette;
    }
}
