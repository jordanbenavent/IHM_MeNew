package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ihm.menew.factories.WebServiceRecipeFactory;
import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.webservice.Point;
import ihm.menew.webservice.Result;

public class HistoryActivity extends AppCompatActivity {

    private TextView textView;
    private TextView readyInMin;
    private ImageView imageView;
    private ImageButton arrowRight;
    private Bitmap bitmap;

    private WebServiceRecipeFactory webServiceRecipeFactory;
    private Intent intent;
    private Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setUpNavigationBar();
        webServiceRecipeFactory = new WebServiceRecipeFactory();
        textView = findViewById(R.id.nomPlat);
        imageView = findViewById(R.id.imagePlat);
        readyInMin = findViewById(R.id.time);
        arrowRight = findViewById(R.id.check);
        // pour visualiser
        setImageUrl("https://spoonacular.com/recipeImages/654959-312x231.jpg");
        textView.setText("Pasta With Tuna");
        readyInMin.setText("30 min");
        onCliclDetail();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    void onCliclDetail() {
        arrowRight.setOnClickListener(click -> {
            intent = new Intent(this, DetailActivity.class);
            String request = "pasta";
            Log.e(getClass().getSimpleName(),"Button detail clicked !");
            try {
                point = webServiceRecipeFactory.makeRequest(request);
                Result result = point.getResults().get(0);
                intent.putExtra("RESULT", (Serializable) result);
                startActivity(intent);
            } catch (IOException e) {
                Log.e("Research", "Something went wrong");
                e.printStackTrace();
            }
        });
    }

    private void setUpNavigationBar(){
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(item  -> {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    this.finish();
                    return true;
                }
                case R.id.navigation_calendar: {
                    this.finish();
                    startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    return true;
                }
                case R.id.navigation_favoris: {
                    this.finish();
                    startActivity(new Intent(getApplicationContext(), FavorisActivity.class));
                    return true;
                }
                case R.id.navigation_research: {
                    this.finish();
                    startActivity(new Intent(getApplicationContext(), Research.class));
                    return true;
                }
                case R.id.navigation_history: {
                    onResume();
                    return true;
                }
            }
            return true;
        });
    }

    public void setImageUrl(String imageUrl) {
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

            this.imageView.setImageBitmap(bitmap);
        }
    }
}