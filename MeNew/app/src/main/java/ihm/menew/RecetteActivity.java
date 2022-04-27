package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ihm.menew.semaine.Plat;

public class RecetteActivity extends AppCompatActivity {

    Plat plat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);
        Intent intent = getIntent();
        plat = intent.getParcelableExtra("Plat");
        ((TextView)findViewById(R.id.platRecette)).setText(plat.getNomPlat());
        ((ImageView)findViewById(R.id.imageRecette)).setImageResource(plat.getImage());
        ((TextView)findViewById(R.id.recette)).setText(plat.getPreparation());
        System.out.println(plat.getImage());
    }
}