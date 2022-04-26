package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ihm.menew.fragments.DetailFragment;
import ihm.menew.webservice.Result;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment;
    public static Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.configureAndShowDetailFragment();
    }

    private void configureAndShowDetailFragment() {
        Intent intent = getIntent();
        result = (Result) intent.getSerializableExtra("RESULT");
        // On trouve le fragment
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);
        if (detailFragment != null){
            detailFragment.setTitle(result.getTitle());
            detailFragment.setImageUrl(result.getImage());
        }


        if (detailFragment == null) {
            // B - Create new main fragment
            detailFragment = new DetailFragment();
            // Mise à jour de ses données
            detailFragment.setTitle(result.getTitle());
            detailFragment.setImageUrl(result.getImage());
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailFragment)
                    .commit();
        }
    }
}