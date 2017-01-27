package com.example.dell.mygreeting;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by DELL on 1/26/2017.
 */

public final class ScreenGif {
    private ScreenGif() {
    }

    public static Bitmap takeScreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap screenshot = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return screenshot;
    }
}
