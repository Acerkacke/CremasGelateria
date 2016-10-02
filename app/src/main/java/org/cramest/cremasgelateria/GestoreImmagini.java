package org.cramest.cremasgelateria;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 27/09/2016.
 */

public class GestoreImmagini{

    public void getBitmapFromURL(BitmapHandler handler,String src,int posizione) {
        getBitmapFromUrlTask getter =  new getBitmapFromUrlTask(handler,posizione);
        getter.execute(src);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }

    private class getBitmapFromUrlTask extends AsyncTask<String, Void, Bitmap>{

        public BitmapHandler imageHandler;
        private int posGelato;

        public getBitmapFromUrlTask(BitmapHandler imageHandler,int posGelato){
            this.imageHandler = imageHandler;
            this.posGelato = posGelato;
        }

        @Override
        protected Bitmap doInBackground(String... srcs) {
            try {
                URL url = new URL(srcs[0]);
                System.out.println("SRC:"+srcs[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Bitmap result) {
            imageHandler.HandleBitmap(result,posGelato);
            super.onPostExecute(result);
        }
    }

}
