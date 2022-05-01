package ihm.menew.choiceOfDishes.dessert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import ihm.menew.CalendarActivity;
import ihm.menew.MainActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.Research;
import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.favoris.mvc.FavorisActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DessertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);
        ((MeNewApplication)getApplication()).onViewDessertCreated(findViewById(R.id.activity_desserts), getIntent());
        setUpNavigationBar();

        if(getIntent().getIntExtra("jour", -1) != -1) {
            findViewById(R.id.goCook).setVisibility(View.INVISIBLE);
            ConstraintLayout constraintLayout = findViewById(R.id.activity_desserts);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.listStarter,ConstraintSet.TOP,R.id.textChoiceDessert,ConstraintSet.BOTTOM,0);
            constraintSet.connect(R.id.listStarter,ConstraintSet.BOTTOM,R.id.addToPlanning,ConstraintSet.TOP,0);
            constraintSet.applyTo(constraintLayout);

        }
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