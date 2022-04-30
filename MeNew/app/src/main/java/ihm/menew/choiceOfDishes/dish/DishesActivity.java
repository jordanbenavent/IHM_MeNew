package ihm.menew.choiceOfDishes.dish;

import androidx.appcompat.app.AppCompatActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.R;

import android.os.Bundle;

public class DishesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        ((MeNewApplication)getApplication()).onViewDishesCreated(findViewById(R.id.activity_dishes));

    }
}