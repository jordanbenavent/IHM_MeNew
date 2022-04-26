package ihm.menew.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.Inflater;

import ihm.menew.R;


public class DetailFragment extends Fragment {

    private String title;
    private String ImageUrl;
    private TextView textView;
    private ImageView imageView;
    private Bitmap bitmap;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("DetailFragment", "TextView was found");
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_detail, container, false);
        textView = result.findViewById(R.id.NomPlat);
        imageView = result.findViewById(R.id.imagePlat);
        textView.setText(title);
        imageView.setImageBitmap(bitmap);
        return result;
    }

    public void setTitle(String title) {
        if (textView == null){
            Log.e("DetailFragment", "TextView is null");
        }
        this.title = title;

    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
        URL url = null;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            httpConn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int resCode = 0;
        try {
            resCode = httpConn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (resCode == HttpURLConnection.HTTP_OK) {
            InputStream in = null;
            try {
                in = httpConn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = BitmapFactory.decodeStream(in);

            //this.imageView.setImageBitmap(bitmap);
        }
    }
}