package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import ihm.menew.fragments.MainFragment;

public class ActivityModificationUtilisateur extends AppCompatActivity  implements MainFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_utilisateur);
    }

    @Override
    public void onButtonClicked(View view) {

    }
}