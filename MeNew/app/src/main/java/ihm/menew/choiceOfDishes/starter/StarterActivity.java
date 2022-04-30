package ihm.menew.choiceOfDishes.starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.choiceOfDishes.dish.DishesActivity;

import android.content.Intent;
import android.os.Bundle;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        ((MeNewApplication)getApplication()).onViewStarterCreated(findViewById(R.id.activity_starter));
        goToDishes();
    }


    public void goToDishes(){
        findViewById(R.id.goToDish).setOnClickListener(click -> {
            startActivity(new Intent(getApplicationContext(), DishesActivity.class));
        });
    }
}