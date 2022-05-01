package ihm.menew.choiceOfDishes.starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ihm.menew.CalendarActivity;
import ihm.menew.MainActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.Research;
import ihm.menew.choiceOfDishes.dish.DishesActivity;
import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.favoris.mvc.FavorisActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        ((MeNewApplication)getApplication()).onViewStarterCreated(findViewById(R.id.activity_starter));
        goToDishes();
        setUpNavigationBar();
    }


    public void goToDishes(){
        findViewById(R.id.goToDish).setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), DishesActivity.class);
            int indiceJour = getIntent().getIntExtra("jour", -1);
            String quand = getIntent().getStringExtra("temps");
            intent.putExtra("jour", indiceJour);
            intent.putExtra("temps",quand);
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