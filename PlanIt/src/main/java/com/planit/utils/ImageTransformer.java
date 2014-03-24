package com.planit.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

import com.squareup.picasso.Transformation;

/**
 * Created by Steven on 15/03/14.
 */
public class ImageTransformer implements Transformation{

    public static Bitmap getRoundedShape(Bitmap sourceBitmap) {
        int targetWidth = 114;
        int targetHeight = 114;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW
        );

        canvas.clipPath(path);

        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null
        );

        if (targetBitmap != sourceBitmap) {
            sourceBitmap.recycle();
        }
        return targetBitmap;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        return ImageTransformer.getRoundedShape(source);
    }

    @Override
    public String key() {
        return "rounded_image";
    }
}
