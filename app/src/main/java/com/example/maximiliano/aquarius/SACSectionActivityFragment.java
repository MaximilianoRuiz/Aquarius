package com.example.maximiliano.aquarius;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class SACSectionActivityFragment extends Fragment {

    public static final String URL = "http://aquarius.umaine.edu/images/bht.jpg";

    private ProgressBar progressBar;
    private ImageView imageView;

    public SACSectionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sacsection, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        ImageWorker imageWorker = new ImageWorker();
        imageWorker.execute(URL);

        return rootView;
    }

    public class ImageWorker extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String image = downloadImage(url);
            return image;
        }

        @Override
        protected void onPostExecute(String pathImage) {
            super.onPostExecute(pathImage);

            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(BitmapFactory.decodeFile(pathImage));

        }

        private String downloadImage(String imageHttpAddress) {
            java.net.URL imageUrl = null;
            Bitmap image = null;
            String path = "";
            try {
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                image = BitmapFactory.decodeStream(conn.getInputStream());
                path = saveImage(getActivity(), "satelite", image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        }

        private String saveImage (Context context, String nombre, Bitmap image){
            ContextWrapper cw = new ContextWrapper(context);
            File dirImages = cw.getDir("images", Context.MODE_PRIVATE);
            File myPath = new File(dirImages, nombre + ".jpg");

            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(myPath);
                image.compress(Bitmap.CompressFormat.JPEG, 10, fos);
                fos.flush();
            }catch (FileNotFoundException ex){
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            return myPath.getAbsolutePath();
        }
    }
}