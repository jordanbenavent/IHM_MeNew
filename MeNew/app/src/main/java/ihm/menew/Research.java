package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.Objects;
import java.util.WeakHashMap;

import ihm.menew.fragments.MainFragment;
import ihm.menew.webservice.Point;
import ihm.menew.webservice.Result;
import ihm.menew.webservice.WebService;

public class Research extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    public ListView listView;
    private WebService webService;
    private Point point;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        listView = (ListView)findViewById(R.id.listResults);
        input = (EditText) findViewById(R.id.Input);
        webService = new WebService();
        onClickResearch();
    }

    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(),"Button clicked !");
        String tag = (String) view.getTag();
        if (tag == null){
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        switch(Objects.requireNonNull(tag)) {
            case "10":
                Log.e(getClass().getSimpleName(), "Button Home clicked !");
                Research.this.finish();
                break;
            case "20":
                Log.e(getClass().getSimpleName(),"Button Favori clicked !");
                startActivity(new Intent(this, SecondActivity.class));
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
                point = webService.makeRequest(request);
                results = point.getResults().toArray(new Result[0]);
            } catch (IOException e) {
                Log.e("Research", "Something went wrong");
                e.printStackTrace();
            }
            ArrayAdapter<Result> arrayAdapter
                    = new ArrayAdapter<Result>(this, android.R.layout.simple_list_item_1 , results);

            listView.setAdapter(arrayAdapter);
        });
    }
}