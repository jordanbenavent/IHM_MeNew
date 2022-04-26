package ihm.menew.favoris.mvc;

import androidx.appcompat.app.AppCompatActivity;

import ihm.menew.MainActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.SecondActivity;
import ihm.menew.fragments.MainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class FavorisActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fav", "Oris");
        setContentView(R.layout.activity_favoris);
        ((MeNewApplication)getApplication()).onViewFavorisCreated(findViewById(R.id.activity_favoris));


    }

    public void onButtonClicked(View view) {
        // Retrieve button tag
        String tag = (String) view.getTag();
        if (tag == null){
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        if (tag != null) {
            Log.e(getClass().getSimpleName(), "Tag est non null");
        }
        switch(Objects.requireNonNull(tag)){
            case "10":
                Log.e(getClass().getSimpleName(),"Button Home clicked !");
                FavorisActivity.this.finish();                //startActivity(new Intent(this, SecondActivity.class));
                return;
            case "20":
                Log.e(getClass().getSimpleName(),"Button Favori clicked !");
                onResume();
                return;
            case "30":
                Log.e(getClass().getSimpleName(),"Button Search clicked !");
                startActivity(new Intent(this, SecondActivity.class));
                return;
            default:break;
        }


    }
}