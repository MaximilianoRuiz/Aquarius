package com.example.maximiliano.aquarius.fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.maximiliano.aquarius.GalleryActivity;
import com.example.maximiliano.aquarius.R;
import com.example.maximiliano.aquarius.data.DetailVO;
import com.example.maximiliano.aquarius.data.Utility;

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
public class DetailFragment extends Fragment {

    private static final String IMAGE_PATH = "/data/data/com.example.maximiliano.aquarius/app_images/";

    TextView tvTitle, tvDescription;
    ImageView imageView;
    ProgressBar progressBar;
    DetailVO detailVO;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ((GalleryActivity)getActivity()).changeActivityTitle(getResources().getString(R.string.detail));

        detailVO = (DetailVO) this.getArguments().getSerializable(GalleryActivityFragment.DETAILVO);

        tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        tvTitle.setText(detailVO.getTitle());
        tvDescription.setText(detailVO.getDescription());

        ImageWorker imageWorker = new ImageWorker();

        imageWorker.execute(detailVO.getURLImage(), Utility.getURLFileName(detailVO.getURLImage()));

        return rootView;
    }


    public class ImageWorker extends AsyncTask<String, Void, String> {

        public static final String IMAGES = "images";
        public static final String EMPTY_PATH = "";
        private Bitmap bitmap = null;

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String name = params[1];
            File f = new File(IMAGE_PATH + name + Utility.JPG);
            if(f.exists() && !f.isDirectory()) {
                return f.getAbsolutePath();
            } else {
                return downloadImage(url, name);
            }
        }

        @Override
        protected void onPostExecute(String pathImage) {
            super.onPostExecute(pathImage);

            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            Bitmap finalBitmap = EMPTY_PATH.equals(pathImage) ?
                    bitmap : BitmapFactory.decodeFile(pathImage);
            imageView.setImageBitmap(finalBitmap);

        }

        private String downloadImage(String imageHttpAddress, String name) {
            java.net.URL imageUrl = null;
            Bitmap image = null;
            String path = EMPTY_PATH;
            try {
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                image = BitmapFactory.decodeStream(conn.getInputStream());
                path = saveImage(getActivity(), name, image);
                if (EMPTY_PATH.equals(path))
                    bitmap = image;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        }

        private String saveImage (Context context, String nombre, Bitmap image){
            ContextWrapper cw = new ContextWrapper(context);
            File dirImages = cw.getDir(IMAGES, Context.MODE_PRIVATE);
            File myPath = new File(dirImages, nombre + Utility.JPG);

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
            catch (Exception e) {
                return EMPTY_PATH;
            }
            return myPath.getAbsolutePath();
        }
    }
}
