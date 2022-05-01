package ihm.menew.choiceOfDishes.dish;

import androidx.appcompat.app.AppCompatActivity;

import ihm.menew.CalendarActivity;
import ihm.menew.MainActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.Research;
import ihm.menew.choiceOfDishes.dessert.DessertActivity;
import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.favoris.mvc.FavorisActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DishesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        ((MeNewApplication)getApplication()).onViewDishesCreated(findViewById(R.id.activity_dishes));
        goToDessert();
        setUpNavigationBar();
    }

    public void goToDessert(){
        findViewById(R.id.goToDessert).setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), DessertActivity.class);
            int indiceJour = getIntent().getIntExtra("jour", -1);
            intent.putExtra("jour", indiceJour);
            startActivity(intent);
        });
    }

    private void setUpNavigationBar() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                case R.id.navigation_calendar: {
                    startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    return true;
                }
                case R.id.navigation_favoris: {
                    startActivity(new Intent(getApplicationContext(), FavorisActivity.class));
                    return true;
                }
                case R.id.navigation_research: {
                    startActivity(new Intent(getApplicationContext(), Research.class));
                    return true;
                }
                case R.id.navigation_history: {
                    startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                    return true;
                }
            }
            return true;
        });
    }
}