package ihm.menew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.fragments.MainFragment;
import ihm.menew.semaine.PLatEnregistre;
import ihm.menew.semaine.Plat;

public class RecetteActivity extends AppCompatActivity implements  MainFragment.OnButtonClickedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);

        Plat plat = PLatEnregistre.get(getIntent().getStringExtra("nomPlat"));

        ((TextView)findViewById(R.id.recetteNom)).setText(plat.getNomPlat());

    }

    @Override
    public void onButtonClicked(View view) {
        // Retrieve button tag
        String tag = (String) view.getTag();
        if (tag == null) {
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        if (tag != null) {
            Log.e(getClass().getSimpleName(), "Tag est non null");
        }
        switch (Objects.requireNonNull(tag)) {
            case "10":
                Log.e(getClass().getSimpleName(), "Button Home clicked !");
                RecetteActivity.this.finish();
                startActivity(new Intent(this, Research.class));
                //startActivity(new Intent(this, SecondActivity.class));
                return;
            case "20":
                Log.e(getClass().getSimpleName(), "Button Favori clicked !");
                startActivity(new Intent(this, FavorisActivity.class));
                RecetteActivity.this.finish();
                return;
            case "30":
                Log.e(getClass().getSimpleName(), "Button Search clicked !");
                startActivity(new Intent(this, Research.class));
                RecetteActivity.this.finish();
                return;
            default:
                break;
        }
    }
}