package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.factories.WebServiceRecipeFactory;
import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.fragments.MainFragment;
import ihm.menew.webservice.Point;
import ihm.menew.webservice.Result;

public class Research extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    public ListView listView;
    private WebServiceRecipeFactory webServiceRecipeFactory;
    private Point point;
    private EditText input;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        listView = (ListView)findViewById(R.id.listResults);
        input = (EditText) findViewById(R.id.Input);
        webServiceRecipeFactory = new WebServiceRecipeFactory();
        onClickResearch();
        intent = new Intent(this, DetailActivity.class);
        setUpNavigationBar();
    }

    @Override
    public void onButtonClicked(View view) {
        // Retrieve button tag
        String tag = (String) view.getTag();
        System.out.println(tag);
        if (tag == null) {
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        if (tag != null) {
            Log.e(getClass().getSimpleName(), "Tag est non null");
        }
        System.out.println(tag);
        switch (Objects.requireNonNull(tag)) {
            case "10":
                Log.e(getClass().getSimpleName(), "Button Home clicked !");
                startActivity(new Intent(this, MainActivity.class));
                return;
            case "15":
                Log.e(getClass().getSimpleName(), "Button Planning clicked !");
                startActivity(new Intent(this, CalendarActivity.class));
                return;
            case "20":
                Log.e(getClass().getSimpleName(), "Button Favori clicked !");
                startActivity(new Intent(this, FavorisActivity.class));
                return;
            case "30":
                Log.e(getClass().getSimpleName(), "Button Search clicked !");
                onResume();
                return;
            case "40":
                Log.e(getClass().getSimpleName(), "Button Historique clicked !");
                startActivity(new Intent(this, NotificationsActivity.class));
                return;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void onClickResearch() {
        findViewById(R.id.research_button).setOnClickListener( click -> {
            Log.e("Research", "button CHERCHER clicked!");
            Result[] results = new Result[0];
            String request = input.getText().toString();
            try {
                point = webServiceRecipeFactory.makeRequest(request);
                results = point.getResults().toArray(new Result[0]);
            } catch (IOException e) {
                Log.e("Research", "Something went wrong");
                e.printStackTrace();
            }
            ArrayAdapter<Result> arrayAdapter
                    = new ArrayAdapter<Result>(this, android.R.layout.simple_list_item_1 , results);

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // On récupère l'item clické
                    Result result = (Result) listView.getAdapter().getItem(i);
                    System.out.println(result);
                    intent.putExtra("RESULT", (Serializable) result);
                    intent.putExtra("photo", (boolean) false);
                    startActivity(intent);
                }
            });
        });
    }

    private void setUpNavigationBar(){
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(item  -> {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                case R.id.navigation_calendar: {
                    startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    return true;
                }
                case R.id.navigation_favoris: {
                    startActivity(new Intent(getApplicationContext(), FavorisActivity.class));
                    return true;
                }
                case R.id.navigation_research: {
                    onResume();
                    return true;
                }
                case R.id.navigation_history: {
                    startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                    return true;
                }
            }
            return true;
        });
    }
}