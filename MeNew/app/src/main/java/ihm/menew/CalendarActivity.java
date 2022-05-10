package ihm.menew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.fragments.MainFragment;

public class CalendarActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        onclickMidi();
    }

    void onClickSoir(){
        findViewById(R.id.soirLundi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(1).setSoir(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));
        });
        findViewById(R.id.soirMardi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(2).setSoir(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));
        });
        findViewById(R.id.soirMercredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(3).setSoir(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.soirJeudi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(4).setSoir(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.soirVendredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(5).setSoir(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.soirSamedi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(6).setSoir(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.soirDimanche).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(0).setSoir(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
    }

    void onclickMidi(){
        findViewById(R.id.midiLundi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(1).setMidi(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));
        });
        findViewById(R.id.midiMardi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(2).setMidi(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));
        });
        findViewById(R.id.midiMercredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(3).setMidi(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.midiJeudi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(4).setMidi(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.midiVendredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(5).setMidi(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.midiSamedi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(6).setMidi(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
        findViewById(R.id.midiDimanche).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(0).setMidi(MeNewApplication.platsCHoisis);
            startActivity( new Intent(getApplicationContext(), MainActivity.class));

        });
    }


    @Override
    public void onButtonClicked(View view) {
        // Retrieve button tag
        String tag = (String) view.getTag();
        System.out.println(tag);
        if (tag == null){
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        if (tag != null) {
            Log.e(getClass().getSimpleName(), "Tag est non null");
        }
        System.out.println(tag);
        switch(Objects.requireNonNull(tag)){
            case "10":
                Log.e(getClass().getSimpleName(),"Button Home clicked !");
                onResume();
                return;
            case "15":
                Log.e(getClass().getSimpleName(),"Button Planning clicked !");
                startActivity(new Intent(this, CalendarActivity.class));
                return;
            case "20":
                Log.e(getClass().getSimpleName(),"Button Favori clicked !");
                startActivity(new Intent(this, FavorisActivity.class));
                return;
            case "30":
                Log.e(getClass().getSimpleName(),"Button Search clicked !");
                startActivity(new Intent(this, Research.class));
                return;
            case "40":
                Log.e(getClass().getSimpleName(),"Button Historique clicked !");
                startActivity(new Intent(this, NotificationsActivity.class));
                return;
            default:break;
        }
    }
}
