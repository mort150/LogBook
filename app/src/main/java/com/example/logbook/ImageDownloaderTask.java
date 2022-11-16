package com.example.logbook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... URL) {

        String imageURL = URL[0];

        Bitmap bitmap = null;
        try {
            // Download Image from URL
            InputStream input = new java.net.URL(imageURL).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        resultListenter.onListen(result);
    }

    public void setOnPostExecute(OnResult onResult){
        this.resultListenter = onResult;
    }

    public interface OnResult {
        public void onListen(Bitmap result);
    }

    private OnResult resultListenter;
}

