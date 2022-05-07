package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import ihm.menew.factories.WebServiceIngredientFactory;
import ihm.menew.fragments.DetailFragment;
import ihm.menew.webservice.PointIngredients;
import ihm.menew.webservice.Result;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment;
    private WebServiceIngredientFactory webServiceIngredientFactory;
    public static Result result;
    public PointIngredients resultIngredients;

    private boolean photoHere = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webServiceIngredientFactory = new WebServiceIngredientFactory();
        this.configureAndShowDetailFragment();
    }

    private void configureAndShowDetailFragment() {
        Intent intent = getIntent();
        result = (Result) intent.getSerializableExtra("RESULT");
        this.photoHere = (boolean) intent.getSerializableExtra("photo");
        // On fait la requete pour obtenir les ingredients et d'autres infos
        try {

            resultIngredients = webServiceIngredientFactory.makeRequestIngredients(String.valueOf(result.getId()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        // On trouve le fragment
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);
        if (detailFragment != null){
            detailFragment.setTitle(result.getTitle());
            detailFragment.setImageUrl(result.getImage());
            // On passe les ingredients et d'autres infos au fragment
            detailFragment.setListIngredients(resultIngredients.ingredientsToArray());
            detailFragment.setReadyIn(resultIngredients.getReadyInMinutes());
            detailFragment.setSourceUrl(resultIngredients.getSourceUrl());
            detailFragment.setPhotoHere(photoHere);
        }


        if (detailFragment == null) {
            // B - Create new main fragment
            detailFragment = new DetailFragment();
            // Mise à jour de ses données
            detailFragment.setTitle(result.getTitle());
            detailFragment.setImageUrl(result.getImage());
            // On passe les ingredients et d'autres infos au fragment
            detailFragment.setListIngredients(resultIngredients.ingredientsToArray());
            detailFragment.setReadyIn(resultIngredients.getReadyInMinutes());
            detailFragment.setSourceUrl(resultIngredients.getSourceUrl());
            detailFragment.setPhotoHere(photoHere);
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailFragment)
                    .commit();
        }
    }
}