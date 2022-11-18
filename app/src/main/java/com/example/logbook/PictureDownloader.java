package com.example.logbook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

public class PictureDownloader extends AsyncTask<String, Void, Bitmap> {

    public interface OnResult {
        void onListen(Bitmap result);
    }

    private OnResult resultListener;

    @Override
    protected void onPostExecute(Bitmap result) {
        resultListener.onListen(result);
    }

    @Override
    protected Bitmap doInBackground(String... URL) {
        String pictureURL = URL[0];
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new java.net.URL(pictureURL).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public void setExecute(OnResult onResult) {
        this.resultListener = onResult;
    }
}

