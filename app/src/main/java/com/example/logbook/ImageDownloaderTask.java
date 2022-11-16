package com.example.logbook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

    public interface OnResult {
        void onListen(Bitmap result);
    }

    private OnResult resultListener;

    @Override
    protected void onPostExecute(Bitmap result) {
        resultListener.onListen(result);
    }

    public void setOnPostExecute(OnResult onResult) {
        this.resultListener = onResult;
    }

    @Override
    protected Bitmap doInBackground(String... URL) {

        String pictureURL = URL[0];
        Bitmap bitmap = null;
        try {
            InputStream input = new java.net.URL(pictureURL).openStream();
            bitmap = BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}

