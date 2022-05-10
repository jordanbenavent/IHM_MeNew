package ihm.menew.favoris.mvc;

import androidx.appcompat.app.AppCompatActivity;

import ihm.menew.CalendarActivity;
import ihm.menew.MainActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.Research;
import ihm.menew.SecondActivity;
import ihm.menew.databinding.ActivityFavorisBinding;
import ihm.menew.databinding.ActivityRecetteBinding;
import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.fragments.MainFragment;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarItemView;

import java.util.Objects;

public class FavorisActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        ((MeNewApplication)getApplication()).onViewFavorisCreated(findViewById(R.id.activity_favoris));
        setUpNavigationBar();
    }

    private void setUpNavigationBar(){
        findViewById(R.id.navigation_favoris).setSelected(true);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(item  -> {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    startActivity( new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                case R.id.navigation_calendar: {
                    startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    return true;
                }
                case R.id.navigation_favoris: {
                    onResume();
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