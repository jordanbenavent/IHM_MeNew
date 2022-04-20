package ihm.menew.favoris.mvc;

import androidx.appcompat.app.AppCompatActivity;

import ihm.menew.R;
import ihm.menew.fragments.MainFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FavorisActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fav", "Oris");
        setContentView(R.layout.activity_favoris);
    }

    @Override
    public void onButtonClicked(View view) {
        Log.e("click", "favoris");
    }
}