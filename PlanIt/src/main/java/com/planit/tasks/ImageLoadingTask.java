package com.planit.tasks;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.loopj.android.http.BinaryHttpResponseHandler;
import com.planit.constants.GlobalUserStore;
import com.planit.utils.WebClient;

import java.util.concurrent.Callable;

/**
 * Created by Steven on 15/03/14.
 */
public class ImageLoadingTask implements Callable<BitmapDrawable> {

    Context context;
    private String imageUrl;

    public ImageLoadingTask(Context context, String imageUrl) {
        this.context = context;
        this.imageUrl = imageUrl;
    }


    @Override
    public BitmapDrawable call() throws Exception {
        final BitmapDrawable[] drawable = new BitmapDrawable[1];

        String[] allowedContentTypes = new String[]{"image/jpeg", "image/png"};
        WebClient.get(imageUrl.substring(0, imageUrl.length() - 5), null, new BinaryHttpResponseHandler(allowedContentTypes) {
            @Override
            public void onSuccess(byte[] fileData) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(fileData, 0, fileData.length));
                GlobalUserStore.getUser().setImage(bitmapDrawable);
                drawable[0] = bitmapDrawable;
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

        return drawable[0];

    }


}
