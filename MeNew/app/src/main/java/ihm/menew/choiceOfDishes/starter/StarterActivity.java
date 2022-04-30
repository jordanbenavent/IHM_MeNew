package ihm.menew.choiceOfDishes.starter;

import androidx.appcompat.app.AppCompatActivity;

import ihm.menew.MeNewApplication;
import ihm.menew.R;
import android.os.Bundle;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        ((MeNewApplication)getApplication()).onViewStarterCreated(findViewById(R.id.activity_starter));
    }
}