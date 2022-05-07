package ihm.menew.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import java.util.ArrayList;
import java.util.zip.Inflater;

import ihm.menew.R;


public class DetailFragment extends Fragment {

    private String title;
    private String ImageUrl;
    private String sourceUrl;
    private int readyIn;
    private ArrayList<String> ingredients;
    private View photoFragment;

    private TextView textView;
    private TextView readyInMin;
    private TextView ingredientList;
    private TextView urlRecette;
    private ImageView imageView;
    private Bitmap bitmap;

    private boolean photoHere = false;

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
        readyInMin = result.findViewById(R.id.readyIn);
        ingredientList = result.findViewById(R.id.ingrediantsList);
        urlRecette = result.findViewById(R.id.lienRecette);
        photoFragment = result.findViewById(R.id.photoFrame);
        // setteurs
        textView.setText(title);
        readyInMin.setText("Prêt dans : " + readyIn + " min");
        imageView.setImageBitmap(bitmap);
        ingredientList.setText(createList(ingredients));
        urlRecette.setText(sourceUrl);
        photoFragment.setVisibility(photoHere ? View.VISIBLE : View.GONE);
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

    public void setListIngredients(ArrayList<String> list){
        this.ingredients = list;
    }

    public void setReadyIn(int readyIn) {
        this.readyIn = readyIn;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String createList(ArrayList<String> ingredients){
        String result = "";
        for (String s : ingredients){
            result += (" • " + s + "\n");
        }
        return result;
    }

    public void setPhotoHere(boolean isHere){
        this.photoHere = isHere;
    }
}